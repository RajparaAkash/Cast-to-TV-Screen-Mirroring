package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Icon {
    @SerializedName("iconPath")
    @Expose
    private String iconPath;
    @SerializedName("imageBase64")
    @Expose
    private String imageBase64;

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String str) {
        this.iconPath = str;
    }

    public String getImageBase64() {
        return this.imageBase64;
    }

    public void setImageBase64(String str) {
        this.imageBase64 = str;
    }
}
