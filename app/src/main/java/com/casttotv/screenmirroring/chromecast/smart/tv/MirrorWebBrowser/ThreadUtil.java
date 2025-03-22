package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {

    public static void sleepMillis(long j) {
        try {
            TimeUnit.MILLISECONDS.sleep(j);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
