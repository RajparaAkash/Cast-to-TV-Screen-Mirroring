package com.connectsdk.service.webos.lgcast.screenmirroring.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;

import java.util.concurrent.atomic.AtomicBoolean;


public class MirroringVolume {
    private final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private AudioManager mAudioManager;
    private BroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private int mPrevVolume;
    private AtomicBoolean mStarted;

    public MirroringVolume(Context context) {
        this.mContext = context;
        if (context == null) {
            throw new IllegalArgumentException("Invalid context");
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.mAudioManager = audioManager;
        if (audioManager == null) {
            throw new IllegalArgumentException("Invalid AudioManager");
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.mStarted = atomicBoolean;
        atomicBoolean.set(false);
    }

    public void startMute() {
        Logger.print("startMute", new Object[0]);
        this.mPrevVolume = getStreamVolume();
        if (getStreamVolume() > 0) {
            setStreamVolume(0);
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (MirroringVolume.this.mStarted.get() && MirroringVolume.this.getStreamVolume() > 0) {
                    Logger.debug("set mute", new Object[0]);
                    MirroringVolume.this.setStreamVolume(0);
                }
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        this.mContext.registerReceiver(broadcastReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        this.mStarted.set(true);
    }

    public void stopMute() {
        Logger.print("stopMute", new Object[0]);
        this.mStarted.set(false);
        setStreamVolume(this.mPrevVolume);
        BroadcastReceiver broadcastReceiver = this.mBroadcastReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
    }


    public int getStreamVolume() {
        return this.mAudioManager.getStreamVolume(3);
    }


    public void setStreamVolume(int volume) {
        this.mAudioManager.setStreamVolume(3, volume, 1);
    }
}
