package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.database.DeviceDatabase;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;

public class PreferenceUtils {
    private PreferenceUtils() {
    }

    public static void setHost(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(DeviceDatabase.HOST, str);
        edit.commit();
    }

    public static String getHost(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(DeviceDatabase.HOST, "");
    }

    public static void setConnectedDevice(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(DeviceDatabase.SERIAL_NUMBER, str);
        edit.commit();
    }

    public static Device getConnectedDevice(Context context) throws Exception {
        Device device = DBUtils.getDevice(context, PreferenceManager.getDefaultSharedPreferences(context).getString(DeviceDatabase.SERIAL_NUMBER, null));
        if (device != null) {
            return device;
        }
        throw new Exception("Device not connected");
    }

    public static boolean shouldProvideHapticFeedback(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("haptic_feedback_preference", false);
    }
}
