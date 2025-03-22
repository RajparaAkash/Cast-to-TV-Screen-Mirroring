package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

import android.content.Context;

import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;

public class CommandHelper {
    private CommandHelper() {
    }

    public static String getDeviceInfoURL(Context context, String str) {
        return str;
    }

    public static String getDeviceURL(Context context) {
        try {
            return PreferenceUtils.getHost(context);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getIconURL(Context context, String str) {
        try {
            return ("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060") + "/query/icon/" + str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getConnectedDeviceInfoURL(Context context, String str) {
        try {
            return PreferenceUtils.getConnectedDevice(context).getHost();
        } catch (Exception unused) {
            return "";
        }
    }
}
