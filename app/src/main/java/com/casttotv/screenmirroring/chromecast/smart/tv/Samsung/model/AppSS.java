package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class AppSS {
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("app_type")
    @Expose
    private Integer appType;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("name")
    @Expose
    private String name;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public Integer getAppType() {
        return this.appType;
    }

    public void setAppType(Integer num) {
        this.appType = num;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
