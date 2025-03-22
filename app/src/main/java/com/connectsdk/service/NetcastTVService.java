package com.connectsdk.service;

import android.graphics.PointF;
import android.util.Log;

import androidx.exifinterface.media.ExifInterface;

import com.connectsdk.core.AppInfo;
import com.connectsdk.core.ChannelInfo;
import com.connectsdk.core.ExternalInputInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.etc.helper.HttpConnection;
import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.airplay.PListParser;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.ExternalInputControl;
import com.connectsdk.service.capability.KeyControl;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.MouseControl;
import com.connectsdk.service.capability.PowerControl;
import com.connectsdk.service.capability.TVControl;
import com.connectsdk.service.capability.TextInputControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.NotSupportedServiceSubscription;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.NetcastTVServiceConfig;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.netcast.NetcastAppNumberParser;
import com.connectsdk.service.netcast.NetcastApplicationsParser;
import com.connectsdk.service.netcast.NetcastChannelParser;
import com.connectsdk.service.netcast.NetcastHttpServer;
import com.connectsdk.service.netcast.NetcastVirtualKeycodes;
import com.connectsdk.service.netcast.NetcastVolumeParser;
import com.connectsdk.service.sessions.LaunchSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class NetcastTVService extends DeviceService implements Launcher, MediaControl, MediaPlayer, TVControl, VolumeControl, ExternalInputControl, MouseControl, TextInputControl, PowerControl, KeyControl {

    public static final String f313ID = "Netcast TV";
    public static final String ROAP_PATH_APP_STORE = "/roap/api/command/";
    public static final String SMART_SHARE = "SmartShare™";
    public static final String TARGET_3D_MODE = "3DMode";
    public static final String TARGET_APPLIST_GET = "applist_get";
    public static final String TARGET_APPNUM_GET = "appnum_get";
    public static final String TARGET_CHANNEL_LIST = "channel_list";
    public static final String TARGET_CURRENT_CHANNEL = "cur_channel";
    public static final String TARGET_IS_3D = "is_3D";
    public static final String TARGET_VOLUME_INFO = "volume_info";
    public static final String UDAP_API_COMMAND = "command";
    public static final String UDAP_API_EVENT = "event";
    public static final String UDAP_API_PAIRING = "pairing";
    public static final String UDAP_PATH_APPTOAPP_COMMAND = "/udap/api/apptoapp/command/";
    public static final String UDAP_PATH_APPTOAPP_DATA = "/udap/api/apptoapp/data/";
    public static final String UDAP_PATH_COMMAND = "/udap/api/command";
    public static final String UDAP_PATH_DATA = "/udap/api/data";
    public static final String UDAP_PATH_EVENT = "/udap/api/event";
    public static final String UDAP_PATH_PAIRING = "/udap/api/pairing";
    List<AppInfo> applications;
    DIALService dialService;
    DLNAService dlnaService;
    NetcastHttpServer httpServer;
    LaunchSession inputPickerSession;
    StringBuilder keyboardString;
    PointF mMouseDistance;
    Boolean mMouseIsMoving;
    private ResponseListener<String> mTextChangedListener;
    State state;
    List<URLServiceSubscription<?>> subscriptions;

    public enum State {
        NONE,
        INITIAL,
        CONNECTING,
        PAIRING,
        PAIRED,
        DISCONNECTING
    }

    @Override
    public ExternalInputControl getExternalInput() {
        return this;
    }

    @Override
    public KeyControl getKeyControl() {
        return this;
    }

    @Override
    public Launcher getLauncher() {
        return this;
    }

    @Override
    public MediaPlayer getMediaPlayer() {
        return this;
    }

    @Override
    public MouseControl getMouseControl() {
        return this;
    }

    @Override
    public PowerControl getPowerControl() {
        return this;
    }

    @Override
    public TVControl getTVControl() {
        return this;
    }

    @Override
    public TextInputControl getTextInputControl() {
        return this;
    }

    @Override
    public VolumeControl getVolumeControl() {
        return this;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public NetcastTVService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
        this.state = State.INITIAL;
        this.mTextChangedListener = new ResponseListener<String>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(String newValue) {
                NetcastTVService.this.keyboardString = new StringBuilder(newValue);
            }
        };
        this.pairingType = PairingType.PIN_CODE;
        if (serviceDescription.getPort() != 9090) {
            serviceDescription.setPort(9090);
        }
        this.applications = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
        this.keyboardString = new StringBuilder();
        this.state = State.INITIAL;
        this.inputPickerSession = null;
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(f313ID, "urn:schemas-upnp-org:device:MediaRenderer:1");
    }

    @Override
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        if (clazz.equals(MediaPlayer.class)) {
            return getMediaPlayerCapabilityLevel();
        }
        if (clazz.equals(MediaControl.class)) {
            return getMediaControlCapabilityLevel();
        }
        if (clazz.equals(Launcher.class)) {
            return getLauncherCapabilityLevel();
        }
        if (clazz.equals(TVControl.class)) {
            return getTVControlCapabilityLevel();
        }
        if (clazz.equals(VolumeControl.class)) {
            return getVolumeControlCapabilityLevel();
        }
        if (clazz.equals(ExternalInputControl.class)) {
            return getExternalInputControlPriorityLevel();
        }
        if (clazz.equals(MouseControl.class)) {
            return getMouseControlCapabilityLevel();
        }
        if (clazz.equals(TextInputControl.class)) {
            return getTextInputControlCapabilityLevel();
        }
        if (clazz.equals(PowerControl.class)) {
            return getPowerControlCapabilityLevel();
        }
        if (clazz.equals(KeyControl.class)) {
            return getKeyControlCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override
    public void setServiceDescription(ServiceDescription serviceDescription) {
        super.setServiceDescription(serviceDescription);
        if (serviceDescription.getPort() != 9090) {
            serviceDescription.setPort(9090);
        }
    }

    @Override
    public void connect() {
        if (this.state != State.INITIAL) {
            Log.w(Util.T, "already connecting; not trying to connect again: " + this.state);
            return;
        }
        if (!(this.serviceConfig instanceof NetcastTVServiceConfig)) {
            ServiceConfig.ServiceConfigListener listener = this.serviceConfig.getListener();
            this.serviceConfig = new NetcastTVServiceConfig(this.serviceConfig.getServiceUUID());
            this.serviceConfig.setListener(listener);
        }
        if (DiscoveryManager.getInstance().getPairingLevel() == DiscoveryManager.PairingLevel.ON) {
            if (((NetcastTVServiceConfig) this.serviceConfig).getPairingKey() != null && ((NetcastTVServiceConfig) this.serviceConfig).getPairingKey().length() != 0) {
                sendPairingKey(((NetcastTVServiceConfig) this.serviceConfig).getPairingKey());
            } else {
                showPairingKeyOnTV();
            }
            Util.runInBackground(new Runnable() {
                @Override
                public void run() {
                    NetcastTVService netcastTVService = NetcastTVService.this;
                    NetcastTVService netcastTVService2 = NetcastTVService.this;
                    netcastTVService.httpServer = new NetcastHttpServer(netcastTVService2, netcastTVService2.getServiceDescription().getPort(), NetcastTVService.this.mTextChangedListener);
                    NetcastTVService.this.httpServer.setSubscriptions(NetcastTVService.this.subscriptions);
                    NetcastTVService.this.httpServer.start();
                }
            });
            return;
        }
        hConnectSuccess();
    }

    @Override
    public void disconnect() {
        endPairing(null);
        this.connected = false;
        if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (NetcastTVService.this.listener != null) {
                    NetcastTVService.this.listener.onDisconnect(NetcastTVService.this, null);
                }
            }
        });
        NetcastHttpServer netcastHttpServer = this.httpServer;
        if (netcastHttpServer != null) {
            netcastHttpServer.stop();
            this.httpServer = null;
        }
        this.state = State.INITIAL;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }


    public void hConnectSuccess() {
        this.connected = true;
        reportConnected(true);
    }

    @Override
    public void onLoseReachability(DeviceServiceReachability reachability) {
        if (this.connected) {
            disconnect();
        } else if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
    }

    public void hostByeBye() {
        disconnect();
    }

    public void showPairingKeyOnTV() {
        this.state = State.CONNECTING;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                if (NetcastTVService.this.listener != null) {
                    DeviceServiceListener deviceServiceListener = NetcastTVService.this.listener;
                    NetcastTVService netcastTVService = NetcastTVService.this;
                    deviceServiceListener.onPairingRequired(netcastTVService, netcastTVService.pairingType, null);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                NetcastTVService.this.state = State.INITIAL;
                if (NetcastTVService.this.listener != null) {
                    NetcastTVService.this.listener.onConnectionFailure(NetcastTVService.this, error);
                }
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_PAIRING);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "showKey");
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_PAIRING, hashMap), responseListener).send();
    }

    @Override
    public void cancelPairing() {
        removePairingKeyOnTV();
        this.state = State.INITIAL;
    }

    public void removePairingKeyOnTV() {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object response) {
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_PAIRING);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "CancelAuthKeyReq");
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_PAIRING, hashMap), responseListener).send();
    }

    @Override
    public void sendPairingKey(final String pairingKey) {
        this.state = State.PAIRING;
        if (!(this.serviceConfig instanceof NetcastTVServiceConfig)) {
            this.serviceConfig = new NetcastTVServiceConfig(this.serviceConfig.getServiceUUID());
        }
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                NetcastTVService.this.state = State.PAIRED;
                ((NetcastTVServiceConfig) NetcastTVService.this.serviceConfig).setPairingKey(pairingKey);
                NetcastTVService.this.hConnectSuccess();
            }

            @Override
            public void onError(ServiceCommandError error) {
                NetcastTVService.this.state = State.INITIAL;
                if (NetcastTVService.this.listener != null) {
                    NetcastTVService.this.listener.onConnectionFailure(NetcastTVService.this, error);
                }
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_PAIRING);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "hello");
        hashMap.put("value", pairingKey);
        hashMap.put("port", String.valueOf(this.serviceDescription.getPort()));
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_PAIRING, hashMap), responseListener).send();
    }

    private void endPairing(ResponseListener<Object> listener) {
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_PAIRING);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "byebye");
        hashMap.put("port", String.valueOf(this.serviceDescription.getPort()));
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_PAIRING, hashMap), listener).send();
    }

    @Override
    public CapabilityPriorityLevel getLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }


    class NetcastTVLaunchSessionR extends LaunchSession {
        String appName;
        NetcastTVService service;

        @Override
        public void close(ResponseListener<Object> responseListener) {
        }

        NetcastTVLaunchSessionR(NetcastTVService service, String auid, String appName) {
            this.service = service;
            this.appId = auid;
        }

        NetcastTVLaunchSessionR(NetcastTVService service, JSONObject obj) throws JSONException {
            this.service = service;
            fromJSONObject(obj);
        }

        @Override
        public JSONObject toJSONObject() throws JSONException {
            JSONObject jSONObject = super.toJSONObject();
            jSONObject.put("type", "netcasttv");
            jSONObject.put("appName", this.appName);
            return jSONObject;
        }

        @Override
        public void fromJSONObject(JSONObject obj) throws JSONException {
            super.fromJSONObject(obj);
            this.appName = obj.optString("appName");
        }
    }

    public void getApplication(final String appName, final AppInfoListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_APPTOAPP_DATA + appName), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                final String strObj = (String) response;

                AppInfo appId = new AppInfo() {{
                    setId(decToHex(strObj));
                }};

                Util.postSuccess(listener, appId);
            }

            @Override
            public void onError(ServiceCommandError error) {
                AppInfoListener appInfoListener = listener;
                if (appInfoListener != null) {
                    Util.postError(appInfoListener, error);
                }
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public void launchApp(final String appId, final AppLaunchListener listener) {
        getAppInfoForId(appId, new AppInfoListener() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(AppInfo appInfo) {
                NetcastTVService.this.launchAppWithInfo(appInfo, listener);
            }
        });
    }

    private void getAppInfoForId(final String appId, final AppInfoListener listener) {
        getAppList(new AppListListener() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(List<AppInfo> object) {
                for (AppInfo appInfo : object) {
                    if (appInfo.getName().equalsIgnoreCase(appId)) {
                        Util.postSuccess(listener, appInfo);
                        return;
                    }
                }
                Util.postError(listener, new ServiceCommandError(0, "Unable to find the App with id", null));
            }
        });
    }


    public void launchApplication(final String appName, final String auid, final String contentId, final AppLaunchListener listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", auid);
            jSONObject.put("title", appName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(auid);
                launchSessionForAppId.setAppName(appName);
                launchSessionForAppId.setService(NetcastTVService.this);
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
                Util.postSuccess(listener, launchSessionForAppId);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_APPTOAPP_COMMAND);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "AppExecute");
        hashMap.put("auid", auid);
        if (appName != null) {
            hashMap.put("appname", appName);
        }
        if (contentId != null) {
            hashMap.put("contentid", contentId);
        }
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), responseListener).send();
    }

    @Override
    public void launchAppWithInfo(AppInfo appInfo, AppLaunchListener listener) {
        launchAppWithInfo(appInfo, null, listener);
    }

    @Override
    public void launchAppWithInfo(AppInfo appInfo, Object params, AppLaunchListener listener) {
        String encode = HttpMessage.encode(appInfo.getName());
        String id = appInfo.getId();
        String str = null;
        JSONObject jSONObject = params instanceof JSONObject ? (JSONObject) params : null;
        if (jSONObject != null) {
            try {
                str = (String) jSONObject.get("contentId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        launchApplication(encode, id, str, listener);
    }

    @Override
    public void launchBrowser(String url, final AppLaunchListener listener) {
        if (url != null && url.length() != 0) {
            Log.w(Util.T, "Netcast TV does not support deeplink for Browser");
        }
        getApplication("Internet", new AppInfoListener() {
            @Override
            public void onSuccess(AppInfo appInfo) {
                NetcastTVService.this.launchApplication("Internet", appInfo.getId(), null, listener);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void launchYouTube(String contentId, AppLaunchListener listener) {
        launchYouTube(contentId, 0.0f, listener);
    }

    @Override
    public void launchYouTube(final String contentId, float startTime, final AppLaunchListener listener) {
        if (getDIALService() != null) {
            getDIALService().getLauncher().launchYouTube(contentId, startTime, listener);
        } else if (startTime <= 0.0d) {
            getApplication("YouTube", new AppInfoListener() {
                @Override
                public void onSuccess(AppInfo appInfo) {
                    NetcastTVService.this.launchApplication(appInfo.getName(), appInfo.getId(), contentId, listener);
                }

                @Override
                public void onError(ServiceCommandError error) {
                    Util.postError(listener, error);
                }
            });
        } else {
            Util.postError(listener, new ServiceCommandError(0, "Cannot reach DIAL service for launching with provided start time", null));
        }
    }

    @Override
    public void launchHulu(final String contentId, final AppLaunchListener listener) {
        getApplication("Hulu", new AppInfoListener() {
            @Override
            public void onSuccess(AppInfo appInfo) {
                NetcastTVService.this.launchApplication("Hulu", appInfo.getId(), contentId, listener);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void launchNetflix(final String contentId, final AppLaunchListener listener) {
        if (!this.serviceDescription.getModelNumber().equals("4.0")) {
            launchApp("Netflix", listener);
        } else {
            getApplication("Netflix", new AppInfoListener() {
                @Override
                public void onSuccess(final AppInfo appInfo) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("id", appInfo.getId());
                        jSONObject.put("name", "Netflix");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ResponseListener<Object> responseListener = new ResponseListener<Object>() {
                        @Override
                        public void onSuccess(Object response) {
                            LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(appInfo.getId());
                            launchSessionForAppId.setAppName("Netflix");
                            launchSessionForAppId.setService(NetcastTVService.this);
                            launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
                            Util.postSuccess(listener, launchSessionForAppId);
                        }

                        @Override
                        public void onError(ServiceCommandError error) {
                            Util.postError(listener, error);
                        }
                    };
                    String uDAPRequestURL = NetcastTVService.this.getUDAPRequestURL(NetcastTVService.UDAP_PATH_APPTOAPP_COMMAND);
                    HashMap hashMap = new HashMap();
                    hashMap.put("name", "SearchCMDPlaySDPContent");
                    hashMap.put("content_type", "1");
                    hashMap.put("conts_exec_type", "20");
                    hashMap.put("conts_plex_type_flag", "N");
                    hashMap.put("conts_search_id", "2023237");
                    hashMap.put("conts_age", "18");
                    hashMap.put("exec_id", "netflix");
                    hashMap.put("item_id", "-Q m=http%3A%2F%2Fapi.netflix.com%2Fcatalog%2Ftitles%2Fmovies%2F" + contentId + "&amp;source_type=4&amp;trackId=6054700&amp;trackUrl=https%3A%2F%2Fapi.netflix.com%2FAPI_APP_ID_6261%3F%23Search%3F");
                    hashMap.put("app_type", "");
                    new ServiceCommand(NetcastTVService.this, uDAPRequestURL, NetcastTVService.this.getUDAPMessageBody(NetcastTVService.UDAP_API_COMMAND, hashMap), responseListener).send();
                }

                @Override
                public void onError(ServiceCommandError error) {
                    Util.postError(listener, error);
                }
            });
        }
    }

    @Override
    public void launchAppStore(final String appId, final AppLaunchListener listener) {
        if (!this.serviceDescription.getModelNumber().equals("4.0")) {
            launchApp("LG Smart World", listener);
            return;
        }
        String uDAPRequestURL = getUDAPRequestURL(ROAP_PATH_APP_STORE);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "SearchCMDPlaySDPContent");
        hashMap.put("content_type", "4");
        hashMap.put("conts_exec_type", "");
        hashMap.put("conts_plex_type_flag", "");
        hashMap.put("conts_search_id", "");
        hashMap.put("conts_age", "12");
        hashMap.put("exec_id", "");
        hashMap.put("item_id", HttpMessage.encode(appId));
        hashMap.put("app_type", ExifInterface.LATITUDE_SOUTH);
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(appId);
                launchSessionForAppId.setAppName("LG Smart World");
                launchSessionForAppId.setService(NetcastTVService.this);
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
                Util.postSuccess(listener, launchSessionForAppId);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public void closeApp(LaunchSession launchSession, ResponseListener<Object> listener) {
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_APPTOAPP_COMMAND);
        HashMap hashMap = new HashMap();
        hashMap.put("name", "AppTerminate");
        hashMap.put("auid", launchSession.getAppId());
        if (launchSession.getAppName() != null) {
            hashMap.put("appname", HttpMessage.encode(launchSession.getAppName()));
        }
        new ServiceCommand(launchSession.getService(), uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), listener).send();
    }

    public void getTotalNumberOfApplications(int type, final AppCountListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_DATA, TARGET_APPNUM_GET, String.valueOf(type)), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, Integer.valueOf(NetcastTVService.this.parseAppNumberXmlToJSON((String) response)));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    private void getApplications(int type, int number, final AppListListener listener) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                String strObj = (String) response;

                JSONArray applicationArray = parseApplicationsXmlToJSON(strObj);
                List<AppInfo> appList = new ArrayList<AppInfo>();

                for (int i = 0; i < applicationArray.length(); i++) {
                    try {
                        final JSONObject appJSON = applicationArray.getJSONObject(i);

                        AppInfo appInfo = new AppInfo() {{
                            setId(appJSON.getString("id"));
                            setName(appJSON.getString("title"));
                        }};

                        appList.add(appInfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Util.postSuccess(listener, appList);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };

        String requestURL = getUDAPRequestURL(UDAP_PATH_DATA, TARGET_APPLIST_GET, String.valueOf(type), "0", String.valueOf(number));

        ServiceCommand<ResponseListener<Object>> command = new ServiceCommand<ResponseListener<Object>>(this, requestURL, null, responseListener);
        command.setHttpMethod(ServiceCommand.TYPE_GET);
        command.send();
    }

    public class C192417 implements AppCountListener {
        final AppListListener val$listener;

        C192417(final AppListListener val$listener) {
            this.val$listener = val$listener;
        }

        public class C19251 implements AppListListener {
            C19251() {
            }

            @Override
            public void onSuccess(List<AppInfo> apps) {
                NetcastTVService.this.applications.addAll(apps);
                NetcastTVService.this.getTotalNumberOfApplications(3, new AppCountListener() {
                    @Override
                    public void onSuccess(final Integer count) {
                        NetcastTVService.this.getApplications(3, count, new AppListListener() {
                            @Override
                            public void onSuccess(List<AppInfo> apps2) {
                                NetcastTVService.this.applications.addAll(apps2);
                                Util.postSuccess(C192417.this.val$listener, NetcastTVService.this.applications);
                            }

                            @Override
                            public void onError(ServiceCommandError error) {
                                Util.postError(C192417.this.val$listener, error);
                            }
                        });
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        Util.postError(C192417.this.val$listener, error);
                    }
                });
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(C192417.this.val$listener, error);
            }
        }

        @Override
        public void onSuccess(final Integer count) {
            NetcastTVService.this.getApplications(2, count.intValue(), new C19251());
        }

        @Override
        public void onError(ServiceCommandError error) {
            Util.postError(this.val$listener, error);
        }
    }

    @Override
    public void getAppList(final AppListListener listener) {
        this.applications.clear();
        getTotalNumberOfApplications(2, new C192417(listener));
    }

    @Override
    public void getRunningApp(AppInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<AppInfoListener> subscribeRunningApp(AppInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return new NotSupportedServiceSubscription();
    }

    @Override
    public void getAppState(final LaunchSession launchSession, final AppStateListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, String.format(Locale.US, "%s%s", getUDAPRequestURL(UDAP_PATH_APPTOAPP_DATA), String.format(Locale.US, "/%s/status", launchSession.getAppId())), null, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(Object object) {
                Launcher.AppState appState;
                String str = (String) object;
                if (str.equalsIgnoreCase("NONE")) {
                    appState = new AppState(false, false);
                } else if (str.equalsIgnoreCase("LOAD")) {
                    appState = new AppState(false, true);
                } else if (str.equalsIgnoreCase("RUN_NF")) {
                    appState = new AppState(true, false);
                } else if (str.equalsIgnoreCase("TERM")) {
                    appState = new AppState(false, true);
                } else {
                    appState = new AppState(false, false);
                }
                Util.postSuccess(listener, appState);
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public ServiceSubscription<AppStateListener> subscribeAppState(LaunchSession launchSession, AppStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public CapabilityPriorityLevel getTVControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void getChannelList(final ChannelListListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_DATA, TARGET_CHANNEL_LIST), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                String str = (String) response;
                try {
                    SAXParserFactory newInstance = SAXParserFactory.newInstance();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                    SAXParser newSAXParser = newInstance.newSAXParser();
                    NetcastChannelParser netcastChannelParser = new NetcastChannelParser();
                    newSAXParser.parse(byteArrayInputStream, netcastChannelParser);
                    JSONArray jSONChannelArray = netcastChannelParser.getJSONChannelArray();
                    ArrayList arrayList = new ArrayList<>();
                    for (int i = 0; i < jSONChannelArray.length(); i++) {
                        try {
                            arrayList.add(NetcastChannelParser.parseRawChannelData((JSONObject) jSONChannelArray.get(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Util.postSuccess(listener, arrayList);
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (ParserConfigurationException e3) {
                    e3.printStackTrace();
                } catch (SAXException e4) {
                    e4.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public void channelUp(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.CHANNEL_UP.getCode(), listener);
    }

    @Override
    public void channelDown(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.CHANNEL_DOWN.getCode(), listener);
    }

    @Override
    public void setChannel(final ChannelInfo channelInfo, final ResponseListener<Object> listener) {
        getChannelList(new ChannelListListener() {
            @Override
            public void onSuccess(List<ChannelInfo> channelList) {
                String uDAPRequestURL = NetcastTVService.this.getUDAPRequestURL(NetcastTVService.UDAP_PATH_COMMAND);
                HashMap<String, String> hashMap = new HashMap<>();
                for (int i = 0; i < channelList.size(); i++) {
                    ChannelInfo channelInfo2 = channelList.get(i);
                    JSONObject rawData = channelInfo2.getRawData();
                    try {
                        String str = channelInfo.getNumber().split("-")[0];
                        String str2 = channelInfo.getNumber().split("-")[1];
                        int majorNumber = channelInfo2.getMajorNumber();
                        int minorNumber = channelInfo2.getMinorNumber();
                        String str3 = (String) rawData.get("sourceIndex");
                        int intValue = (Integer) rawData.get("physicalNumber");
                        if (Integer.parseInt(str) == majorNumber && Integer.parseInt(str2) == minorNumber) {
                            hashMap.put("name", "HandleChannelChange");
                            hashMap.put("major", str);
                            hashMap.put("minor", str2);
                            hashMap.put("sourceIndex", str3);
                            hashMap.put("physicalNum", String.valueOf(intValue));
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                new ServiceCommand(NetcastTVService.this, uDAPRequestURL, NetcastTVService.this.getUDAPMessageBody(NetcastTVService.UDAP_API_COMMAND, hashMap), listener).send();
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void getCurrentChannel(final ChannelListener listener) {
        new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_DATA, TARGET_CURRENT_CHANNEL), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                String str = (String) response;
                try {
                    SAXParserFactory newInstance = SAXParserFactory.newInstance();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                    SAXParser newSAXParser = newInstance.newSAXParser();
                    NetcastChannelParser netcastChannelParser = new NetcastChannelParser();
                    newSAXParser.parse(byteArrayInputStream, netcastChannelParser);
                    JSONArray jSONChannelArray = netcastChannelParser.getJSONChannelArray();
                    if (jSONChannelArray.length() > 0) {
                        Util.postSuccess(listener, NetcastChannelParser.parseRawChannelData((JSONObject) jSONChannelArray.get(0)));
                    }
                } catch (IOException | ParserConfigurationException | JSONException | SAXException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public ServiceSubscription<ChannelListener> subscribeCurrentChannel(final ChannelListener listener) {
        getCurrentChannel(listener);

        URLServiceSubscription<ChannelListener> request = new URLServiceSubscription<ChannelListener>(this, "ChannelChanged", null, null);
        request.setHttpMethod(ServiceCommand.TYPE_GET);
        request.addListener(listener);
        addSubscription(request);

        return request;
    }

    @Override
    public void getProgramInfo(ProgramInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<ProgramInfoListener> subscribeProgramInfo(ProgramInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public void getProgramList(ProgramListListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<ProgramListListener> subscribeProgramList(ProgramListListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public void set3DEnabled(final boolean enabled, final ResponseListener<Object> listener) {
        get3DEnabled(new State3DModeListener() {
            @Override
            public void onSuccess(Boolean isEnabled) {
                if (enabled != isEnabled.booleanValue()) {
                    NetcastTVService.this.sendVirtualKeyCode(NetcastVirtualKeycodes.VIDEO_3D.getCode(), listener);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void get3DEnabled(final State3DModeListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_DATA, TARGET_IS_3D), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, ((String) response).toUpperCase(Locale.ENGLISH).contains("TRUE"));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public ServiceSubscription<State3DModeListener> subscribe3DEnabled(final State3DModeListener listener) {
        get3DEnabled(listener);

        URLServiceSubscription<State3DModeListener> request = new URLServiceSubscription<State3DModeListener>(this, TARGET_3D_MODE, null, null);
        request.setHttpMethod(ServiceCommand.TYPE_GET);
        request.addListener(listener);

        addSubscription(request);

        return request;
    }

    @Override
    public CapabilityPriorityLevel getVolumeControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void volumeUp(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.VOLUME_UP.getCode(), listener);
    }

    @Override
    public void volumeDown(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.VOLUME_DOWN.getCode(), listener);
    }

    @Override
    public void setVolume(float volume, ResponseListener<Object> listener) {
        if (getDLNAService() != null) {
            getDLNAService().setVolume(volume, listener);
        } else {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public void getVolume(final VolumeListener listener) {
        getVolumeStatus(new VolumeStatusListener() {
            @Override
            public void onSuccess(VolumeStatus status) {
                Util.postSuccess(listener, status.volume);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void setMute(final boolean isMute, final ResponseListener<Object> listener) {
        getVolumeStatus(new VolumeStatusListener() {
            @Override
            public void onSuccess(VolumeStatus status) {
                if (isMute != status.isMute) {
                    NetcastTVService.this.sendVirtualKeyCode(NetcastVirtualKeycodes.MUTE.getCode(), listener);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void getMute(final MuteListener listener) {
        getVolumeStatus(new VolumeStatusListener() {
            @Override
            public void onSuccess(VolumeStatus status) {
                Util.postSuccess(listener, status.isMute);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public ServiceSubscription<VolumeListener> subscribeVolume(VolumeListener listener) {
        if (getDLNAService() != null) {
            return getDLNAService().subscribeVolume(listener);
        }
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public ServiceSubscription<MuteListener> subscribeMute(MuteListener listener) {
        if (getDLNAService() != null) {
            return getDLNAService().subscribeMute(listener);
        }
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    private void getVolumeStatus(final VolumeStatusListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getUDAPRequestURL(UDAP_PATH_DATA, TARGET_VOLUME_INFO), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                JSONObject parseVolumeXmlToJSON = NetcastTVService.this.parseVolumeXmlToJSON((String) response);
                try {
                    Util.postSuccess(listener, new VolumeStatus((Boolean) parseVolumeXmlToJSON.get(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME), (Integer) parseVolumeXmlToJSON.get("level")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public CapabilityPriorityLevel getExternalInputControlPriorityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void launchInputPicker(final AppLaunchListener listener) {
        getApplication(HttpMessage.encode("Input List"), new AppInfoListener() {
            @Override
            public void onSuccess(final AppInfo appInfo) {
                NetcastTVService.this.launchApplication("Input List", appInfo.getId(), null, new AppLaunchListener() {
                    @Override
                    public void onSuccess(LaunchSession session) {
                        if (NetcastTVService.this.inputPickerSession == null) {
                            NetcastTVService.this.inputPickerSession = session;
                        }
                        Util.postSuccess(listener, session);
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        Util.postError(listener, error);
                    }
                });
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void closeInputPicker(LaunchSession launchSession, ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.EXIT.getCode(), listener);
    }

    @Override
    public void getExternalInputList(ExternalInputListListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void setExternalInput(ExternalInputInfo input, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void getMediaInfo(final MediaInfoListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().getMediaInfo(listener);
        } else if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener) {
        if (getDLNAService() != null) {
            return getDLNAService().subscribeMediaInfo(listener);
        }
        if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
            return null;
        }
        return null;
    }

    @Override
    public void displayImage(final String url, final String mimeType, final String title, final String description, final String iconSrc, final LaunchListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().displayImage(url, mimeType, title, description, iconSrc, new LaunchListener() {
                @Override
                public void onError(ServiceCommandError error) {
                    if (listener != null) {
                        Util.postError(listener, error);
                    }
                }

                @Override
                public void onSuccess(MediaLaunchObject object) {
                    object.launchSession.setAppId(NetcastTVService.SMART_SHARE);
                    object.launchSession.setAppName(NetcastTVService.SMART_SHARE);
                    object.mediaControl = NetcastTVService.this.getMediaControl();
                    if (listener != null) {
                        Util.postSuccess(listener, object);
                    }
                }
            });
            return;
        }
        System.err.println("DLNA Service is not ready yet");
    }

    @Override
    public void displayImage(MediaInfo mediaInfo, LaunchListener listener) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = null;
        if (mediaInfo != null) {
            String url = mediaInfo.getUrl();
            String mimeType = mediaInfo.getMimeType();
            String title = mediaInfo.getTitle();
            String description = mediaInfo.getDescription();
            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                str6 = mediaInfo.getImages().get(0).getUrl();
            }
            str5 = str6;
            str = url;
            str2 = mimeType;
            str3 = title;
            str4 = description;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        displayImage(str, str2, str3, str4, str5, listener);
    }

    @Override
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener) {
        playMedia(new MediaInfo.Builder(url, mimeType).setTitle(title).setDescription(description).setIcon(iconSrc).build(), shouldLoop, listener);
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, final LaunchListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().playMedia(mediaInfo, shouldLoop, new LaunchListener() {
                @Override
                public void onError(ServiceCommandError error) {
                    if (listener != null) {
                        Util.postError(listener, error);
                    }
                }

                @Override
                public void onSuccess(MediaLaunchObject object) {
                    object.launchSession.setAppId(NetcastTVService.SMART_SHARE);
                    object.launchSession.setAppName(NetcastTVService.SMART_SHARE);
                    object.mediaControl = NetcastTVService.this.getMediaControl();
                    if (listener != null) {
                        Util.postSuccess(listener, object);
                    }
                }
            });
            return;
        }
        System.err.println("DLNA Service is not ready yet");
    }

    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        if (getDLNAService() == null) {
            Util.postError(listener, new ServiceCommandError(0, "Service is not connected", null));
        } else {
            getDLNAService().closeMedia(launchSession, listener);
        }
    }

    @Override
    public MediaControl getMediaControl() {
        return DiscoveryManager.getInstance().getPairingLevel() == DiscoveryManager.PairingLevel.OFF ? getDLNAService() : this;
    }

    @Override
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void play(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.PLAY.getCode(), listener);
    }

    @Override
    public void pause(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.PAUSE.getCode(), listener);
    }

    @Override
    public void stop(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.STOP.getCode(), listener);
    }

    @Override
    public void rewind(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void fastForward(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void previous(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.SKIP_BACKWARD.getCode(), listener);
    }

    @Override
    public void next(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.SKIP_FORWARD.getCode(), listener);
    }

    @Override
    public void seek(long position, ResponseListener<Object> listener) {
        if (getDLNAService() != null) {
            getDLNAService().seek(position, listener);
        } else if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public void getDuration(DurationListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().getDuration(listener);
        } else if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public void getPosition(PositionListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().getPosition(listener);
        } else if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public void getPlayState(PlayStateListener listener) {
        if (getDLNAService() != null) {
            getDLNAService().getPlayState(listener);
        } else if (listener != null) {
            Util.postError(listener, ServiceCommandError.notSupported());
        }
    }

    @Override
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        if (getDLNAService() != null) {
            return getDLNAService().subscribePlayState(listener);
        }
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public CapabilityPriorityLevel getMouseControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    private void setMouseCursorVisible(boolean visible, ResponseListener<Object> listener) {
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_EVENT);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "CursorVisible");
        hashMap.put("value", visible ? PListParser.TAG_TRUE : PListParser.TAG_FALSE);
        hashMap.put("mode", "auto");
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody("event", hashMap), listener).send();
    }

    @Override
    public void connectMouse() {
        setMouseCursorVisible(true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Log.d(Util.T, "Netcast TV's mouse has been connected");
                NetcastTVService.this.mMouseDistance = new PointF(0.0f, 0.0f);
                NetcastTVService.this.mMouseIsMoving = false;
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's mouse connection has been failed");
            }
        });
    }

    @Override
    public void disconnectMouse() {
        setMouseCursorVisible(false, null);
    }

    @Override
    public void click() {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's mouse click has been failed");
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_COMMAND);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name", "HandleTouchClick");
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), responseListener).send();
    }

    @Override
    public void move(double dx, double dy) {
        PointF pointF = this.mMouseDistance;
        pointF.x = (float) (pointF.x + dx);
        PointF pointF2 = this.mMouseDistance;
        pointF2.y = (float) (pointF2.y + dy);
        if (this.mMouseIsMoving) {
            return;
        }
        this.mMouseIsMoving = true;
        moveMouse();
    }


    public void moveMouse() {
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_COMMAND);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "HandleTouchMove");
        hashMap.put("x", String.valueOf((int) this.mMouseDistance.x));
        hashMap.put("y", String.valueOf((int) this.mMouseDistance.y));
        PointF pointF = this.mMouseDistance;
        pointF.y = 0.0f;
        pointF.x = 0.0f;
        new ServiceCommand<ResponseListener<Object>>(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                if (NetcastTVService.this.mMouseDistance.x > 0.0f || NetcastTVService.this.mMouseDistance.y > 0.0f) {
                    moveMouse();
                } else {
                    NetcastTVService.this.mMouseIsMoving = false;
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's mouse move has failed");
                NetcastTVService.this.mMouseIsMoving = false;
            }
        }).send();
    }

    @Override
    public void move(PointF diff) {
        move(diff.x, diff.y);
    }

    @Override
    public void scroll(double dx, double dy) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's mouse scroll has been failed");
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_COMMAND);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "HandleTouchWheel");
        if (dy > 0.0d) {
            hashMap.put("value", "up");
        } else {
            hashMap.put("value", "down");
        }
        new ServiceCommand<ResponseListener<Object>>(this, uDAPRequestURL, getUDAPMessageBody(UDAP_API_COMMAND, hashMap), responseListener).send();
    }

    @Override
    public void scroll(PointF diff) {
        scroll(diff.x, diff.y);
    }

    @Override
    public CapabilityPriorityLevel getTextInputControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public ServiceSubscription<TextInputStatusListener> subscribeTextInputStatus(final TextInputStatusListener listener) {
        keyboardString = new StringBuilder();

        URLServiceSubscription<TextInputStatusListener> request = new URLServiceSubscription<TextInputStatusListener>(this, "KeyboardVisible", null, null);
        request.addListener(listener);

        addSubscription(request);

        return request;
    }

    @Override
    public void sendText(final String input) {
        Log.d(Util.T, "Add to Queue: " + input);
        this.keyboardString.append(input);
        handleKeyboardInput("Editing", this.keyboardString.toString());
    }

    @Override
    public void sendEnter() {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's enter key has been failed");
            }
        };
        handleKeyboardInput("EditEnd", this.keyboardString.toString());
        sendVirtualKeyCode(NetcastVirtualKeycodes.RED.getCode(), responseListener);
    }

    @Override
    public void sendDelete() {
        if (this.keyboardString.length() > 1) {
            StringBuilder sb = this.keyboardString;
            sb.deleteCharAt(sb.length() - 1);
        } else {
            this.keyboardString = new StringBuilder();
        }
        handleKeyboardInput("Editing", this.keyboardString.toString());
    }

    private void handleKeyboardInput(final String state, final String buffer) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's keyboard input has been failed");
            }
        };
        String uDAPRequestURL = getUDAPRequestURL(UDAP_PATH_EVENT);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name", "TextEdited");
        hashMap.put("state", state);
        hashMap.put("value", buffer);
        new ServiceCommand(this, uDAPRequestURL, getUDAPMessageBody("event", hashMap), responseListener).send();
    }

    @Override
    public CapabilityPriorityLevel getKeyControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void up(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.KEY_UP.getCode(), listener);
    }

    @Override
    public void down(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.KEY_DOWN.getCode(), listener);
    }

    @Override
    public void left(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.KEY_LEFT.getCode(), listener);
    }

    @Override
    public void right(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.KEY_RIGHT.getCode(), listener);
    }

    @Override
    public void ok(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.OK.getCode(), listener);
    }

    @Override
    public void back(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.BACK.getCode(), listener);
    }

    @Override
    public void home(final ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.HOME.getCode(), listener);
    }

    @Override
    public CapabilityPriorityLevel getPowerControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void powerOff(ResponseListener<Object> listener) {
        sendVirtualKeyCode(NetcastVirtualKeycodes.POWER.getCode(), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Netcast TV's power off has been failed");
            }
        });
    }

    @Override
    public void powerOn(ResponseListener<Object> listener) {
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }


    public JSONObject parseVolumeXmlToJSON(String data) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes("UTF-8"));
            SAXParser newSAXParser = newInstance.newSAXParser();
            NetcastVolumeParser netcastVolumeParser = new NetcastVolumeParser();
            newSAXParser.parse(byteArrayInputStream, netcastVolumeParser);
            return netcastVolumeParser.getVolumeStatus();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }


    public int parseAppNumberXmlToJSON(String data) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes("UTF-8"));
            SAXParser newSAXParser = newInstance.newSAXParser();
            NetcastAppNumberParser netcastAppNumberParser = new NetcastAppNumberParser();
            newSAXParser.parse(byteArrayInputStream, netcastAppNumberParser);
            return netcastAppNumberParser.getApplicationNumber();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public JSONArray parseApplicationsXmlToJSON(String data) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
            SAXParser newSAXParser = newInstance.newSAXParser();
            NetcastApplicationsParser netcastApplicationsParser = new NetcastApplicationsParser();
            newSAXParser.parse(byteArrayInputStream, netcastApplicationsParser);
            return netcastApplicationsParser.getApplications();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHttpMessageForHandleKeyInput(final int keycode) {
        String valueOf = String.valueOf(keycode);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name", "HandleKeyInput");
        hashMap.put("value", valueOf);
        return getUDAPMessageBody(UDAP_API_COMMAND, hashMap);
    }

    @Override
    public void sendKeyCode(KeyCode keycode, ResponseListener<Object> listener) {
        switch (keycode) {
            case NUM_0:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_0.getCode(), listener);
                break;
            case NUM_1:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_1.getCode(), listener);
                break;
            case NUM_2:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_2.getCode(), listener);
                break;
            case NUM_3:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_3.getCode(), listener);
                break;
            case NUM_4:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_4.getCode(), listener);
                break;
            case NUM_5:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_5.getCode(), listener);
                break;
            case NUM_6:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_6.getCode(), listener);
                break;
            case NUM_7:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_7.getCode(), listener);
                break;
            case NUM_8:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_8.getCode(), listener);
                break;
            case NUM_9:
                sendVirtualKeyCode(NetcastVirtualKeycodes.NUMBER_9.getCode(), listener);
                break;
            case DASH:
                sendVirtualKeyCode(NetcastVirtualKeycodes.DASH.getCode(), listener);
                break;
            case ENTER:
                sendVirtualKeyCode(NetcastVirtualKeycodes.OK.getCode(), listener);
                break;
            default:
                Util.postError(listener, new ServiceCommandError(0, "The keycode is not available", null));
        }
    }

    public void sendVirtualKeyCode(final int keycode, final ResponseListener<Object> listener) {
        setMouseCursorVisible(false, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    Thread.sleep(150L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new ServiceCommand(NetcastTVService.this, NetcastTVService.this.getUDAPRequestURL(NetcastTVService.UDAP_PATH_COMMAND), NetcastTVService.this.getHttpMessageForHandleKeyInput(keycode), listener).send();
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    public String getUDAPRequestURL(String path) {
        return getUDAPRequestURL(path, null);
    }

    private String getUDAPRequestURL(String path, String target) {
        return getUDAPRequestURL(path, target, null);
    }

    private String getUDAPRequestURL(String path, String target, String type) {
        return getUDAPRequestURL(path, target, type, null, null);
    }

    private String getUDAPRequestURL(String path, String target, String type, String index, String number) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(this.serviceDescription.getIpAddress());
        sb.append(":");
        sb.append(this.serviceDescription.getPort());
        sb.append(path);
        if (target != null) {
            sb.append("?target=");
            sb.append(target);
            if (type != null) {
                sb.append("&type=");
                sb.append(type);
            }
            if (index != null) {
                sb.append("&index=");
                sb.append(index);
            }
            if (number != null) {
                sb.append("&number=");
                sb.append(number);
            }
        }
        return sb.toString();
    }

    public String getUDAPMessageBody(String api, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<envelope>");
        sb.append("<api type=\"").append(api).append("\">");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(createNode(entry.getKey(), entry.getValue()));
        }
        sb.append("</api>");
        sb.append("</envelope>");
        return sb.toString();
    }

    private String createNode(String tag, String value) {
        return "<" + tag + ">" + value + "</" + tag + ">";
    }

    public String decToHex(String dec) {
        if (dec == null || dec.length() <= 0) {
            return null;
        }
        try {
            return decToHex(Long.parseLong(dec.trim()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decToHex(long dec) {
        return String.format("%016x", dec);
    }

    @Override
    public void sendCommand(final ServiceCommand<?> mCommand) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                Object payload = mCommand.getPayload();
                try {
                    HttpConnection newInstance = HttpConnection.newInstance(URI.create(mCommand.getTarget()));
                    newInstance.setHeader("User-Agent", HttpMessage.UDAP_USER_AGENT);
                    newInstance.setHeader("Content-Type", HttpMessage.CONTENT_TYPE_TEXT_XML);
                    if (payload != null && mCommand.getHttpMethod().equalsIgnoreCase("POST")) {
                        newInstance.setMethod(HttpConnection.Method.POST);
                        newInstance.setPayload(payload.toString());
                    }
                    newInstance.execute();
                    int responseCode = newInstance.getResponseCode();
                    Log.d("", "RESP " + responseCode);
                    if (responseCode == 200) {
                        Util.postSuccess(((ServiceCommand<?>) mCommand).getResponseListener(), newInstance.getResponseString());
                    } else {
                        Util.postError(((ServiceCommand<?>) mCommand).getResponseListener(), ServiceCommandError.getError(responseCode));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Util.postError(((ServiceCommand<?>) mCommand).getResponseListener(), new ServiceCommandError(0, e.getMessage(), null));
                }
            }
        });
    }

    private void addSubscription(URLServiceSubscription<?> subscription) {
        this.subscriptions.add(subscription);
        NetcastHttpServer netcastHttpServer = this.httpServer;
        if (netcastHttpServer != null) {
            netcastHttpServer.setSubscriptions(this.subscriptions);
        }
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
        this.subscriptions.remove(subscription);
        NetcastHttpServer netcastHttpServer = this.httpServer;
        if (netcastHttpServer != null) {
            netcastHttpServer.setSubscriptions(this.subscriptions);
        }
    }

    @Override
    protected void updateCapabilities() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (DiscoveryManager.getInstance().getPairingLevel() == DiscoveryManager.PairingLevel.ON) {
            Collections.addAll(arrayList, TextInputControl.Capabilities);
            Collections.addAll(arrayList, MouseControl.Capabilities);
            Collections.addAll(arrayList, KeyControl.Capabilities);
            Collections.addAll(arrayList, MediaPlayer.Capabilities);
            arrayList.add(PowerControl.Off);
            arrayList.add(MediaControl.Play);
            arrayList.add(MediaControl.Pause);
            arrayList.add(MediaControl.Stop);
            arrayList.add(MediaControl.Duration);
            arrayList.add(MediaControl.Position);
            arrayList.add(MediaControl.Seek);
            arrayList.add(Launcher.Application);
            arrayList.add(Launcher.Application_Close);
            arrayList.add(Launcher.Application_List);
            arrayList.add(Launcher.Browser);
            arrayList.add(Launcher.Hulu);
            arrayList.add(Launcher.Netflix);
            arrayList.add(Launcher.Netflix_Params);
            arrayList.add(Launcher.YouTube);
            arrayList.add(Launcher.YouTube_Params);
            arrayList.add(Launcher.AppStore);
            arrayList.add(TVControl.Channel_Up);
            arrayList.add(TVControl.Channel_Down);
            arrayList.add(TVControl.Channel_Get);
            arrayList.add(TVControl.Channel_List);
            arrayList.add(TVControl.Channel_Subscribe);
            arrayList.add(TVControl.Get_3D);
            arrayList.add(TVControl.Set_3D);
            arrayList.add(TVControl.Subscribe_3D);
            arrayList.add(ExternalInputControl.Picker_Launch);
            arrayList.add(ExternalInputControl.Picker_Close);
            arrayList.add(VolumeControl.Volume_Get);
            arrayList.add(VolumeControl.Volume_Up_Down);
            arrayList.add(VolumeControl.Mute_Get);
            arrayList.add(VolumeControl.Mute_Set);
            if (this.serviceDescription.getModelNumber().equals("4.0")) {
                arrayList.add(Launcher.AppStore_Params);
            }
        } else {
            Collections.addAll(arrayList, MediaPlayer.Capabilities);
            arrayList.add(MediaControl.Play);
            arrayList.add(MediaControl.Pause);
            arrayList.add(MediaControl.Stop);
            arrayList.add(Launcher.YouTube);
            arrayList.add(Launcher.YouTube_Params);
        }
        arrayList.add(MediaPlayer.Subtitle_SRT);
        setCapabilities(arrayList);
    }

    public DLNAService getDLNAService() {
        ConnectableDevice connectableDevice;
        if (this.dlnaService == null && (connectableDevice = DiscoveryManager.getInstance().getAllDevices().get(this.serviceDescription.getIpAddress())) != null) {
            DLNAService dLNAService = null;
            Iterator<DeviceService> it = connectableDevice.getServices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceService next = it.next();
                if (DLNAService.class.isAssignableFrom(next.getClass())) {
                    dLNAService = (DLNAService) next;
                    break;
                }
            }
            this.dlnaService = dLNAService;
        }
        return this.dlnaService;
    }

    public DIALService getDIALService() {
        ConnectableDevice connectableDevice;
        if (this.dialService == null && (connectableDevice = DiscoveryManager.getInstance().getAllDevices().get(this.serviceDescription.getIpAddress())) != null) {
            DIALService dIALService = null;
            Iterator<DeviceService> it = connectableDevice.getServices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceService next = it.next();
                if (DIALService.class.isAssignableFrom(next.getClass())) {
                    dIALService = (DIALService) next;
                    break;
                }
            }
            this.dialService = dIALService;
        }
        return this.dialService;
    }
}
