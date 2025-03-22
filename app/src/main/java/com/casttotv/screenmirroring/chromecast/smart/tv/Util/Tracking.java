package com.casttotv.screenmirroring.chromecast.smart.tv.Util;

import android.content.Context;
import android.os.Bundle;

public class Tracking {

    public static void connect(Context context, String str, String str2, String str3) {
        try {
            Boolean bool = Boolean.FALSE;
            Bundle bundle = new Bundle();
            bundle.putString(str2, str2);
            bundle.putString(str, str);
            String[] strArr = NameDevice.list;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str4 = strArr[i];
                if (str3.contains(str4)) {
                    bool = Boolean.TRUE;
                    bundle.putString("name_default", str4);
                    bundle.putString(str3, str3.replace(" ", "_"));
                    break;
                }
                i++;
            }
            if (!bool.booleanValue()) {
                bundle.putString("name_default", "other");
                bundle.putString(str3, str3.replace(" ", "_"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void trackCast(Context context, String str, String str2, String str3, String str4) {
        try {
            Boolean bool = Boolean.FALSE;
            Bundle bundle = new Bundle();
            bundle.putString(str, str);
            bundle.putString(str2, str2);
            String[] strArr = NameDevice.list;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str5 = strArr[i];
                if (str3.contains(str5)) {
                    bool = Boolean.TRUE;
                    str3.replace(" ", "_");
                    bundle.putString("name_default", str5);
                    bundle.putString(str3, str3);
                    break;
                }
                i++;
            }
            if (!bool.booleanValue()) {
                bundle.putString("name_default", "other");
                bundle.putString(str3, str3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
