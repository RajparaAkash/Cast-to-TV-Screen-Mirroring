package com.lge.lib.lgcast.iface;

import android.media.projection.MediaProjection;
import android.os.Handler;

import com.lge.lib.lgcast.func.h;

public class VideoCaptureIF {

    private h f3065a;

    public VideoCaptureIF(String str) {
        this.f3065a = new h(str);
    }

    public int getStatus() {
        return this.f3065a.a();
    }

    public void pause() {
        this.f3065a.c();
    }

    public void prepare(int i, int i2, int i3, MediaProjection mediaProjection, Handler handler) {
        this.f3065a.a(i, i2, i3, mediaProjection, handler);
    }

    public void setErrorListener(CaptureErrorListener captureErrorListener) {
        this.f3065a.a(captureErrorListener);
    }

    public void start() {
        this.f3065a.d();
    }

    public void stop() {
        this.f3065a.e();
    }
}
