package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;

public class SettingsManagerActivity implements Settings {

    private static SettingsManagerActivity INSTANCE = null;
    private final Context context;

    // Preference keys
    public static final String PREF_AUTH_ENABLED = "pref_auth_enabled";
    public static final String PREF_AUTH_PASSWORD = "pref_auth_password";
    public static final String PREF_AUTH_USERNAME = "pref_auth_username";
    public static final String PREF_COLOR = "pref_color";
    public static final String PREF_FORMAT = "pref_format";
    public static final String PREF_PORT = "pref_port";
    public static final String PREF_QUALITY = "pref_quality";

    private SettingsManagerActivity(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized SettingsManagerActivity getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SettingsManagerActivity(context);
        }
        return INSTANCE;
    }

    @Override
    public String getQuality() {
        return getSharedPreferences().getString(PREF_QUALITY, "high");
    }

    @Override
    public double getScalingFactor() {
        String quality = getQuality();
        int qualityFactor = getQualityFactor(quality);

        Point displayResolution = DisplayUtil.getDisplayResolution(this.context);
        double min = (double) qualityFactor / Math.min(displayResolution.x, displayResolution.y);

        return Math.min(1.0d, Math.max(0.1d, min));
    }

    private int getQualityFactor(String quality) {
        switch (quality) {
            case "low":
                return 280;
            case "high":
                return 600;
            case "native":
                return 1;
            case "medium":
            default:
                return 350;
        }
    }


    @Override
    public int getJpegQuality() {
        String quality = getQuality();
        switch (quality) {
            case "native":
                return 100;
            case "low":
                return 35;
            case "high":
                return 80;
            case "medium":
            default:
                return 45;
        }
    }

    @Override
    public boolean hasColor() {
        return getSharedPreferences().getBoolean(PREF_COLOR, true);
    }

    @Override
    public int getWebServerPort() {
        return Integer.parseInt(getSharedPreferences().getString(PREF_PORT, "9090"));
    }

    @Override
    public String getFormat() {
        return getSharedPreferences().getString(PREF_FORMAT, "mjpeg");
    }

    @Override
    public boolean isHttpAuthEnabled() {
        return getSharedPreferences().getBoolean(PREF_AUTH_ENABLED, false);
    }

    @Override
    public String getHttpUsername() {
        return getSharedPreferences().getString(PREF_AUTH_USERNAME, "");
    }

    @Override
    public String getHttpPassword() {
        return getSharedPreferences().getString(PREF_AUTH_PASSWORD, "");
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.context);
    }
}
