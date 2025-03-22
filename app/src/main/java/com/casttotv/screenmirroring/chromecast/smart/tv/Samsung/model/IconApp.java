package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class IconApp {
    @SerializedName("data")
    @Expose
    private Icon data;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("from")
    @Expose
    private String from;

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public Icon getData() {
        return this.data;
    }

    public void setData(Icon icon) {
        this.data = icon;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }
}
