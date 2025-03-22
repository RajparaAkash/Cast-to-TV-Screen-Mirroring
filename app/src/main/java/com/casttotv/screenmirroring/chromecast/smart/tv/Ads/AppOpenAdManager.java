package com.casttotv.screenmirroring.chromecast.smart.tv.Ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.casttotv.screenmirroring.chromecast.smart.tv.BuildConfig;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PrefHelper;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.lang.ref.WeakReference;
import java.util.Date;

public class AppOpenAdManager implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    private static final String TAG = AppOpenAdManager.class.getSimpleName();
    private AppOpenAd appOpenAd = null;
    private long loadTime = 0;
    private WeakReference<Activity> currentActivity;
    private final MyApplication myApplication;

    public AppOpenAdManager(MyApplication myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void fetchAd() {
        if (isAdAvailable()) {
            return;
        }

        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(myApplication, PrefHelper.getInstance().getAdsData().getAppOpenTag(), request,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                        AppOpenAdManager.this.appOpenAd = appOpenAd;
                        AppOpenAdManager.this.loadTime = System.currentTimeMillis();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e(TAG, "Ad failed to load: " + loadAdError.getMessage());
                        appOpenAd = null;
                    }
                });
    }

    public void showAdIfAvailable() {
        int currentVersionCode = BuildConfig.VERSION_CODE;
        if (!MyApplication.canRequestAds || MyApplication.isAdShowing
                || PrefHelper.getInstance().getAdsData().getAppA() == 0 || PrefHelper.getInstance().getAdsData().getAppV() == currentVersionCode) {
            return;
        }

        if (!isAdAvailable() || currentActivity == null) {
            fetchAd();
            return;
        }

        Activity activity = currentActivity.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad dismissed.");
                appOpenAd = null;
                MyApplication.isAdShowing = false;
                fetchAd();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                Log.e(TAG, "Ad failed to show: " + adError.getMessage());
                appOpenAd = null;
                MyApplication.isAdShowing = false;
            }

            @Override
            public void onAdShowedFullScreenContent() {
                MyApplication.isAdShowing = true;
                Log.d(TAG, "Ad showed.");
            }
        });

        appOpenAd.show(activity);
    }

    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo();
    }

    private boolean wasLoadTimeLessThanNHoursAgo() {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * (long) 4));
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        showAdIfAvailable();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        currentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        // No action needed
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        // No action needed
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        // No action needed
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        if (currentActivity != null && currentActivity.get() == activity) {
            currentActivity.clear();
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onCreate(owner);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onPause(owner);
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);
    }
}
