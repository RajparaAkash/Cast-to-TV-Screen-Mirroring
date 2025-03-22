package com.casttotv.screenmirroring.chromecast.smart.tv.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private final NetworkChangeListener listener;

    public NetworkChangeReceiver(NetworkChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
            EventBus.getDefault().post(new MessageEvent("KEY_CONNECT"));
            listener.onNetworkStatusChanged(NetworkUtils.isInternetAvailable(context));
        }
    }

    public interface NetworkChangeListener {
        void onNetworkStatusChanged(boolean isConnected);
    }
}
