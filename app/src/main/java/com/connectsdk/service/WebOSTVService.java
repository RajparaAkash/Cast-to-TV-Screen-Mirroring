package com.connectsdk.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.util.Log;
import android.view.Surface;

import androidx.annotation.NonNull;

import com.connectsdk.core.AppInfo;
import com.connectsdk.core.ChannelInfo;
import com.connectsdk.core.ExternalInputInfo;
import com.connectsdk.core.ImageInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.device.DefaultConnectableDeviceStore;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.ExternalInputControl;
import com.connectsdk.service.capability.KeyControl;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.MouseControl;
import com.connectsdk.service.capability.PlaylistControl;
import com.connectsdk.service.capability.PowerControl;
import com.connectsdk.service.capability.RemoteCameraControl;
import com.connectsdk.service.capability.ScreenMirroringControl;
import com.connectsdk.service.capability.TVControl;
import com.connectsdk.service.capability.TextInputControl;
import com.connectsdk.service.capability.ToastControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.WebAppLauncher;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.NotSupportedServiceSubscription;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.config.WebOSTVServiceConfig;
import com.connectsdk.service.sessions.LaunchSession;
import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebOSWebAppSession;
import com.connectsdk.service.webos.WebOSTVDeviceService;
import com.connectsdk.service.webos.WebOSTVKeyboardInput;
import com.connectsdk.service.webos.WebOSTVMouseSocketConnection;
import com.connectsdk.service.webos.WebOSTVServiceSocketClient;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;
import com.connectsdk.service.webos.lgcast.common.utils.XmlUtil;
import com.connectsdk.service.webos.lgcast.remotecamera.api.RemoteCameraApi;
import com.connectsdk.service.webos.lgcast.screenmirroring.api.ScreenMirroringApi;
import com.google.android.gms.actions.SearchIntents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class WebOSTVService extends WebOSTVDeviceService implements Launcher, MediaPlayer, PlaylistControl, VolumeControl, TVControl, ToastControl, ExternalInputControl, MouseControl, KeyControl, TextInputControl, WebAppLauncher, ScreenMirroringControl, RemoteCameraControl {
    static String APP_STATE = "ssap://system.launcher/getAppState";
    static String APP_STATUS = "ssap://com.webos.service.appstatus/getAppStatus";
    static String CHANNEL = "ssap://tv/getCurrentChannel";
    static String CHANNEL_LIST = "ssap://tv/getChannelList";
    static final String CLOSE_APP_URI = "ssap://system.launcher/close";
    static final String CLOSE_MEDIA_URI = "ssap://media.viewer/close";
    static final String CLOSE_WEBAPP_URI = "ssap://webapp/closeWebApp";
    static String FOREGROUND_APP = "ssap://com.webos.applicationManager/getForegroundAppInfo";

    public static final String ID = "webOS TV";
    private static final String MEDIA_PLAYER_ID = "MediaPlayer";
    static String MUTE = "ssap://audio/getMute";
    static String PROGRAM = "ssap://tv/getChannelProgramInfo";
    static String VOLUME = "ssap://audio/getVolume";
    static String VOLUME_STATUS = "ssap://audio/getStatus";
    WebOSTVKeyboardInput keyboardInput;
    ConcurrentHashMap<String, String> mAppToAppIdMappings;
    private WebOSTVServiceSocketClient.WebOSTVServiceSocketClientListener mSocketListener;
    ConcurrentHashMap<String, WebOSWebAppSession> mWebAppSessions;
    WebOSTVMouseSocketConnection mouseSocket;
    List<String> permissions;
    WebOSTVServiceSocketClient socket;


    public interface ACRAuthTokenListener extends ResponseListener<String> {
    }


    public interface LaunchPointsListener extends ResponseListener<JSONArray> {
    }


    public interface SecureAccessTestListener extends ResponseListener<Boolean> {
    }


    public interface ServiceInfoListener extends ResponseListener<JSONArray> {
    }


    public interface SystemInfoListener extends ResponseListener<JSONObject> {
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
    public PlaylistControl getPlaylistControl() {
        return this;
    }

    @Override
    public RemoteCameraControl getRemoteCameraControl() {
        return this;
    }

    @Override
    public ScreenMirroringControl getScreenMirroringControl() {
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
    public ToastControl getToastControl() {
        return this;
    }

    @Override
    public VolumeControl getVolumeControl() {
        return this;
    }

    @Override
    public WebAppLauncher getWebAppLauncher() {
        return this;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public WebOSTVService(ServiceDescription serviceDescription, ServiceConfig serviceConf) {
        super(serviceDescription, serviceConf);
        this.mSocketListener = new WebOSTVServiceSocketClient.WebOSTVServiceSocketClientListener() {
            @Override
            public void onRegistrationFailed(final ServiceCommandError error) {
                WebOSTVService.this.disconnect();
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (WebOSTVService.this.listener != null) {
                            WebOSTVService.this.listener.onConnectionFailure(WebOSTVService.this, error);
                        }
                    }
                });
            }

            @Override
            public Boolean onReceiveMessage(JSONObject message) {
                return true;
            }

            @Override
            public void updateClientKey(String ClientKey) {
                try {
                    WebOSTVService.this.setClientKey(ClientKey);
                } catch (Exception e) {
                    Log.e("ConnectSDK", e.getMessage(), e);
                }
            }

            @Override
            public void updateUUID(String UUID) {
                WebOSTVService.this.serviceDescription.setUUID(UUID);
            }

            @Override
            public void updateIPAddress(String IPAddress) {
                WebOSTVService.this.serviceDescription.setIpAddress(IPAddress);
            }

            @Override
            public void onFailWithError(final ServiceCommandError error) {
                WebOSTVService.this.socket.setListener(null);
                WebOSTVService.this.socket.disconnect();
                WebOSTVService.this.socket = null;
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (WebOSTVService.this.listener != null) {
                            WebOSTVService.this.listener.onConnectionFailure(WebOSTVService.this, error);
                        }
                    }
                });
            }

            @Override
            public void onConnect() {
                WebOSTVService.this.reportConnected(true);
            }

            @Override
            public void onCloseWithError(final ServiceCommandError error) {
                WebOSTVService.this.socket.setListener(null);
                WebOSTVService.this.socket.disconnect();
                WebOSTVService.this.socket = null;
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (WebOSTVService.this.listener != null) {
                            WebOSTVService.this.listener.onDisconnect(WebOSTVService.this, error);
                        }
                    }
                });
            }

            @Override
            public void onBeforeRegister(final PairingType pairingType) {
                if (DiscoveryManager.getInstance().getPairingLevel().compareTo(DiscoveryManager.PairingLevel.PROTECTED) >= 0) {
                    Util.runOnUI(new Runnable() {
                        @Override
                        public void run() {
                            if (WebOSTVService.this.listener != null) {
                                WebOSTVService.this.listener.onPairingRequired(WebOSTVService.this, pairingType, null);
                            }
                        }
                    });
                }
            }
        };
        this.serviceConfig = new WebOSTVServiceConfig(serviceConf.toJSONObject());
        this.pairingType = PairingType.FIRST_SCREEN;
        this.mAppToAppIdMappings = new ConcurrentHashMap<>();
        this.mWebAppSessions = new ConcurrentHashMap<>();
    }

    public WebOSTVServiceConfig getWebOSTVServiceConfig() {
        return (WebOSTVServiceConfig) this.serviceConfig;
    }

    @Override
    public void setPairingType(PairingType pairingType) {
        this.pairingType = pairingType;
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
        if (clazz.equals(ToastControl.class)) {
            return getToastControlCapabilityLevel();
        }
        if (clazz.equals(WebAppLauncher.class)) {
            return getWebAppLauncherCapabilityLevel();
        }
        if (clazz.equals(PlaylistControl.class)) {
            return getPlaylistControlCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override
    public void setServiceDescription(ServiceDescription serviceDescription) {
        super.setServiceDescription(serviceDescription);
        if (this.serviceDescription.getVersion() != null || this.serviceDescription.getResponseHeaders() == null) {
            return;
        }
        String[] split = serviceDescription.getResponseHeaders().get("Server").get(0).split(StringUtil.SPACE)[0].split("/");
        this.serviceDescription.setVersion(split[split.length - 1]);
        try {
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(serviceDescription.getLocationXML()));
            String[] split2 = newDocumentBuilder.parse(inputSource).getElementsByTagName("serviceId").item(0).getTextContent().split("-");
            this.serviceDescription.setPort(Integer.parseInt(split2[split2.length - 1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCapabilities();
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "urn:lge-com:service:webos-second-screen:1");
    }

    @Override
    public boolean isConnected() {
        if (this.socket == null) {
            return false;
        }
        if (DiscoveryManager.getInstance().getPairingLevel().compareTo(DiscoveryManager.PairingLevel.PROTECTED) >= 0) {
            return this.socket.isConnected() && this.socket.getClientKey() != "";
        }
        return this.socket.isConnected();
    }

    @Override
    public void connect() {
        if (this.socket == null) {
            WebOSTVServiceSocketClient webOSTVServiceSocketClient = new WebOSTVServiceSocketClient(getWebOSTVServiceConfig(), getPairingType(), getPermissions(), WebOSTVServiceSocketClient.getURI(getServiceDescription().getIpAddress(), getServiceDescription().getPort()));
            this.socket = webOSTVServiceSocketClient;
            webOSTVServiceSocketClient.setListener(this.mSocketListener);
        }
        if (isConnected()) {
            return;
        }
        this.socket.connect();
    }

    @Override
    public void disconnect() {
        Log.d(Util.T, "attempting to disconnect to " + this.serviceDescription.getIpAddress());
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (WebOSTVService.this.listener != null) {
                    WebOSTVService.this.listener.onDisconnect(WebOSTVService.this, null);
                }
            }
        });
        WebOSTVServiceSocketClient webOSTVServiceSocketClient = this.socket;
        if (webOSTVServiceSocketClient != null) {
            webOSTVServiceSocketClient.setListener(null);
            this.socket.disconnect();
            this.socket = null;
        }
        ConcurrentHashMap<String, String> concurrentHashMap = this.mAppToAppIdMappings;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, WebOSWebAppSession> concurrentHashMap2 = this.mWebAppSessions;
        if (concurrentHashMap2 != null) {
            Enumeration<WebOSWebAppSession> elements = concurrentHashMap2.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().disconnectFromWebApp();
            }
            this.mWebAppSessions.clear();
        }
    }

    @Override
    public void cancelPairing() {
        WebOSTVServiceSocketClient webOSTVServiceSocketClient = this.socket;
        if (webOSTVServiceSocketClient != null) {
            webOSTVServiceSocketClient.disconnect();
        }
    }

    public String getClientKey() {
        return getWebOSTVServiceConfig().getClientKey();
    }

    public void setClientKey(String ClientKey) {
        getWebOSTVServiceConfig().setClientKey(ClientKey);
    }

    public ConcurrentHashMap<String, String> getWebAppIdMappings() {
        return this.mAppToAppIdMappings;
    }

    @Override
    public CapabilityPriorityLevel getLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void launchApp(String appId, AppLaunchListener listener) {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(appId);
        launchAppWithInfo(appInfo, listener);
    }

    @Override
    public void launchAppWithInfo(AppInfo appInfo, AppLaunchListener listener) {
        launchAppWithInfo(appInfo, null, listener);
    }

    @Override
    public void launchAppWithInfo(final AppInfo appInfo, Object params, final AppLaunchListener listener) {
        String uri = "ssap://system.launcher/launch";
        JSONObject payload = new JSONObject();

        final String appId = appInfo.getId();

        String contentId = null;

        if (params != null) {
            try {
                contentId = (String) ((JSONObject) params).get("contentId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            payload.put("id", appId);

            if (contentId != null)
                payload.put("contentId", contentId);

            if (params != null)
                payload.put("params", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                JSONObject obj = (JSONObject) response;
                LaunchSession launchSession = new LaunchSession();

                launchSession.setService(WebOSTVService.this);
                launchSession.setAppId(appId); // note that response uses id to mean appId
                launchSession.setSessionId(obj.optString("sessionId"));
                launchSession.setSessionType(LaunchSession.LaunchSessionType.App);

                Util.postSuccess(listener, launchSession);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, uri, payload, true, responseListener);
        request.send();
    }

    @Override
    public void launchBrowser(String url, final AppLaunchListener listener) {
        JSONObject jSONObject = new JSONObject();
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                JSONObject jSONObject2 = (JSONObject) response;
                LaunchSession launchSession = new LaunchSession();
                launchSession.setService(WebOSTVService.this);
                launchSession.setAppId(jSONObject2.optString("id"));
                launchSession.setSessionId(jSONObject2.optString("sessionId"));
                launchSession.setSessionType(LaunchSession.LaunchSessionType.App);
                launchSession.setRawData(jSONObject2);
                Util.postSuccess(listener, launchSession);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        try {
            jSONObject.put("target", url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://system.launcher/open", jSONObject, true, responseListener).send();
    }

    @Override
    public void launchYouTube(String contentId, AppLaunchListener listener) {
        launchYouTube(contentId, 0.0f, listener);
    }

    @Override
    public void launchYouTube(final String contentId, float startTime, final AppLaunchListener listener) {
        JSONObject jSONObject = new JSONObject();
        if (contentId != null && contentId.length() > 0) {
            if (startTime < 0.0d) {
                Util.postError(listener, new ServiceCommandError(0, "Start time may not be negative", null));
                return;
            }
            try {
                jSONObject.put("contentId", String.format("%s&pairingCode=%s&t=%.1f", contentId, UUID.randomUUID().toString(), Float.valueOf(startTime)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        launchAppWithInfo(new AppInfo() {
            {
                setId("youtube.leanback.v4");
                setName("YouTube");
            }
        }, jSONObject, listener);
    }

    @Override
    public void launchHulu(String contentId, AppLaunchListener listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", contentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        launchAppWithInfo(new AppInfo() {
            {
                setId("hulu");
                setName("Hulu");
            }
        }, jSONObject, listener);
    }

    @Override
    public void launchNetflix(String contentId, AppLaunchListener listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", "m=http%3A%2F%2Fapi.netflix.com%2Fcatalog%2Ftitles%2Fmovies%2F" + contentId + "&source_type=4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        launchAppWithInfo(new AppInfo() {
            {
                setId("netflix");
                setName("Netflix");
            }
        }, jSONObject, listener);
    }

    @Override
    public void launchAppStore(String appId, AppLaunchListener listener) {
        AppInfo appInfo = new AppInfo("com.webos.app.discovery");
        appInfo.setName("LG Store");
        JSONObject jSONObject = new JSONObject();
        if (appId != null && appId.length() > 0) {
            try {
                jSONObject.put(SearchIntents.EXTRA_QUERY, String.format("category/GAME_APPS/%s", appId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        launchAppWithInfo(appInfo, jSONObject, listener);
    }

    @Override
    public void closeApp(LaunchSession launchSession, ResponseListener<Object> listener) {
        String appId = launchSession.getAppId();
        String sessionId = launchSession.getSessionId();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", appId);
            jSONObject.put("sessionId", sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(launchSession.getService(), CLOSE_APP_URI, jSONObject, true, listener).send();
    }

    @Override
    public void getAppList(final AppListListener listener) {
        String uri = "ssap://com.webos.applicationManager/listApps";

        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {

                try {
                    JSONObject jsonObj = (JSONObject) response;

                    JSONArray apps = (JSONArray) jsonObj.get("apps");
                    List<AppInfo> appList = new ArrayList<AppInfo>();

                    for (int i = 0; i < apps.length(); i++) {
                        final JSONObject appObj = apps.getJSONObject(i);

                        AppInfo appInfo = new AppInfo() {{
                            setId(appObj.getString("id"));
                            setName(appObj.getString("title"));
                            setRawData(appObj);
                        }};

                        appList.add(appInfo);
                    }

                    Util.postSuccess(listener, appList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, uri, null, true, responseListener);
        request.send();
    }

    @Override
    public void getRunningApp(AppInfoListener listener) {
        getRunningApp(false, listener);
    }

    @Override
    public ServiceSubscription<AppInfoListener> subscribeRunningApp(AppInfoListener listener) {
        return (URLServiceSubscription) getRunningApp(true, listener);
    }

    @Override
    public void getAppState(LaunchSession launchSession, AppStateListener listener) {
        getAppState(false, launchSession, listener);
    }

    @Override
    public ServiceSubscription<AppStateListener> subscribeAppState(LaunchSession launchSession, AppStateListener listener) {
        return (URLServiceSubscription) getAppState(true, launchSession, listener);
    }

    @Override
    public CapabilityPriorityLevel getToastControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void showToast(String message, ResponseListener<Object> listener) {
        showToast(message, null, null, listener);
    }

    @Override
    public void showToast(String message, String iconData, String iconExtension, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", message);
            if (iconData != null) {
                jSONObject.put("iconData", iconData);
                jSONObject.put("iconExtension", iconExtension);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendToast(jSONObject, listener);
    }

    @Override
    public void showClickableToastForApp(String message, AppInfo appInfo, JSONObject params, ResponseListener<Object> listener) {
        showClickableToastForApp(message, appInfo, params, null, null, listener);
    }

    @Override
    public void showClickableToastForApp(String message, AppInfo appInfo, JSONObject params, String iconData, String iconExtension, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", message);
            if (iconData != null) {
                jSONObject.put("iconData", iconData);
                jSONObject.put("iconExtension", iconExtension);
            }
            if (appInfo != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("appId", appInfo.getId());
                if (params != null) {
                    jSONObject2.put("params", params);
                }
                jSONObject.put("onClick", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendToast(jSONObject, listener);
    }

    @Override
    public void showClickableToastForURL(String message, String url, ResponseListener<Object> listener) {
        showClickableToastForURL(message, url, null, null, listener);
    }

    @Override
    public void showClickableToastForURL(String message, String url, String iconData, String iconExtension, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", message);
            if (iconData != null) {
                jSONObject.put("iconData", iconData);
                jSONObject.put("iconExtension", iconExtension);
            }
            if (url != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("target", url);
                jSONObject.put("onClick", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendToast(jSONObject, listener);
    }

    @Override
    public CapabilityPriorityLevel getVolumeControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    public void volumeUp() {
        volumeUp(null);
    }

    @Override
    public void volumeUp(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://audio/volumeUp", null, true, listener).send();
    }

    public void volumeDown() {
        volumeDown(null);
    }

    @Override
    public void volumeDown(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://audio/volumeDown", null, true, listener).send();
    }

    public void setVolume(int volume) {
        setVolume(volume, null);
    }

    @Override
    public void setVolume(float volume, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", Math.round(volume * 100.0f));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://audio/setVolume", jSONObject, true, listener).send();
    }

    @Override
    public void getVolume(VolumeListener listener) {
        getVolume(false, listener);
    }

    @Override
    public ServiceSubscription<VolumeListener> subscribeVolume(VolumeListener listener) {
        return (ServiceSubscription) getVolume(true, listener);
    }

    @Override
    public void setMute(boolean isMute, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME, isMute);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://audio/setMute", jSONObject, true, listener).send();
    }

    @Override
    public void getMute(MuteListener listener) {
        getMuteStatus(false, listener);
    }

    @Override
    public ServiceSubscription<MuteListener> subscribeMute(MuteListener listener) {
        return (ServiceSubscription) getMuteStatus(true, listener);
    }

    public void getVolumeStatus(VolumeStatusListener listener) {
        getVolumeStatus(false, listener);
    }

    public ServiceSubscription<VolumeStatusListener> subscribeVolumeStatus(VolumeStatusListener listener) {
        return (ServiceSubscription) getVolumeStatus(true, listener);
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void getMediaInfo(MediaInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener) {
        listener.onError(ServiceCommandError.notSupported());
        return null;
    }

    private void displayMedia(JSONObject params, final LaunchListener listener) {
        new ServiceCommand(this, "ssap://media.viewer/open", params, true, new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                JSONObject jSONObject = (JSONObject) response;
                LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(jSONObject.optString("id"));
                launchSessionForAppId.setService(WebOSTVService.this);
                launchSessionForAppId.setSessionId(jSONObject.optString("sessionId"));
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.Media);
                Util.postSuccess(listener, new MediaLaunchObject(launchSessionForAppId, WebOSTVService.this));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public void displayImage(final String url, final String mimeType, final String title, final String description, final String iconSrc, final LaunchListener listener) {
        if ("4.0.0".equalsIgnoreCase(this.serviceDescription.getVersion())) {
            DeviceService dlnaService = this.getDLNAService();

            if (dlnaService != null) {
                MediaPlayer mediaPlayer = dlnaService.getAPI(MediaPlayer.class);

                if (mediaPlayer != null) {
                    mediaPlayer.displayImage(url, mimeType, title, description, iconSrc, listener);
                    return;
                }
            }

            JSONObject params = null;

            try {
                params = new JSONObject() {{
                    put("target", url);
                    put("title", title == null ? NULL : title);
                    put("description", description == null ? NULL : description);
                    put("mimeType", mimeType == null ? NULL : mimeType);
                    put("iconSrc", iconSrc == null ? NULL : iconSrc);
                }};
            } catch (JSONException ex) {
                ex.printStackTrace();
                Util.postError(listener, new ServiceCommandError(-1, ex.getLocalizedMessage(), ex));
            }

            if (params != null)
                this.displayMedia(params, listener);
        } else {
            final WebAppSession.LaunchListener webAppLaunchListener = new WebAppSession.LaunchListener() {

                @Override
                public void onError(ServiceCommandError error) {
                    listener.onError(error);
                }

                @Override
                public void onSuccess(WebAppSession webAppSession) {
                    webAppSession.displayImage(url, mimeType, title, description, iconSrc, listener);
                }
            };

            this.getWebAppLauncher().joinWebApp(MEDIA_PLAYER_ID, new WebAppSession.LaunchListener() {

                @Override
                public void onError(ServiceCommandError error) {
                    getWebAppLauncher().launchWebApp(MEDIA_PLAYER_ID, webAppLaunchListener);
                }

                @Override
                public void onSuccess(WebAppSession webAppSession) {
                    webAppSession.displayImage(url, mimeType, title, description, iconSrc, listener);
                }
            });
        }
    }

    @Override
    public void displayImage(MediaInfo mediaInfo, LaunchListener listener) {
        String mediaUrl = null;
        String mimeType = null;
        String title = null;
        String desc = null;
        String iconSrc = null;

        if (mediaInfo != null) {
            mediaUrl = mediaInfo.getUrl();
            mimeType = mediaInfo.getMimeType();
            title = mediaInfo.getTitle();
            desc = mediaInfo.getDescription();

            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                ImageInfo imageInfo = mediaInfo.getImages().get(0);
                iconSrc = imageInfo.getUrl();
            }
        }

        displayImage(mediaUrl, mimeType, title, desc, iconSrc, listener);
    }

    @Override
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener) {
        playMedia(new MediaInfo.Builder(url, mimeType).setTitle(title).setDescription(description).setIcon(iconSrc).build(), shouldLoop, listener);
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
        if ("4.0.0".equalsIgnoreCase(this.serviceDescription.getVersion())) {
            playMediaByNativeApp(mediaInfo, shouldLoop, listener);
        } else {
            playMediaByWebApp(mediaInfo, shouldLoop, listener);
        }
    }

    private void playMediaByWebApp(final MediaInfo mediaInfo, final boolean shouldLoop, final LaunchListener listener) {
        final WebAppSession.LaunchListener launchListener = new WebAppSession.LaunchListener() {

            @Override
            public void onError(ServiceCommandError error) {
                listener.onError(error);
            }

            @Override
            public void onSuccess(WebAppSession webAppSession) {
                webAppSession.playMedia(mediaInfo, shouldLoop, listener);
            }
        };
        getWebAppLauncher().joinWebApp(MEDIA_PLAYER_ID, new WebAppSession.LaunchListener() {

            @Override
            public void onError(ServiceCommandError error) {
                WebOSTVService.this.getWebAppLauncher().launchWebApp(WebOSTVService.MEDIA_PLAYER_ID, launchListener);
            }

            @Override
            public void onSuccess(WebAppSession webAppSession) {
                webAppSession.playMedia(mediaInfo, shouldLoop, listener);
            }
        });
    }

    private void playMediaByNativeApp(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
        ImageInfo imageInfo;
        MediaPlayer mediaPlayer;
        DeviceService dLNAService = getDLNAService();
        if (dLNAService != null && (mediaPlayer = (MediaPlayer) dLNAService.getAPI(MediaPlayer.class)) != null) {
            mediaPlayer.playMedia(mediaInfo, shouldLoop, listener);
            return;
        }
        String str = null;
        List<ImageInfo> images = mediaInfo.getImages();
        if (images != null && !images.isEmpty() && (imageInfo = images.get(0)) != null) {
            str = imageInfo.getUrl();
        }
        try {
            displayMedia(createPlayMediaJsonRequestForSsap(mediaInfo, shouldLoop, str), listener);
        } catch (JSONException e) {
            Util.postError(listener, new ServiceCommandError(-1, e.getLocalizedMessage(), e));
            Log.e(Util.T, "Create JSON request for ssap://media.viewer/open failure", e);
        }
    }

    @NonNull
    private JSONObject createPlayMediaJsonRequestForSsap(final MediaInfo mediaInfo, final boolean shouldLoop, final String iconSrc) throws JSONException {
        return new JSONObject() {{
            put("target", mediaInfo.getUrl());
            put("title", getJsonValue(mediaInfo.getTitle()));
            put("description", getJsonValue(mediaInfo.getDescription()));
            put("mimeType", getJsonValue(mediaInfo.getMimeType()));
            put("iconSrc", getJsonValue(iconSrc));
            put("loop", shouldLoop);
        }};
    }

    public Object getJsonValue(Object value) {
        return value == null ? JSONObject.NULL : value;
    }

    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (launchSession.getAppId() != null && launchSession.getAppId().length() > 0) {
                jSONObject.put("id", launchSession.getAppId());
            }
            if (launchSession.getSessionId() != null && launchSession.getSessionId().length() > 0) {
                jSONObject.put("sessionId", launchSession.getSessionId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(launchSession.getService(), CLOSE_MEDIA_URI, jSONObject, true, listener).send();
    }

    @Override
    public CapabilityPriorityLevel getTVControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    public void channelUp() {
        channelUp(null);
    }

    @Override
    public void channelUp(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://tv/channelUp", null, true, listener).send();
    }

    public void channelDown() {
        channelDown(null);
    }

    @Override
    public void channelDown(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://tv/channelDown", null, true, listener).send();
    }

    @Override
    public void setChannel(ChannelInfo channelInfo, ResponseListener<Object> listener) {
        Objects.requireNonNull(channelInfo, "channelInfo must not be null");
        JSONObject jSONObject = new JSONObject();
        try {
            if (channelInfo.getId() != null) {
                jSONObject.put("channelId", channelInfo.getId());
            }
            if (channelInfo.getNumber() != null) {
                jSONObject.put("channelNumber", channelInfo.getNumber());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://tv/openChannel", jSONObject, true, listener).send();
    }

    public void setChannelById(String channelId) {
        setChannelById(channelId, null);
    }

    public void setChannelById(String channelId, ResponseListener<Object> listener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channelId", channelId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://tv/openChannel", jSONObject, true, listener).send();
    }

    @Override
    public void getCurrentChannel(ChannelListener listener) {
        getCurrentChannel(false, listener);
    }

    @Override
    public ServiceSubscription<ChannelListener> subscribeCurrentChannel(ChannelListener listener) {
        return (ServiceSubscription) getCurrentChannel(true, listener);
    }

    @Override
    public void getChannelList(ChannelListListener listener) {
        getChannelList(false, listener);
    }

    public ServiceSubscription<ChannelListListener> subscribeChannelList(final ChannelListListener listener) {
        return (ServiceSubscription) getChannelList(true, listener);
    }

    public void getChannelCurrentProgramInfo(ProgramInfoListener listener) {
        getChannelCurrentProgramInfo(false, listener);
    }

    public ServiceSubscription<ProgramInfoListener> subscribeChannelCurrentProgramInfo(ProgramInfoListener listener) {
        return (ServiceSubscription) getChannelCurrentProgramInfo(true, listener);
    }

    @Override
    public void getProgramInfo(ProgramInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<ProgramInfoListener> subscribeProgramInfo(ProgramInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return new NotSupportedServiceSubscription();
    }

    @Override
    public void getProgramList(ProgramListListener listener) {
        getProgramList(false, listener);
    }

    @Override
    public ServiceSubscription<ProgramListListener> subscribeProgramList(ProgramListListener listener) {
        return (ServiceSubscription) getProgramList(true, listener);
    }

    @Override
    public void set3DEnabled(final boolean enabled, final ResponseListener<Object> listener) {
        new ServiceCommand(this, enabled ? "ssap://com.webos.service.tv.display/set3DOn" : "ssap://com.webos.service.tv.display/set3DOff", null, true, listener).send();
    }

    private ServiceCommand<State3DModeListener> get3DEnabled(boolean isSubscription, final State3DModeListener listener) {
        ServiceCommand<State3DModeListener> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, Boolean.valueOf(((JSONObject) response).getJSONObject("status3D").getBoolean("status")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, "ssap://com.webos.service.tv.display/get3DStatus", null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, "ssap://com.webos.service.tv.display/get3DStatus", null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    @Override
    public void get3DEnabled(final State3DModeListener listener) {
        get3DEnabled(false, listener);
    }

    @Override
    public ServiceSubscription<State3DModeListener> subscribe3DEnabled(final State3DModeListener listener) {
        return (ServiceSubscription) get3DEnabled(true, listener);
    }

    @Override
    public CapabilityPriorityLevel getExternalInputControlPriorityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void launchInputPicker(final AppLaunchListener listener) {
        final AppInfo appInfo = new AppInfo() {{
            setId("com.webos.app.inputpicker");
            setName("InputPicker");
        }};

        launchAppWithInfo(appInfo, null, new AppLaunchListener() {
            @Override
            public void onSuccess(LaunchSession object) {
                listener.onSuccess(object);
            }

            @Override
            public void onError(ServiceCommandError error) {
                appInfo.setId("com.webos.app.inputmgr");
                launchAppWithInfo(appInfo, null, listener);
            }
        });
    }

    @Override
    public void closeInputPicker(LaunchSession launchSession, ResponseListener<Object> listener) {
        closeApp(launchSession, listener);
    }

    @Override
    public void getExternalInputList(final ExternalInputListListener listener) {
        new ServiceCommand(this, "ssap://tv/getExternalInputList", null, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, WebOSTVService.this.externalnputInfoFromJSONArray((JSONArray) ((JSONObject) response).get(DefaultConnectableDeviceStore.KEY_DEVICES)));
                } catch (JSONException e) {
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
    public void setExternalInput(ExternalInputInfo externalInputInfo, final ResponseListener<Object> listener) {
        String uri = "ssap://tv/switchInput";

        JSONObject payload = new JSONObject();

        try {
            if (externalInputInfo != null && externalInputInfo.getId() != null) {
                payload.put("inputId", externalInputInfo.getId());
            } else {
                Log.w(Util.T, "ExternalInputInfo has no id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, uri, payload, true, listener);
        request.send();
    }

    @Override
    public CapabilityPriorityLevel getMouseControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void connectMouse() {
        connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
            @Override
            public void onConnected() {
                // intentionally left empty
            }
        });
    }

    @Override
    public void disconnectMouse() {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection == null) {
            return;
        }
        webOSTVMouseSocketConnection.disconnect();
        this.mouseSocket = null;
    }

    private void connectMouse(final WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener successHandler) {
        if (this.mouseSocket != null) {
            return;
        }
        new ServiceCommand(this, "ssap://com.webos.service.networkinput/getPointerInputSocket", null, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    WebOSTVService.this.mouseSocket = new WebOSTVMouseSocketConnection((String) ((JSONObject) response).get("socketPath"), successHandler);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Log.w(Util.T, "Connect mouse error: " + error.getMessage());
            }
        }).send();
    }

    @Override
    public void click() {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection != null) {
            webOSTVMouseSocketConnection.click();
        } else {
            connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
                @Override
                public void onConnected() {
                    WebOSTVService.this.mouseSocket.click();
                }
            });
        }
    }

    @Override
    public void move(final double dx, final double dy) {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection != null) {
            webOSTVMouseSocketConnection.move(dx, dy);
        } else {
            connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
                @Override
                public void onConnected() {
                    WebOSTVService.this.mouseSocket.move(dx, dy);
                }
            });
        }
    }

    @Override
    public void move(PointF diff) {
        move(diff.x, diff.y);
    }

    @Override
    public void scroll(final double dx, final double dy) {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection != null) {
            webOSTVMouseSocketConnection.scroll(dx, dy);
        } else {
            connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
                @Override
                public void onConnected() {
                    WebOSTVService.this.mouseSocket.scroll(dx, dy);
                }
            });
        }
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
    public ServiceSubscription<TextInputStatusListener> subscribeTextInputStatus(TextInputStatusListener listener) {
        WebOSTVKeyboardInput webOSTVKeyboardInput = new WebOSTVKeyboardInput(this);
        this.keyboardInput = webOSTVKeyboardInput;
        return webOSTVKeyboardInput.connect(listener);
    }

    @Override
    public void sendText(String input) {
        WebOSTVKeyboardInput webOSTVKeyboardInput = this.keyboardInput;
        if (webOSTVKeyboardInput != null) {
            webOSTVKeyboardInput.addToQueue(input);
        }
    }

    @Override
    public void sendKeyCode(KeyCode keycode, ResponseListener<Object> listener) {
        switch (keycode) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
                sendSpecialKey(String.valueOf(keycode.getCode()), listener);
                break;
            case DASH:
                sendSpecialKey("DASH", listener);
                break;
            case ENTER:
                sendSpecialKey("ENTER", listener);
                break;
            default:
                Util.postError(listener, new ServiceCommandError(0, "The keycode is not available", null));
        }
    }

    @Override
    public void sendEnter() {
        WebOSTVKeyboardInput webOSTVKeyboardInput = this.keyboardInput;
        if (webOSTVKeyboardInput != null) {
            webOSTVKeyboardInput.sendEnter();
        }
    }

    @Override
    public void sendDelete() {
        WebOSTVKeyboardInput webOSTVKeyboardInput = this.keyboardInput;
        if (webOSTVKeyboardInput != null) {
            webOSTVKeyboardInput.sendDel();
        }
    }

    @Override
    public CapabilityPriorityLevel getKeyControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    private void sendSpecialKey(final String key, final ResponseListener<Object> listener) {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection != null) {
            webOSTVMouseSocketConnection.button(key);
            Util.postSuccess(listener, null);
            return;
        }
        connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
            @Override
            public void onConnected() {
                WebOSTVService.this.mouseSocket.button(key);
                Util.postSuccess(listener, null);
            }
        });
    }

    @Override
    public void up(ResponseListener<Object> listener) {
        sendSpecialKey("UP", listener);
    }

    @Override
    public void down(ResponseListener<Object> listener) {
        sendSpecialKey("DOWN", listener);
    }

    @Override
    public void left(ResponseListener<Object> listener) {
        sendSpecialKey("LEFT", listener);
    }

    @Override
    public void right(ResponseListener<Object> listener) {
        sendSpecialKey("RIGHT", listener);
    }

    @Override
    public void ok(final ResponseListener<Object> listener) {
        WebOSTVMouseSocketConnection webOSTVMouseSocketConnection = this.mouseSocket;
        if (webOSTVMouseSocketConnection != null) {
            webOSTVMouseSocketConnection.click();
            Util.postSuccess(listener, null);
            return;
        }
        connectMouse(new WebOSTVMouseSocketConnection.WebOSTVMouseSocketListener() {
            @Override
            public void onConnected() {
                WebOSTVService.this.mouseSocket.click();
                Util.postSuccess(listener, null);
            }
        });
    }

    @Override
    public void back(ResponseListener<Object> listener) {
        sendSpecialKey("BACK", listener);
    }

    @Override
    public void home(ResponseListener<Object> listener) {
        sendSpecialKey("HOME", listener);
    }

    @Override
    public CapabilityPriorityLevel getWebAppLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void launchWebApp(final String webAppId, final WebAppSession.LaunchListener listener) {
        launchWebApp(webAppId, null, true, listener);
    }

    @Override
    public void launchWebApp(String webAppId, boolean relaunchIfRunning, WebAppSession.LaunchListener listener) {
        launchWebApp(webAppId, null, relaunchIfRunning, listener);
    }

    @Override
    public void launchWebApp(final String webAppId, final JSONObject params, final WebAppSession.LaunchListener listener) {
        if (webAppId == null || webAppId.length() == 0) {
            Util.postError(listener, new ServiceCommandError(-1, "You need to provide a valid webAppId.", null));
            return;
        }
        final WebOSWebAppSession webOSWebAppSession = this.mWebAppSessions.get(webAppId);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("webAppId", webAppId);
            if (params != null) {
                jSONObject.put("urlParams", params);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, "ssap://webapp/launchWebApp", jSONObject, true, new ResponseListener<Object>() {

            @Override
            public void onSuccess(final Object response) {
                LaunchSession launchSessionForAppId;
                JSONObject jSONObject2 = (JSONObject) response;
                WebOSWebAppSession webOSWebAppSession2 = webOSWebAppSession;
                if (webOSWebAppSession2 != null) {
                    launchSessionForAppId = webOSWebAppSession2.launchSession;
                } else {
                    launchSessionForAppId = LaunchSession.launchSessionForAppId(webAppId);
                    webOSWebAppSession2 = new WebOSWebAppSession(launchSessionForAppId, WebOSTVService.this);
                    WebOSTVService.this.mWebAppSessions.put(webAppId, webOSWebAppSession2);
                }
                launchSessionForAppId.setService(WebOSTVService.this);
                launchSessionForAppId.setSessionId(jSONObject2.optString("sessionId"));
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
                launchSessionForAppId.setRawData(jSONObject2);
                Util.postSuccess(listener, webOSWebAppSession2);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public void launchWebApp(final String webAppId, final JSONObject params, boolean relaunchIfRunning, final WebAppSession.LaunchListener listener) {
        if (webAppId == null) {
            Util.postError(listener, new ServiceCommandError(0, "Must pass a web App id", null));
        } else if (relaunchIfRunning) {
            launchWebApp(webAppId, params, listener);
        } else {
            getLauncher().getRunningApp(new AppInfoListener() {
                @Override
                public void onError(ServiceCommandError error) {
                    Util.postError(listener, error);
                }

                @Override
                public void onSuccess(AppInfo appInfo) {
                    if (appInfo.getId().indexOf(webAppId) != -1) {
                        LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(webAppId);
                        launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
                        launchSessionForAppId.setService(WebOSTVService.this);
                        launchSessionForAppId.setRawData(appInfo.getRawData());
                        Util.postSuccess(listener, WebOSTVService.this.webAppSessionForLaunchSession(launchSessionForAppId));
                        return;
                    }
                    WebOSTVService.this.launchWebApp(webAppId, params, listener);
                }
            });
        }
    }

    @Override
    public void closeWebApp(LaunchSession launchSession, final ResponseListener<Object> listener) {
        if (launchSession == null || launchSession.getAppId() == null || launchSession.getAppId().length() == 0) {
            Util.postError(listener, new ServiceCommandError(0, "Must provide a valid launch session", null));
            return;
        }
        WebOSWebAppSession webOSWebAppSession = this.mWebAppSessions.get(launchSession.getAppId());
        if (webOSWebAppSession != null) {
            webOSWebAppSession.disconnectFromWebApp();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (launchSession.getAppId() != null) {
                jSONObject.put("webAppId", launchSession.getAppId());
            }
            if (launchSession.getSessionId() != null) {
                jSONObject.put("sessionId", launchSession.getSessionId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, CLOSE_WEBAPP_URI, jSONObject, true, listener).send();
    }

    public void connectToWebApp(final WebOSWebAppSession webAppSession, final boolean joinOnly, final ResponseListener<Object> connectionListener) {
        if (this.mWebAppSessions == null) {
            this.mWebAppSessions = new ConcurrentHashMap<>();
        }
        if (this.mAppToAppIdMappings == null) {
            this.mAppToAppIdMappings = new ConcurrentHashMap<>();
        }
        if (webAppSession == null || webAppSession.launchSession == null) {
            Util.postError(connectionListener, new ServiceCommandError(0, "You must provide a valid LaunchSession object", null));
            return;
        }
        final String appId = webAppSession.launchSession.getAppId();
        String str = webAppSession.launchSession.getSessionType() == LaunchSession.LaunchSessionType.WebApp ? "webAppId" : "appId";
        if (appId == null || appId.length() == 0) {
            Util.postError(connectionListener, new ServiceCommandError(-1, "You must provide a valid web app session", null));
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, appId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webAppSession.appToAppSubscription = new URLServiceSubscription<>(webAppSession.socket, "ssap://webapp/connectToApp", jSONObject, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(final Object response) {
                JSONObject jSONObject2 = (JSONObject) response;
                String optString = jSONObject2.optString("state");
                if (!optString.equalsIgnoreCase("CONNECTED")) {
                    if (joinOnly && optString.equalsIgnoreCase("WAITING_FOR_APP")) {
                        Util.postError(connectionListener, new ServiceCommandError(0, "Web app is not currently running", null));
                        return;
                    }
                    return;
                }
                String optString2 = jSONObject2.optString("appId");
                if (optString2 != null && optString2.length() != 0) {
                    if (webAppSession.launchSession.getSessionType() == LaunchSession.LaunchSessionType.WebApp) {
                        WebOSTVService.this.mAppToAppIdMappings.put(optString2, appId);
                    }
                    webAppSession.setFullAppId(optString2);
                }
                if (connectionListener != null) {
                    Util.runOnUI(new Runnable() {
                        @Override
                        public void run() {
                            connectionListener.onSuccess(response);
                        }
                    });
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                webAppSession.disconnectFromWebApp();
                if ((error == null || error.getPayload() == null) ? false : error.getPayload().toString().contains("app channel closed")) {
                    if (webAppSession.getWebAppSessionListener() != null) {
                        Util.runOnUI(new Runnable() {
                            @Override
                            public void run() {
                                webAppSession.getWebAppSessionListener().onWebAppSessionDisconnect(webAppSession);
                            }
                        });
                        return;
                    }
                    return;
                }
                Util.postError(connectionListener, error);
            }
        });
        webAppSession.appToAppSubscription.subscribe();
    }

    public void notifyPairingRequired() {
        if (this.listener != null) {
            this.listener.onPairingRequired(this, this.pairingType, null);
        }
    }

    @Override
    public void pinWebApp(String webAppId, final ResponseListener<Object> listener) {
        if (webAppId == null || webAppId.length() == 0) {
            if (listener != null) {
                listener.onError(new ServiceCommandError(-1, "You must provide a valid web app id", null));
                return;
            }
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("webAppId", webAppId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new URLServiceSubscription(this, "ssap://webapp/pinWebApp", jSONObject, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(final Object response) {
                if (((JSONObject) response).has("pairingType")) {
                    WebOSTVService.this.notifyPairingRequired();
                    return;
                }
                ResponseListener responseListener = listener;
                if (responseListener != null) {
                    responseListener.onSuccess(response);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public void unPinWebApp(String webAppId, final ResponseListener<Object> listener) {
        if (webAppId == null || webAppId.length() == 0) {
            if (listener != null) {
                listener.onError(new ServiceCommandError(-1, "You must provide a valid web app id", null));
                return;
            }
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("webAppId", webAppId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new URLServiceSubscription(this, "ssap://webapp/removePinnedWebApp", jSONObject, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(final Object response) {
                if (((JSONObject) response).has("pairingType")) {
                    WebOSTVService.this.notifyPairingRequired();
                    return;
                }
                ResponseListener responseListener = listener;
                if (responseListener != null) {
                    responseListener.onSuccess(response);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    private ServiceCommand<WebAppSession.WebAppPinStatusListener> isWebAppPinned(boolean isSubscription, String webAppId, final WebAppSession.WebAppPinStatusListener listener) {
        ServiceCommand<WebAppSession.WebAppPinStatusListener> serviceCommand;
        if (webAppId == null || webAppId.length() == 0) {
            if (listener != null) {
                listener.onError(new ServiceCommandError(-1, "You must provide a valid web app id", null));
            }
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("webAppId", webAppId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(final Object response) {
                boolean optBoolean = ((JSONObject) response).optBoolean("pinned");
                WebAppSession.WebAppPinStatusListener webAppPinStatusListener = listener;
                if (webAppPinStatusListener != null) {
                    webAppPinStatusListener.onSuccess(Boolean.valueOf(optBoolean));
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, "ssap://webapp/isWebAppPinned", jSONObject, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, "ssap://webapp/isWebAppPinned", jSONObject, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    @Override
    public void isWebAppPinned(String webAppId, WebAppSession.WebAppPinStatusListener listener) {
        isWebAppPinned(false, webAppId, listener);
    }

    @Override
    public ServiceSubscription<WebAppSession.WebAppPinStatusListener> subscribeIsWebAppPinned(String webAppId, WebAppSession.WebAppPinStatusListener listener) {
        return (URLServiceSubscription) isWebAppPinned(true, webAppId, listener);
    }

    public void joinApp(String appId, WebAppSession.LaunchListener listener) {
        LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(appId);
        launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
        launchSessionForAppId.setService(this);
        joinWebApp(launchSessionForAppId, listener);
    }

    public void connectToApp(String appId, final WebAppSession.LaunchListener listener) {
        LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(appId);
        launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
        launchSessionForAppId.setService(this);
        final WebOSWebAppSession webAppSessionForLaunchSession = webAppSessionForLaunchSession(launchSessionForAppId);
        connectToWebApp(webAppSessionForLaunchSession, false, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(Object object) {
                Util.postSuccess(listener, webAppSessionForLaunchSession);
            }
        });
    }

    @Override
    public void joinWebApp(final LaunchSession webAppLaunchSession, final WebAppSession.LaunchListener listener) {
        final WebOSWebAppSession webAppSession = this.webAppSessionForLaunchSession(webAppLaunchSession);

        webAppSession.join(new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(Object object) {
                Util.postSuccess(listener, webAppSession);
            }
        });
    }


    @Override
    public void joinWebApp(String webAppId, WebAppSession.LaunchListener listener) {
        LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(webAppId);
        launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
        launchSessionForAppId.setService(this);
        joinWebApp(launchSessionForAppId, listener);
    }

    public WebOSWebAppSession webAppSessionForLaunchSession(LaunchSession launchSession) {
        if (this.mWebAppSessions == null) {
            this.mWebAppSessions = new ConcurrentHashMap<>();
        }
        if (launchSession.getService() == null) {
            launchSession.setService(this);
        }
        WebOSWebAppSession webOSWebAppSession = this.mWebAppSessions.get(launchSession.getAppId());
        if (webOSWebAppSession == null) {
            WebOSWebAppSession webOSWebAppSession2 = new WebOSWebAppSession(launchSession, this);
            this.mWebAppSessions.put(launchSession.getAppId(), webOSWebAppSession2);
            return webOSWebAppSession2;
        }
        return webOSWebAppSession;
    }

    private void sendMessage(Object message, LaunchSession launchSession, ResponseListener<Object> listener) {
        if (launchSession == null || launchSession.getAppId() == null) {
            Util.postError(listener, new ServiceCommandError(0, "Must provide a valid LaunchSession object", null));
        } else if (message == null) {
            Util.postError(listener, new ServiceCommandError(0, "Cannot send a null message", null));
        } else {
            if (this.socket == null) {
                connect();
            }
            String appId = launchSession.getAppId();
            if (launchSession.getSessionType() == LaunchSession.LaunchSessionType.WebApp) {
                appId = this.mAppToAppIdMappings.get(appId);
            }
            if (appId == null || appId.length() == 0) {
                Util.postError(listener, new ServiceCommandError(-1, "You must provide a valid LaunchSession to send messages to", null));
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "p2p");
                jSONObject.put("to", appId);
                jSONObject.put("payload", message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendCommand(new ServiceCommand<>(this, null, jSONObject, true, listener));
        }
    }

    public void sendMessage(String message, LaunchSession launchSession, ResponseListener<Object> listener) {
        if (message != null && message.length() > 0) {
            sendMessage((Object) message, launchSession, listener);
        } else {
            Util.postError(listener, new ServiceCommandError(0, "Cannot send a null message", null));
        }
    }

    public void sendMessage(JSONObject message, LaunchSession launchSession, ResponseListener<Object> listener) {
        if (message != null && message.length() > 0) {
            sendMessage((Object) message, launchSession, listener);
        } else {
            Util.postError(listener, new ServiceCommandError(0, "Cannot send a null message", null));
        }
    }

    public void getServiceInfo(final ServiceInfoListener listener) {
        new ServiceCommand(this, "ssap://api/getServiceList", null, true, new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, (JSONArray) ((JSONObject) response).get("services"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    public void getSystemInfo(final SystemInfoListener listener) {
        new ServiceCommand(this, "ssap://system/getSystemInfo", null, true, new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, (JSONObject) ((JSONObject) response).get("features"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    public void secureAccessTest(final SecureAccessTestListener listener) {
        new ServiceCommand(this, "ssap://com.webos.service.secondscreen.gateway/test/secure", null, true, new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, Boolean.valueOf(((Boolean) ((JSONObject) response).get("returnValue")).booleanValue()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    public void getACRAuthToken(final ACRAuthTokenListener listener) {
        String uri = "ssap://tv/getACRAuthToken";

        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {

                try {
                    JSONObject jsonObj = (JSONObject) response;
                    String authToken = (String) jsonObj.get("token");
                    Util.postSuccess(listener, authToken);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, uri, null, true, responseListener);
        request.send();
    }

    public void getLaunchPoints(final LaunchPointsListener listener) {
        String uri = "ssap://com.webos.applicationManager/listLaunchPoints";

        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {

                try {
                    JSONObject jsonObj = (JSONObject) response;
                    JSONArray launchPoints = (JSONArray) jsonObj.get("launchPoints");
                    Util.postSuccess(listener, launchPoints);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, uri, null, true, responseListener);
        request.send();
    }

    @Override
    public CapabilityPriorityLevel getPlaylistControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void jumpToTrack(long index, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void setPlayMode(PlayMode playMode, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void sendCommand(ServiceCommand<?> command) {
        WebOSTVServiceSocketClient webOSTVServiceSocketClient = this.socket;
        if (webOSTVServiceSocketClient != null) {
            webOSTVServiceSocketClient.sendCommand(command);
        }
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
        WebOSTVServiceSocketClient webOSTVServiceSocketClient = this.socket;
        if (webOSTVServiceSocketClient != null) {
            webOSTVServiceSocketClient.unsubscribe(subscription);
        }
    }

    @Override
    protected void updateCapabilities() {
        ArrayList arrayList = new ArrayList<>();
        Collections.addAll(arrayList, VolumeControl.Capabilities);
        Collections.addAll(arrayList, MediaPlayer.Capabilities);
        if (DiscoveryManager.getInstance().getPairingLevel().compareTo(DiscoveryManager.PairingLevel.PROTECTED) >= 0) {
            Collections.addAll(arrayList, TextInputControl.Capabilities);
            Collections.addAll(arrayList, MouseControl.Capabilities);
            Collections.addAll(arrayList, KeyControl.Capabilities);
            Collections.addAll(arrayList, MediaPlayer.Capabilities);
            Collections.addAll(arrayList, Launcher.Capabilities);
            Collections.addAll(arrayList, TVControl.Capabilities);
            Collections.addAll(arrayList, ExternalInputControl.Capabilities);
            Collections.addAll(arrayList, ToastControl.Capabilities);
            arrayList.add(PowerControl.Off);
        } else {
            arrayList.add(Launcher.Application);
            arrayList.add(Launcher.Application_Params);
            arrayList.add(Launcher.Application_Close);
            arrayList.add(Launcher.Browser);
            arrayList.add(Launcher.Browser_Params);
            arrayList.add(Launcher.Hulu);
            arrayList.add(Launcher.Netflix);
            arrayList.add(Launcher.Netflix_Params);
            arrayList.add(Launcher.YouTube);
            arrayList.add(Launcher.YouTube_Params);
            arrayList.add(Launcher.AppStore);
            arrayList.add(Launcher.AppStore_Params);
            arrayList.add(Launcher.AppState);
            arrayList.add(Launcher.AppState_Subscribe);
        }
        if (this.serviceDescription != null) {
            if (this.serviceDescription.getVersion() != null && (this.serviceDescription.getVersion().contains("4.0.0") || this.serviceDescription.getVersion().contains("4.0.1"))) {
                arrayList.add(WebAppLauncher.Launch);
                arrayList.add(WebAppLauncher.Launch_Params);
                arrayList.add(MediaControl.Play);
                arrayList.add(MediaControl.Pause);
                arrayList.add(MediaControl.Stop);
                arrayList.add(MediaControl.Seek);
                arrayList.add(MediaControl.Position);
                arrayList.add(MediaControl.Duration);
                arrayList.add(MediaControl.PlayState);
                arrayList.add(WebAppLauncher.Close);
                if (getDLNAService() != null) {
                    arrayList.add(MediaPlayer.Subtitle_SRT);
                }
            } else {
                Collections.addAll(arrayList, WebAppLauncher.Capabilities);
                Collections.addAll(arrayList, MediaControl.Capabilities);
                arrayList.add(MediaPlayer.Subtitle_WebVTT);
                arrayList.add(PlaylistControl.JumpToTrack);
                arrayList.add(PlaylistControl.Next);
                arrayList.add(PlaylistControl.Previous);
                arrayList.add(MediaPlayer.Loop);
            }
            String locationXML = this.serviceDescription.getLocationXML();
            String findElement = locationXML != null ? XmlUtil.findElement(locationXML, "appCasting") : null;
            String findElement2 = locationXML != null ? XmlUtil.findElement(locationXML, "supportAppcastingFeatures") : null;
            if (findElement2 != null) {
                if (findElement2.contains("mirroring")) {
                    arrayList.add(ScreenMirroringControl.ScreenMirroring);
                }
                if (findElement2.contains("remote-camera")) {
                    arrayList.add(RemoteCameraControl.RemoteCamera);
                }
            } else if (findElement != null && "support".equals(findElement)) {
                arrayList.add(ScreenMirroringControl.ScreenMirroring);
            }
        }
        setCapabilities(arrayList);
    }

    public List<String> getPermissions() {
        List<String> list = this.permissions;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList<>();
        Collections.addAll(arrayList, kWebOSTVServiceOpenPermissions);
        if (DiscoveryManager.getInstance().getPairingLevel() == DiscoveryManager.PairingLevel.PROTECTED) {
            Collections.addAll(arrayList, kWebOSTVServiceProtectedPermissions);
        } else if (DiscoveryManager.getInstance().getPairingLevel() == DiscoveryManager.PairingLevel.ON) {
            Collections.addAll(arrayList, kWebOSTVServiceProtectedPermissions);
            Collections.addAll(arrayList, kWebOSTVServicePersonalActivityPermissions);
        }
        this.permissions = arrayList;
        return arrayList;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
        WebOSTVServiceConfig webOSTVServiceConfig = (WebOSTVServiceConfig) this.serviceConfig;
        if (webOSTVServiceConfig.getClientKey() != null) {
            webOSTVServiceConfig.setClientKey(null);
            if (isConnected()) {
                Log.w(Util.T, "Permissions changed -- you will need to re-pair to the TV.");
                disconnect();
            }
        }
    }

    public List<ExternalInputInfo> externalnputInfoFromJSONArray(JSONArray inputList) {
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < inputList.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) inputList.get(i);
                String string = jSONObject.getString("id");
                String string2 = jSONObject.getString("label");
                boolean z = jSONObject.getBoolean("connected");
                String string3 = jSONObject.getString("icon");
                ExternalInputInfo externalInputInfo = new ExternalInputInfo();
                externalInputInfo.setRawData(jSONObject);
                externalInputInfo.setId(string);
                externalInputInfo.setName(string2);
                externalInputInfo.setConnected(z);
                externalInputInfo.setIconURL(string3);
                arrayList.add(externalInputInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override
    public void getPlayState(PlayStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public void sendPairingKey(String pairingKey) {
        WebOSTVServiceSocketClient webOSTVServiceSocketClient = this.socket;
        if (webOSTVServiceSocketClient != null) {
            webOSTVServiceSocketClient.sendPairingKey(pairingKey);
        }
    }

    @Override
    public void startScreenMirroring(Context context, Intent projectionData, ScreenMirroringStartListener startListener) {
        ScreenMirroringApi.getInstance().startMirroring(context, projectionData, getServiceDescription().getIpAddress(), null, startListener);
    }

    @Override
    public void startScreenMirroring(Context context, Intent projectionData, Class secondScreenClass, ScreenMirroringStartListener startListener) {
        ScreenMirroringApi.getInstance().startMirroring(context, projectionData, getServiceDescription().getIpAddress(), secondScreenClass, startListener);
    }

    @Override
    public void stopScreenMirroring(Context context, ScreenMirroringStopListener stopListener) {
        ScreenMirroringApi.getInstance().stopMirroring(context, stopListener);
    }

    @Override
    public void setErrorListener(Context context, final ScreenMirroringErrorListener errorListener) {
        ScreenMirroringApi.getInstance().setErrorListener(context, new ScreenMirroringErrorListener() {
            @Override
            public void onError(ScreenMirroringError screenMirroringError) {
                errorListener.onError(screenMirroringError);
            }
        });
    }

    @Override
    public void startRemoteCamera(Context context, Surface previewSurface, boolean micMute, int lensFacing, RemoteCameraStartListener startListener) {
        RemoteCameraApi.getInstance().startRemoteCamera(context, previewSurface, getServiceDescription().getIpAddress(), micMute, lensFacing, startListener);
    }

    @Override
    public void stopRemoteCamera(Context context, RemoteCameraStopListener stopListener) {
        RemoteCameraApi.getInstance().stopRemoteCamera(context, stopListener);
    }

    @Override
    public void setMicMute(Context context, boolean micMute) {
        RemoteCameraApi.getInstance().setMicMute(context, micMute);
    }

    @Override
    public void setLensFacing(Context context, int lensFacing) {
        RemoteCameraApi.getInstance().setLensFacing(context, lensFacing);
    }

    @Override
    public void setCameraPlayingListener(Context context, RemoteCameraPlayingListener playingListener) {
        RemoteCameraApi.getInstance().setCameraPlayingListener(context, playingListener);
    }

    @Override
    public void setPropertyChangeListener(Context context, RemoteCameraPropertyChangeListener propertyChangeListener) {
        RemoteCameraApi.getInstance().setPropertyChangeListener(context, propertyChangeListener);
    }

    @Override
    public void setErrorListener(Context context, RemoteCameraErrorListener errorListener) {
        RemoteCameraApi.getInstance().setErrorListener(context, errorListener);
    }
}
