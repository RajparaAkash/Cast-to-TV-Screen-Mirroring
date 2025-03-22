package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class AppSamSung {
    @SerializedName("data")
    @Expose
    private Data data;
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

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }
}
