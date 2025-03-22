package com.connectsdk.service;

import android.text.TextUtils;
import android.util.Log;

import com.connectsdk.core.AppInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.etc.helper.HttpConnection;
import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.KeyControl;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.TextInputControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.NotSupportedServiceSubscription;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.roku.RokuApplicationListParser;
import com.connectsdk.service.sessions.LaunchSession;
import com.google.android.gms.actions.SearchIntents;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RokuService extends DeviceService implements Launcher, MediaPlayer, MediaControl, KeyControl, TextInputControl {

    public static final String ID = "Roku";
    private static List<String> registeredApps;
    DIALService dialService;

    @Override
    public KeyControl getKeyControl() {
        return this;
    }

    @Override
    public Launcher getLauncher() {
        return this;
    }

    @Override
    public MediaControl getMediaControl() {
        return this;
    }

    @Override
    public MediaPlayer getMediaPlayer() {
        return this;
    }

    @Override
    public TextInputControl getTextInputControl() {
        return this;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
    }

    static {
        registeredApps = new ArrayList<>();
        registeredApps.add("YouTube");
        registeredApps.add("Netflix");
        registeredApps.add("Amazon");
    }

    public static void registerApp(String appId) {
        if (registeredApps.contains(appId)) {
            return;
        }
        registeredApps.add(appId);
    }

    public RokuService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
    }

    @Override
    public void setServiceDescription(ServiceDescription serviceDescription) {
        super.setServiceDescription(serviceDescription);
        if (this.serviceDescription != null) {
            this.serviceDescription.setPort(8060);
        }
        probeForAppSupport();
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "roku:ecp");
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
        if (clazz.equals(TextInputControl.class)) {
            return getTextInputControlCapabilityLevel();
        }
        if (clazz.equals(KeyControl.class)) {
            return getKeyControlCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override
    public CapabilityPriorityLevel getLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }


    class RokuLaunchSession extends LaunchSession {
        RokuLaunchSession() {
        }

        @Override
        public void close(ResponseListener<Object> responseListener) {
            RokuService.this.home(responseListener);
        }
    }

    @Override
    public void launchApp(String appId, AppLaunchListener listener) {
        if (appId == null) {
            Util.postError(listener, new ServiceCommandError(0, "Must supply a valid app id", null));
            return;
        }
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
        String str;
        String str2;
        String str3;
        if (appInfo == null || appInfo.getId() == null) {
            Util.postError(listener, new ServiceCommandError(-1, "Cannot launch app without valid AppInfo object", appInfo));
            return;
        }
        String requestURL = requestURL("launch", appInfo.getId());
        StringBuilder str4 = new StringBuilder();
        if ((params instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) params;
            int i = 0;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    str = jSONObject.getString(next);
                } catch (JSONException exception) {
                    str = null;
                }
                if (str != null) {
                    String str5 = i == 0 ? "?" : "&";
                    try {
                        str2 = URLEncoder.encode(next, "UTF-8");
                    } catch (UnsupportedEncodingException unused2) {
                        str2 = null;
                    }
                    try {
                        str3 = URLEncoder.encode(str, "UTF-8");
                    } catch (UnsupportedEncodingException unused3) {
                        str3 = null;
                        if (str2 != null) {
                            str4.append(str5).append(str2).append("=").append(str3);
                            i++;
                        }
                    }
                    if (str2 != null && str3 != null) {
                        str4.append(str5).append(str2).append("=").append(str3);
                        i++;
                    }
                }
            }
        }
        if (str4.length() > 0) {
            requestURL = requestURL + str4;
        }
        new ServiceCommand(this, requestURL, null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                RokuLaunchSession rokuLaunchSession = new RokuLaunchSession();
                rokuLaunchSession.setService(RokuService.this);
                rokuLaunchSession.setAppId(appInfo.getId());
                rokuLaunchSession.setAppName(appInfo.getName());
                rokuLaunchSession.setSessionType(LaunchSession.LaunchSessionType.App);
                Util.postSuccess(listener, rokuLaunchSession);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public void closeApp(LaunchSession launchSession, ResponseListener<Object> listener) {
        home(listener);
    }

    @Override
    public void getAppList(final AppListListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, requestURL(SearchIntents.EXTRA_QUERY, "apps"), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                String str = (String) response;
                SAXParserFactory newInstance = SAXParserFactory.newInstance();
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                    SAXParser newSAXParser = newInstance.newSAXParser();
                    RokuApplicationListParser rokuApplicationListParser = new RokuApplicationListParser();
                    newSAXParser.parse(byteArrayInputStream, rokuApplicationListParser);
                    Util.postSuccess(listener, rokuApplicationListParser.getApplicationList());
                } catch (IOException | ParserConfigurationException | SAXException e) {
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
    public void getRunningApp(AppInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<AppInfoListener> subscribeRunningApp(AppInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return new NotSupportedServiceSubscription();
    }

    @Override
    public void getAppState(LaunchSession launchSession, AppStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<AppStateListener> subscribeAppState(LaunchSession launchSession, AppStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public void launchBrowser(String url, AppLaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void launchYouTube(String contentId, AppLaunchListener listener) {
        launchYouTube(contentId, 0.0f, listener);
    }

    @Override
    public void launchYouTube(String contentId, float startTime, AppLaunchListener listener) {
        if (getDIALService() != null) {
            getDIALService().getLauncher().launchYouTube(contentId, startTime, listener);
        } else {
            Util.postError(listener, new ServiceCommandError(0, "Cannot reach DIAL service for launching with provided start time", null));
        }
    }

    @Override
    public void launchNetflix(final String contentId, final AppLaunchListener listener) {
        getAppList(new AppListListener() {
            @Override
            public void onSuccess(List<AppInfo> appList) {
                for (AppInfo appInfo : appList) {
                    if (appInfo.getName().equalsIgnoreCase("Netflix")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("mediaType", "movie");
                            if (contentId != null && contentId.length() > 0) {
                                jSONObject.put("contentId", contentId);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RokuService.this.launchAppWithInfo(appInfo, jSONObject, listener);
                        return;
                    }
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void launchHulu(final String contentId, final AppLaunchListener listener) {
        getAppList(new AppListListener() {
            @Override
            public void onSuccess(List<AppInfo> appList) {
                for (AppInfo appInfo : appList) {
                    if (appInfo.getName().contains("Hulu")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("contentId", contentId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RokuService.this.launchAppWithInfo(appInfo, jSONObject, listener);
                        return;
                    }
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void launchAppStore(final String appId, AppLaunchListener listener) {
        AppInfo appInfo = new AppInfo("11");
        appInfo.setName("Channel Store");

        JSONObject params = null;
        try {
            params = new JSONObject() {
                {
                    put("contentId", appId);
                }
            };
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        launchAppWithInfo(appInfo, params, listener);
    }

    @Override
    public CapabilityPriorityLevel getKeyControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void up(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Up"), null, listener).send();
    }

    @Override
    public void down(final ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Down"), null, listener).send();
    }

    @Override
    public void left(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Left"), null, listener).send();
    }

    @Override
    public void right(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Right"), null, listener).send();
    }

    @Override
    public void ok(final ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Select"), null, listener).send();
    }

    @Override
    public void back(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Back"), null, listener).send();
    }

    @Override
    public void home(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Home"), null, listener).send();
    }

    @Override
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void play(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Play"), null, listener).send();
    }

    @Override
    public void pause(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Play"), null, listener).send();
    }

    @Override
    public void stop(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL(null, "input?a=sto"), null, listener).send();
    }

    @Override
    public void rewind(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Rev"), null, listener).send();
    }

    @Override
    public void fastForward(ResponseListener<Object> listener) {
        new ServiceCommand(this, requestURL("keypress", "Fwd"), null, listener).send();
    }

    @Override
    public void previous(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void next(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void getDuration(DurationListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void getPosition(PositionListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void seek(long position, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
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

    private void displayMedia(String url, String mimeType, String title, String description, String iconSrc, final LaunchListener listener) {
        String format;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                RokuLaunchSession rokuLaunchSession = new RokuLaunchSession();
                rokuLaunchSession.setService(RokuService.this);
                rokuLaunchSession.setSessionType(LaunchSession.LaunchSessionType.Media);
                Util.postSuccess(listener, new MediaLaunchObject(rokuLaunchSession, RokuService.this));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        String substring = mimeType.contains("/") ? mimeType.substring(mimeType.indexOf("/") + 1) : mimeType;
        if (mimeType.contains("image")) {
            format = String.format("15985?t=p&u=%s&tr=crossfade", HttpMessage.encode(url));
        } else {
            if (mimeType.contains("video")) {
                Object[] objArr = new Object[3];
                objArr[0] = HttpMessage.encode(url);
                objArr[1] = TextUtils.isEmpty(title) ? "(null)" : HttpMessage.encode(title);
                objArr[2] = HttpMessage.encode(substring);
                format = String.format("15985?t=v&u=%s&k=(null)&videoName=%s&videoFormat=%s", objArr);
            } else {
                Object[] objArr2 = new Object[5];
                objArr2[0] = HttpMessage.encode(url);
                objArr2[1] = TextUtils.isEmpty(title) ? "(null)" : HttpMessage.encode(title);
                objArr2[2] = TextUtils.isEmpty(description) ? "(null)" : HttpMessage.encode(description);
                objArr2[3] = HttpMessage.encode(substring);
                objArr2[4] = TextUtils.isEmpty(iconSrc) ? "(null)" : HttpMessage.encode(iconSrc);
                format = String.format("15985?t=a&u=%s&k=(null)&songname=%s&artistname=%s&songformat=%s&albumarturl=%s", objArr2);
            }
        }
        new ServiceCommand(this, requestURL("input", format), null, responseListener).send();
    }

    @Override
    public void displayImage(String url, String mimeType, String title, String description, String iconSrc, LaunchListener listener) {
        displayMedia(url, mimeType, title, description, iconSrc, listener);
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
        displayMedia(url, mimeType, title, description, iconSrc, listener);
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
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
        playMedia(str, str2, str3, str4, str5, shouldLoop, listener);
    }

    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        home(listener);
    }

    @Override
    public CapabilityPriorityLevel getTextInputControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public ServiceSubscription<TextInputStatusListener> subscribeTextInputStatus(TextInputStatusListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return new NotSupportedServiceSubscription();
    }

    @Override
    public void sendText(String input) {
        String str;
        if (input == null || input.length() == 0) {
            return;
        }
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object response) {
            }
        };
        try {
            str = "Lit_" + URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        String requestURL = requestURL("keypress", str);
        Log.d(Util.T, "RokuService::send() | uri = " + requestURL);
        new ServiceCommand(this, requestURL, null, responseListener).send();
    }

    @Override
    public void sendKeyCode(KeyCode keyCode, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void sendEnter() {
        new ServiceCommand(this, requestURL("keypress", "Enter"), null, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object response) {
            }
        }).send();
    }

    @Override
    public void sendDelete() {
        new ServiceCommand(this, requestURL("keypress", "Backspace"), null, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object response) {
            }
        }).send();
    }

    @Override
    public void sendCommand(final ServiceCommand<?> mCommand) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                Object payload = mCommand.getPayload();
                try {
                    Log.d("", "RESP " + mCommand.getTarget());
                    HttpConnection newInstance = HttpConnection.newInstance(URI.create(mCommand.getTarget()));
                    if (mCommand.getHttpMethod().equalsIgnoreCase("POST")) {
                        newInstance.setMethod(HttpConnection.Method.POST);
                        if (payload != null) {
                            newInstance.setPayload(payload.toString());
                        }
                    }
                    newInstance.execute();
                    int responseCode = newInstance.getResponseCode();
                    Log.d("", "RESP " + responseCode);
                    if (responseCode != 200 && responseCode != 201) {
                        Util.postError(((ServiceCommand<?>) mCommand).getResponseListener(), ServiceCommandError.getError(responseCode));
                        return;
                    }
                    Util.postSuccess(((ServiceCommand<?>) mCommand).getResponseListener(), newInstance.getResponseString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Util.postError(((ServiceCommand<?>) mCommand).getResponseListener(), new ServiceCommandError(0, e.getMessage(), null));
                }
            }
        });
    }

    private String requestURL(String action, String parameter) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(this.serviceDescription.getIpAddress()).append(":");
        sb.append(this.serviceDescription.getPort()).append("/");
        if (action != null) {
            sb.append(action);
        }
        if (parameter != null) {
            sb.append("/").append(parameter);
        }
        return sb.toString();
    }

    private void probeForAppSupport() {
        getAppList(new AppListListener() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(List<AppInfo> object) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (String str : RokuService.registeredApps) {
                    for (AppInfo appInfo : object) {
                        if (appInfo.getName().contains(str)) {
                            arrayList.add("Launcher." + str);
                            arrayList.add("Launcher." + str + ".Params");
                        }
                    }
                }
                RokuService.this.addCapabilities(arrayList);
            }
        });
    }

    @Override
    protected void updateCapabilities() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(KeyControl.up);
        arrayList.add(KeyControl.Down);
        arrayList.add(KeyControl.Left);
        arrayList.add(KeyControl.Right);
        arrayList.add(KeyControl.OK);
        arrayList.add(KeyControl.Back);
        arrayList.add(KeyControl.Home);
        arrayList.add(KeyControl.Send_Key);
        arrayList.add(Launcher.Application);
        arrayList.add(Launcher.Application_Params);
        arrayList.add(Launcher.Application_List);
        arrayList.add(Launcher.AppStore);
        arrayList.add(Launcher.AppStore_Params);
        arrayList.add(Launcher.Application_Close);
        arrayList.add(MediaPlayer.Display_Image);
        arrayList.add("MediaPlayer.Play.Video");
        arrayList.add("MediaPlayer.Play.Audio");
        arrayList.add(MediaPlayer.Close);
        arrayList.add(MediaPlayer.MetaData_Title);
        arrayList.add(MediaControl.FastForward);
        arrayList.add(MediaControl.Rewind);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(TextInputControl.Send);
        arrayList.add(TextInputControl.Send_Delete);
        arrayList.add(TextInputControl.Send_Enter);
        setCapabilities(arrayList);
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
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public void connect() {
        this.connected = true;
        reportConnected(true);
    }

    @Override
    public void disconnect() {
        this.connected = false;
        if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (RokuService.this.listener != null) {
                    RokuService.this.listener.onDisconnect(RokuService.this, null);
                }
            }
        });
    }

    @Override
    public void onLoseReachability(DeviceServiceReachability reachability) {
        if (this.connected) {
            disconnect();
        } else if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
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
