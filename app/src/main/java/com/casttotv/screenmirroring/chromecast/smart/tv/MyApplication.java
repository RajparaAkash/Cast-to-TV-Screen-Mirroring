package com.casttotv.screenmirroring.chromecast.smart.tv;

import android.app.Application;
import android.content.Intent;

import com.connectsdk.DeviceConnectService;
import com.connectsdk.discovery.DiscoveryManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class MyApplication extends Application {

    public static boolean canRequestAds = false;
    public static boolean isAdShowing = false;
    private static MyApplication application;

    public String myCookie = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        try {
            DiscoveryManager.init(getApplicationContext());
            DiscoveryManager.getInstance().start();
            DeviceConnectService.enqueueWork(this, new Intent());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            FirebaseApp.initializeApp(getApplicationContext());
            FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(getApplicationContext());
            FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();

            if (BuildConfig.DEBUG) {
                analytics.setAnalyticsCollectionEnabled(false);
                crashlytics.setCrashlyticsCollectionEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyApplication getInstance() {
        if (application == null) {
            throw new IllegalStateException("Application instance is null!");
        }
        return application;
    }
}
