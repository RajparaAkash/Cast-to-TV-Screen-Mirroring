package com.casttotv.screenmirroring.chromecast.smart.tv.Sony;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LifecycleHelper implements LifecycleObserver {
    public static final LifecycleHelper INSTANCE = new LifecycleHelper();

    private LifecycleHelper() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateActivity() {
    }

    public void registerLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

}
