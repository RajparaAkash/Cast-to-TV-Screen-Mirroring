package com.connectsdk.service.webos.lgcast.common.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefUtil {
    public static boolean contains(Context context, String key) {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).contains(key);
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).edit();
        edit.remove(key);
        edit.commit();
    }

    public static void set(Context context, String key, String value) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void set(Context context, String key, boolean value) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static void set(Context context, String key, int value) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void set(Context context, String key, long value) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public static String get(Context context, String key, String defaultValue) {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).getString(key, defaultValue);
    }

    public static boolean get(Context context, String key, boolean defaultValue) {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).getBoolean(key, defaultValue);
    }

    public static int get(Context context, String key, int defaultValue) {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).getInt(key, defaultValue);
    }

    public static long get(Context context, String key, long defaultValue) {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0).getLong(key, defaultValue);
    }
}
