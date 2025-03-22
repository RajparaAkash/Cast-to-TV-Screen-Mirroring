package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model;

import androidx.annotation.Keep;

@Keep
public class Entry {
    private String key;
    private String value;

    public Entry(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
