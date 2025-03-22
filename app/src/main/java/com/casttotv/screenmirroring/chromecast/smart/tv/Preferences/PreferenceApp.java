package com.casttotv.screenmirroring.chromecast.smart.tv.Preferences;

public class PreferenceApp {

    public static String get_LanguageCode() {
        return PrefHelper.getInstance().getString("languageCode", "en");
    }

    public static void set_LanguageCode(String str) {
        PrefHelper.getInstance().setString("languageCode", str);
    }

    public static Boolean isVibrate() {
        return PrefHelper.getInstance().getBoolean("vibrate", false);
    }

    public static void set_Vibrate(Boolean bool) {
        PrefHelper.getInstance().setBoolean("vibrate", bool);
    }

    public static Boolean isFirstTime() {
        return PrefHelper.getInstance().getBoolean("firstTime", true);
    }

    public static void set_FirstTime(Boolean bool) {
        PrefHelper.getInstance().setBoolean("firstTime", bool);
    }
}
