package com.casttotv.screenmirroring.chromecast.smart.tv.Api;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class App {
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("iconArtSmallUri")
    @Expose
    private String iconArtSmallUri;
    @SerializedName("isInstalled")
    @Expose
    private Boolean isInstalled;
    @SerializedName("name")
    @Expose
    private String name;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getIconArtSmallUri() {
        return this.iconArtSmallUri;
    }

    public void setIconArtSmallUri(String str) {
        this.iconArtSmallUri = str;
    }

    public Boolean getIsInstalled() {
        return this.isInstalled;
    }

    public void setIsInstalled(Boolean bool) {
        this.isInstalled = bool;
    }

}
