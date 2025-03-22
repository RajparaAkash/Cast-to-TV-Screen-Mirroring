package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.sessions.LaunchSession;
import com.connectsdk.service.sessions.WebAppSession;

import org.json.JSONObject;


public interface WebAppLauncher extends CapabilityMethods {
    public static final String Any = "WebAppLauncher.Any";
    public static final String Launch = "WebAppLauncher.Launch";
    public static final String Launch_Params = "WebAppLauncher.Launch.Params";
    public static final String Message_Send = "WebAppLauncher.Message.Send";
    public static final String Message_Receive = "WebAppLauncher.Message.Receive";
    public static final String Message_Send_JSON = "WebAppLauncher.Message.Send.JSON";
    public static final String Message_Receive_JSON = "WebAppLauncher.Message.Receive.JSON";
    public static final String Connect = "WebAppLauncher.Connect";
    public static final String Disconnect = "WebAppLauncher.Disconnect";
    public static final String Join = "WebAppLauncher.Join";
    public static final String Close = "WebAppLauncher.Close";
    public static final String Pin = "WebAppLauncher.Pin";
    public static final String[] Capabilities = {Launch, Launch_Params, Message_Send, Message_Receive, Message_Send_JSON, Message_Receive_JSON, Connect, Disconnect, Join, Close, Pin};

    void closeWebApp(LaunchSession launchSession, ResponseListener<Object> listener);

    WebAppLauncher getWebAppLauncher();

    CapabilityPriorityLevel getWebAppLauncherCapabilityLevel();

    void isWebAppPinned(String webAppId, WebAppSession.WebAppPinStatusListener listener);

    void joinWebApp(LaunchSession webAppLaunchSession, WebAppSession.LaunchListener listener);

    void joinWebApp(String webAppId, WebAppSession.LaunchListener listener);

    void launchWebApp(String webAppId, WebAppSession.LaunchListener listener);

    void launchWebApp(String webAppId, JSONObject params, WebAppSession.LaunchListener listener);

    void launchWebApp(String webAppId, JSONObject params, boolean relaunchIfRunning, WebAppSession.LaunchListener listener);

    void launchWebApp(String webAppId, boolean relaunchIfRunning, WebAppSession.LaunchListener listener);

    void pinWebApp(String webAppId, ResponseListener<Object> listener);

    ServiceSubscription<WebAppSession.WebAppPinStatusListener> subscribeIsWebAppPinned(String webAppId, WebAppSession.WebAppPinStatusListener listener);

    void unPinWebApp(String webAppId, ResponseListener<Object> listener);
}
