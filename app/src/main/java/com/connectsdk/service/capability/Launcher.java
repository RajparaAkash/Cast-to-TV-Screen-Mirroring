package com.connectsdk.service.capability;

import com.connectsdk.core.AppInfo;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.sessions.LaunchSession;

import java.util.List;


public interface Launcher extends CapabilityMethods {
    public static final String Any = "Launcher.Any";
    public static final String Application = "Launcher.App";
    public static final String Application_Params = "Launcher.App.Params";
    public static final String Application_Close = "Launcher.App.Close";
    public static final String Application_List = "Launcher.App.List";
    public static final String Browser = "Launcher.Browser";
    public static final String Browser_Params = "Launcher.Browser.Params";
    public static final String Hulu = "Launcher.Hulu";
    public static final String Hulu_Params = "Launcher.Hulu.Params";
    public static final String Netflix = "Launcher.Netflix";
    public static final String Netflix_Params = "Launcher.Netflix.Params";
    public static final String YouTube = "Launcher.YouTube";
    public static final String YouTube_Params = "Launcher.YouTube.Params";
    public static final String AppStore = "Launcher.AppStore";
    public static final String AppStore_Params = "Launcher.AppStore.Params";
    public static final String AppState = "Launcher.AppState";
    public static final String AppState_Subscribe = "Launcher.AppState.Subscribe";
    public static final String RunningApp = "Launcher.RunningApp";
    public static final String RunningApp_Subscribe = "Launcher.RunningApp.Subscribe";
    public static final String[] Capabilities = {Application, Application_Params, Application_Close, Application_List, Browser, Browser_Params, Hulu, Hulu_Params, Netflix, Netflix_Params, YouTube, YouTube_Params, AppStore, AppStore_Params, AppState, AppState_Subscribe, RunningApp, RunningApp_Subscribe};


    public interface AppCountListener extends ResponseListener<Integer> {
    }


    public interface AppInfoListener extends ResponseListener<AppInfo> {
    }


    public interface AppLaunchListener extends ResponseListener<LaunchSession> {
    }


    public interface AppListListener extends ResponseListener<List<AppInfo>> {
    }


    public interface AppStateListener extends ResponseListener<AppState> {
    }

    void closeApp(LaunchSession launchSession, ResponseListener<Object> listener);

    void getAppList(AppListListener listener);

    void getAppState(LaunchSession launchSession, AppStateListener listener);

    Launcher getLauncher();

    CapabilityPriorityLevel getLauncherCapabilityLevel();

    void getRunningApp(AppInfoListener listener);

    void launchApp(String appId, AppLaunchListener listener);

    void launchAppStore(String appId, AppLaunchListener listener);

    void launchAppWithInfo(AppInfo appInfo, AppLaunchListener listener);

    void launchAppWithInfo(AppInfo appInfo, Object params, AppLaunchListener listener);

    void launchBrowser(String url, AppLaunchListener listener);

    void launchHulu(String contentId, AppLaunchListener listener);

    void launchNetflix(String contentId, AppLaunchListener listener);

    void launchYouTube(String contentId, float startTime, AppLaunchListener listener);

    void launchYouTube(String contentId, AppLaunchListener listener);

    ServiceSubscription<AppStateListener> subscribeAppState(LaunchSession launchSession, AppStateListener listener);

    ServiceSubscription<AppInfoListener> subscribeRunningApp(AppInfoListener listener);


    public static class AppState {
        public boolean running;
        public boolean visible;

        public AppState(boolean running, boolean visible) {
            this.running = running;
            this.visible = visible;
        }
    }
}
