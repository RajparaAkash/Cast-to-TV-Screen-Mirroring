package com.connectsdk.service.webos.lgcast.screenmirroring.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;


public class MirroringServiceEvent {
    private ContentObserver mAccessibilitySettingObserver;
    private Context mContext;
    private BroadcastReceiver mScreenOnOffReceiver;

    
    public interface AccessibilitySettingListener {
        void onAccessibilitySettingChanged(boolean uibcEnabled);
    }

    
    public interface ScreenOnOffListener {
        void onScreenOnOffChanged(boolean turnOn);
    }

    public MirroringServiceEvent(Context context) {
        if (context == null) {
            throw new IllegalStateException("Invalid context");
        }
        this.mContext = context;
    }

    public void startScreenOnOffReceiver(final ScreenOnOffListener listener) {
        this.mScreenOnOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context contex, Intent intent) {
                boolean z = false;
                if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                    z = true;
                } else {
                    intent.getAction().equals("android.intent.action.SCREEN_OFF");
                }
                ScreenOnOffListener screenOnOffListener = listener;
                if (screenOnOffListener != null) {
                    screenOnOffListener.onScreenOnOffChanged(z);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.mContext.registerReceiver(this.mScreenOnOffReceiver, intentFilter);
    }

    public void startAccessibilitySettingObserver(final AccessibilitySettingListener listener) {
        this.mAccessibilitySettingObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            @Override
            public void onChange(boolean selfChange) {
                boolean isUibcEnabled = MirroringServiceFunc.isUibcEnabled(MirroringServiceEvent.this.mContext);
                AccessibilitySettingListener accessibilitySettingListener = listener;
                if (accessibilitySettingListener != null) {
                    accessibilitySettingListener.onAccessibilitySettingChanged(isUibcEnabled);
                }
            }
        };
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("enabled_accessibility_services"), false, this.mAccessibilitySettingObserver);
    }

    public void quit() {
        BroadcastReceiver broadcastReceiver = this.mScreenOnOffReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
        this.mScreenOnOffReceiver = null;
        if (this.mAccessibilitySettingObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mAccessibilitySettingObserver);
        }
        this.mAccessibilitySettingObserver = null;
    }
}
