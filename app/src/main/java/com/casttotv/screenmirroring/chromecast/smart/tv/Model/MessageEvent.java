package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

public class MessageEvent {

    private long duration;
    private final String message;

    public MessageEvent(String str, long j) {
        this.message = str;
        this.duration = j;
    }

    public MessageEvent(String str) {
        this.message = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getMessage() {
        return this.message;
    }
}
