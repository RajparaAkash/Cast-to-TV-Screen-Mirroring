package com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtils {
    public static final String ACCESS_TOKEN = "nodeToken";
    public static final String CURRENT_AVATAR = "avatar";
    public static final String PASSWORD = "PASSWORD";
    public static final String RIZE_SHARE_PRE = "MYHOME_SHARE_PRE";
    private static final String USERNAME = "USERNAME";

    public static void savePrefsString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(RIZE_SHARE_PRE, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String getPrefsString(Context context, String str) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getString(str, "");
    }

    public static void saveBoolPreferences(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(RIZE_SHARE_PRE, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static boolean readBoolPreferences(Context context, String str, boolean z) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getBoolean(str, z);
    }

    public static String readPreferences(Context context, String str, String str2) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getString(str, str2);
    }

    public static void removePreference(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public static void saveUsernamePassword(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(RIZE_SHARE_PRE, 0).edit();
        edit.putString(USERNAME, str);
        edit.putString(PASSWORD, str2);
        edit.apply();
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getString(USERNAME, null);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getString(ACCESS_TOKEN, null);
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, 0).getString(PASSWORD, null);
    }
}
