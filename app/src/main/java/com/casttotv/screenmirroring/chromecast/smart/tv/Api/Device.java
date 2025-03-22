package com.casttotv.screenmirroring.chromecast.smart.tv.Api;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Device {
    @SerializedName("friendlyName")
    @Expose
    private String friendlyName;

    public Device(String str) {
        this.friendlyName = str;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }
}
