package com.lge.lib.lgcast.iface;

import android.media.projection.MediaProjection;
import android.os.Handler;

import com.lge.lib.lgcast.func.b;

public class AudioCaptureIF {

    private b f3063a;

    public AudioCaptureIF(int i, int i2) {
        this.f3063a = new b(i, i2);
    }

    public void setErrorListener(CaptureErrorListener captureErrorListener) {
        this.f3063a.a(captureErrorListener);
    }

    public void startCapture(MediaProjection mediaProjection, Handler handler) {
        this.f3063a.a(mediaProjection, handler);
    }

    public void stopCapture() {
        this.f3063a.a();
    }
}
