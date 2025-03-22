package com.casttotv.screenmirroring.chromecast.smart.tv.Lg;

import android.content.Context;

public class LGTVSingleTon {
    public static final LGTVSingleTon INSTANCE = new LGTVSingleTon();
    private static LGTV lgtv;

    private LGTVSingleTon() {
    }

    public LGTV getLgtv() {
        return lgtv;
    }

    public void setLgtv(LGTV lgtv2) {
        lgtv = lgtv2;
    }

    public LGTV getInstance(Context context) {
        if (lgtv == null) {
            lgtv = new LGTV(context);
        }
        return lgtv;
    }

}
