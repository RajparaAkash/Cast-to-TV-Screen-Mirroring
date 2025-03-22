package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NetworkMonitor {
    private ConnectivityManager mCm;
    private WifiApManager mWifiApManager;

    public NetworkMonitor(Activity activity, int i) {
        if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_NETWORK_STATE") != 0) {
            requestPermission(activity, i);
            return;
        }
        this.mCm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.mWifiApManager = new WifiApManager(activity);
    }

    public boolean isConnectedToiWiFi() {
        ConnectivityManager connectivityManager = this.mCm;
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public boolean isMobileAccessPointOn() {
        if (mWifiApManager == null) {
            return false;
        }
        return mWifiApManager.isWifiApEnabled();
    }

    private void requestPermission(Activity activity, int i) {
        if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_NETWORK_STATE") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_NETWORK_STATE")) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_NETWORK_STATE"}, i);
        }
    }
}
