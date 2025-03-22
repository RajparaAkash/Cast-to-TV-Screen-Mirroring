package com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.Remote;
import com.google.gson.Gson;

public class Common {
    public static final Common INSTANCE = new Common();
    private static boolean isChannel;

    private Common() {
    }

    public boolean isChannel() {
        return isChannel;
    }

    public void setChannel(boolean z) {
        isChannel = z;
    }

    public void gotoDevicePicker(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityConnect.class));
    }

    public void setVibrate(Context context, boolean z) {
        context.getSharedPreferences(context.getPackageName(), 0).edit().putBoolean("KEY_VIBRATE", z).apply();
    }

    public boolean getVibrate(Context context) {
        return context.getSharedPreferences(context.getPackageName(), 0).getBoolean("KEY_VIBRATE", false);
    }

    public void shakeItBaby(Context context) {
        if (!getVibrate(context)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = context.getSystemService(Context.VIBRATOR_SERVICE);
            ((Vibrator) systemService).vibrate(VibrationEffect.createOneShot(150, -1));
            return;
        }
        Object systemService2 = context.getSystemService(Context.VIBRATOR_SERVICE);
        ((Vibrator) systemService2).vibrate(150);
    }


    public void setDevice1(Context context, Remote remote) {
        String json = new Gson().toJson(remote);
        context.getSharedPreferences(context.getPackageName(), 0).edit().putString("device1", json).apply();
    }

    public Remote getDevice1(Context context) {
        return (Remote) new Gson().fromJson(context.getSharedPreferences(context.getPackageName(), 0).getString("device1", ""), Remote.class);
    }

    public void setDevice2(Context context, Remote remote) {
        String json = new Gson().toJson(remote);
        context.getSharedPreferences(context.getPackageName(), 0).edit().putString("device2", json).apply();
    }

    public Remote getDevice2(Context context) {
        return (Remote) new Gson().fromJson(context.getSharedPreferences(context.getPackageName(), 0).getString("device2", ""), Remote.class);
    }

    public void setIsSaveDevice1(Context context, boolean z) {
        context.getSharedPreferences(context.getPackageName(), 0).edit().putBoolean("isDevice1", z).apply();
    }

    public boolean getIsSaveDevice1(Context context) {
        return context.getSharedPreferences(context.getPackageName(), 0).getBoolean("isDevice1", true);
    }

}
