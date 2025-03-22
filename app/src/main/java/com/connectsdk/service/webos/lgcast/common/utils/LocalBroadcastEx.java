package com.connectsdk.service.webos.lgcast.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.HashMap;


public class LocalBroadcastEx {
    private HashMap<String, BroadcastReceiver> mReceiverMap = new HashMap<>();


    public interface BroadcastListener {
        void onReceive(Intent intent);
    }

    public void register(Context context, String action, final BroadcastListener listener) {
        if (context == null || action == null) {
            return;
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                BroadcastListener broadcastListener = listener;
                if (broadcastListener != null) {
                    broadcastListener.onReceive(intent);
                }
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(action));
        unregister(context, action);
        this.mReceiverMap.put(action, broadcastReceiver);
    }

    public void registerOnce(Context context, final String action, final BroadcastListener listener) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                BroadcastListener broadcastListener = listener;
                if (broadcastListener != null) {
                    broadcastListener.onReceive(intent);
                }
                LocalBroadcastEx.this.unregister(context2, action);
            }
        };
        if (context == null || action == null) {
            return;
        }
        if (registered(action)) {
            unregister(context, action);
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(action));
        this.mReceiverMap.put(action, broadcastReceiver);
    }

    public boolean registered(String action) {
        return this.mReceiverMap.get(action) != null;
    }

    public void unregister(Context context, String action) {
        BroadcastReceiver remove;
        if (context == null || action == null || (remove = this.mReceiverMap.remove(action)) == null) {
            return;
        }
        LocalBroadcastManager.getInstance(context).unregisterReceiver(remove);
    }

    public void unregisterAll(Context context) {
        if (context == null) {
            return;
        }
        for (BroadcastReceiver broadcastReceiver : this.mReceiverMap.values()) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
        }
        this.mReceiverMap.clear();
    }
}
