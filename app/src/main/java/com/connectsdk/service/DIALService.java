package com.connectsdk.service;

import android.util.Log;

import com.connectsdk.core.AppInfo;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.etc.helper.HttpConnection;
import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.NotSupportedServiceSubscription;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class DIALService extends DeviceService implements Launcher {

    private static final String APP_NETFLIX = "Netflix";
    public static final String ID = "DIAL";
    private static List<String> registeredApps;

    @Override
    public void getAppState(LaunchSession launchSession, AppStateListener listener) {
    }

    @Override
    public Launcher getLauncher() {
        return this;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public ServiceSubscription<AppStateListener> subscribeAppState(LaunchSession launchSession, AppStateListener listener) {
        return null;
    }

    static {
        ArrayList arrayList = new ArrayList<>();
        registeredApps = arrayList;
        arrayList.add("YouTube");
        registeredApps.add(APP_NETFLIX);
        registeredApps.add("Amazon");
    }

    public static void registerApp(String appId) {
        if (registeredApps.contains(appId)) {
            return;
        }
        registeredApps.add(appId);
    }

    public DIALService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
    }

    @Override
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        if (clazz.equals(Launcher.class)) {
            return getLauncherCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "urn:dial-multiscreen-org:service:dial:1");
    }

    @Override
    public void setServiceDescription(ServiceDescription serviceDescription) {
        List<String> list;
        super.setServiceDescription(serviceDescription);
        Map<String, List<String>> responseHeaders = getServiceDescription().getResponseHeaders();
        if (responseHeaders != null && (list = responseHeaders.get("Application-URL")) != null && list.size() > 0) {
            getServiceDescription().setApplicationURL(list.get(0));
        }
        probeForAppSupport();
    }

    @Override
    public CapabilityPriorityLevel getLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.NORMAL;
    }

    @Override
    public void launchApp(String appId, AppLaunchListener listener) {
        launchApp(appId, null, listener);
    }

    private void launchApp(String appId, JSONObject params, AppLaunchListener listener) {
        if (appId == null || appId.length() == 0) {
            Util.postError(listener, new ServiceCommandError(0, "Must pass a valid appId", null));
            return;
        }
        AppInfo appInfo = new AppInfo();
        appInfo.setName(appId);
        appInfo.setId(appId);
        launchAppWithInfo(appInfo, listener);
    }

    @Override
    public void launchAppWithInfo(AppInfo appInfo, AppLaunchListener listener) {
        launchAppWithInfo(appInfo, null, listener);
    }

    @Override
    public void launchAppWithInfo(final AppInfo appInfo, Object params, final AppLaunchListener listener) {
        new ServiceCommand(getCommandProcessor(), requestURL(appInfo.getName()), params, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, new ServiceCommandError(0, "Problem Launching app", null));
            }

            @Override
            public void onSuccess(Object object) {
                LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(appInfo.getId());
                launchSessionForAppId.setAppName(appInfo.getName());
                launchSessionForAppId.setSessionId((String) object);
                launchSessionForAppId.setService(DIALService.this);
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.App);
                Util.postSuccess(listener, launchSessionForAppId);
            }
        }).send();
    }

    @Override
    public void launchBrowser(String url, AppLaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void closeApp(final LaunchSession launchSession, final ResponseListener<Object> listener) {
        getAppState(launchSession.getAppName(), new AppStateListener() {
            @Override
            public void onSuccess(Launcher.AppState state) {
                String sessionId;
                DIALService.this.requestURL(launchSession.getAppName());
                if (launchSession.getSessionId().contains("http://") || launchSession.getSessionId().contains("https://")) {
                    sessionId = launchSession.getSessionId();
                } else {
                    sessionId = (launchSession.getSessionId().endsWith("run") || launchSession.getSessionId().endsWith("run/")) ? DIALService.this.requestURL(launchSession.getAppId() + "/run") : DIALService.this.requestURL(launchSession.getSessionId());
                }
                ServiceCommand serviceCommand = new ServiceCommand(launchSession.getService(), sessionId, null, listener);
                serviceCommand.setHttpMethod("DELETE");
                serviceCommand.send();
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
    public void launchYouTube(String contentId, float startTime, AppLaunchListener listener) {
        AppInfo appInfo = new AppInfo("YouTube");
        appInfo.setName(appInfo.getId());
        String str = null;
        if (contentId != null && contentId.length() > 0) {
            if (startTime < 0.0d) {
                if (listener != null) {
                    listener.onError(new ServiceCommandError(0, "Start time may not be negative", null));
                    return;
                }
                return;
            }
            str = String.format(Locale.US, "pairingCode=%s&v=%s&t=%.1f", UUID.randomUUID().toString(), contentId, Float.valueOf(startTime));
        }
        launchAppWithInfo(appInfo, str, listener);
    }

    @Override
    public void launchHulu(String contentId, AppLaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void launchNetflix(final String contentId, AppLaunchListener listener) {
        JSONObject params = null;

        if (contentId != null && contentId.length() > 0) {
            try {
                params = new JSONObject() {{
                    put("v", contentId);
                }};
            } catch (JSONException e) {
                Log.e(Util.T, "Launch Netflix error", e);
            }
        }

        AppInfo appInfo = new AppInfo(APP_NETFLIX);
        appInfo.setName(appInfo.getId());

        launchAppWithInfo(appInfo, params, listener);
    }

    @Override
    public void launchAppStore(String appId, AppLaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    private void getAppState(String appName, final AppStateListener listener) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                String str = (String) response;
                String[] strArr = {"<state>", "</state>"};
                int indexOf = str.indexOf(strArr[0]);
                int indexOf2 = str.indexOf(strArr[1]);
                if (indexOf != -1 && indexOf2 != -1) {
                    String substring = str.substring(indexOf + strArr[0].length(), indexOf2);
                    Util.postSuccess(listener, new AppState("running".equals(substring), "running".equals(substring)));
                    return;
                }
                Util.postError(listener, new ServiceCommandError(0, "Malformed response for app state", null));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        ServiceCommand serviceCommand = new ServiceCommand(getCommandProcessor(), requestURL(appName), null, responseListener);
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public void getAppList(AppListListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
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
    public void closeLaunchSession(LaunchSession launchSession, ResponseListener<Object> listener) {
        if (launchSession.getSessionType() == LaunchSession.LaunchSessionType.App) {
            getLauncher().closeApp(launchSession, listener);
        } else {
            Util.postError(listener, new ServiceCommandError(-1, "Could not find a launcher associated with this LaunchSession", launchSession));
        }
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
                if (DIALService.this.listener != null) {
                    DIALService.this.listener.onDisconnect(DIALService.this, null);
                }
            }
        });
    }

    @Override
    public void onLoseReachability(DeviceServiceReachability reachability) {
        if (this.connected) {
            disconnect();
        } else {
            this.mServiceReachability.stop();
        }
    }

    @Override
    public void sendCommand(final ServiceCommand<?> mCommand) {
        Util.runInBackground(new Runnable() {

            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                ServiceCommand<ResponseListener<Object>> command = (ServiceCommand<ResponseListener<Object>>) mCommand;
                Object payload = command.getPayload();

                try {
                    HttpConnection connection = createHttpConnection(mCommand.getTarget());
                    if (payload != null || command.getHttpMethod().equalsIgnoreCase(ServiceCommand.TYPE_POST)) {
                        connection.setMethod(HttpConnection.Method.POST);
                        if (payload != null) {
                            connection.setHeader(HttpMessage.CONTENT_TYPE_HEADER, "text/plain; " + "charset=\"utf-8\"");
                            connection.setPayload(payload.toString());
                        }
                    } else if (command.getHttpMethod().equalsIgnoreCase(ServiceCommand.TYPE_DEL)) {
                        connection.setMethod(HttpConnection.Method.DELETE);
                    }
                    connection.execute();
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        Util.postSuccess(command.getResponseListener(), connection.getResponseString());
                    } else if (code == 201) {
                        Util.postSuccess(command.getResponseListener(), connection.getResponseHeader("Location"));
                    } else {
                        Util.postError(command.getResponseListener(), ServiceCommandError.getError(code));
                    }
                } catch (Exception e) {
                    Util.postError(command.getResponseListener(), new ServiceCommandError(0, e.getMessage(), null));
                }
            }
        });
    }

    HttpConnection createHttpConnection(String target) throws IOException {
        return HttpConnection.newInstance(URI.create(target));
    }


    public String requestURL(String appName) {
        String applicationURL = this.serviceDescription != null ? this.serviceDescription.getApplicationURL() : null;
        if (applicationURL == null) {
            throw new IllegalStateException("DIAL service application URL not available");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(applicationURL);
        if (!applicationURL.endsWith("/")) {
            sb.append("/");
        }
        sb.append(appName);
        return sb.toString();
    }

    @Override
    protected void updateCapabilities() {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(Launcher.Application);
        arrayList.add(Launcher.Application_Params);
        arrayList.add(Launcher.Application_Close);
        arrayList.add(Launcher.AppState);
        setCapabilities(arrayList);
    }

    private void hasApplication(String appID, ResponseListener<Object> listener) {
        ServiceCommand serviceCommand = new ServiceCommand(getCommandProcessor(), requestURL(appID), null, listener);
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    private void probeForAppSupport() {
        if (this.serviceDescription.getApplicationURL() == null) {
            Log.d(Util.T, "unable to check for installed app; no service application url");
            return;
        }
        for (final String str : registeredApps) {
            hasApplication(str, new ResponseListener<Object>() {
                @Override
                public void onError(ServiceCommandError error) {
                }

                @Override
                public void onSuccess(Object object) {
                    DIALService.this.addCapability("Launcher." + str);
                    DIALService.this.addCapability("Launcher." + str + ".Params");
                }
            });
        }
    }
}
