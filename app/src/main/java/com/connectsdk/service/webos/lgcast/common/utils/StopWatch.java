package com.connectsdk.service.webos.lgcast.common.utils;


public class StopWatch {
    private long started = 0;

    private StopWatch() {
    }

    public static StopWatch start() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.started = System.currentTimeMillis();
        return stopWatch;
    }

    public long reset() {
        long currentTimeMillis = System.currentTimeMillis() - this.started;
        this.started = System.currentTimeMillis();
        return currentTimeMillis;
    }

    public long stop() {
        return System.currentTimeMillis() - this.started;
    }
}
