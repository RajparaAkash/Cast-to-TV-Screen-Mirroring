package com.casttotv.screenmirroring.chromecast.smart.tv.Util;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

public abstract class ThrottleClickListener implements View.OnClickListener {

    private static final long DEFAULT_THROTTLE_INTERVAL = 1000L; // 1 second throttle interval
    private final long throttleInterval;
    private boolean isClickable = true;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public ThrottleClickListener() {
        this(DEFAULT_THROTTLE_INTERVAL);
    }

    public ThrottleClickListener(long throttleInterval) {
        this.throttleInterval = throttleInterval;
    }

    @Override
    public final void onClick(View view) {
        if (isClickable) {
            isClickable = false;
            mainHandler.postDelayed(() -> isClickable = true, throttleInterval);
            onThrottleClick(view);
        }
    }

    public abstract void onThrottleClick(View view);
}
