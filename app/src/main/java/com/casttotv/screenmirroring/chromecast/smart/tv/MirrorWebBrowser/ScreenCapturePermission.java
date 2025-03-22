package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Intent;

public class ScreenCapturePermission {
    private final Intent intent;
    private final int resultCode;

    public ScreenCapturePermission(int i, Intent intent2) {
        this.resultCode = i;
        this.intent = intent2;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Intent getIntent() {
        return this.intent;
    }
}
