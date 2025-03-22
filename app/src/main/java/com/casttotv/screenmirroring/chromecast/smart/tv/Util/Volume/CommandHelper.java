package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

import android.content.Context;

import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;

public class CommandHelper {

    public static String getDeviceURL(Context context) {
        if (TVConnectUtils.getInstance().getConnectableDevice() == null) {
            return "";
        }
        return "http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060";
    }

    public static String getIconURL(Context context, String str) {
        return TVConnectUtils.getInstance().getConnectableDevice() == null ? "" : "http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060/query/icon/" + str;
    }
}
