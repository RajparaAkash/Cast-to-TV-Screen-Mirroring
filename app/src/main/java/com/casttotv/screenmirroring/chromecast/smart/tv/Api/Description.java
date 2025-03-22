package com.casttotv.screenmirroring.chromecast.smart.tv.Api;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Description {
    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
