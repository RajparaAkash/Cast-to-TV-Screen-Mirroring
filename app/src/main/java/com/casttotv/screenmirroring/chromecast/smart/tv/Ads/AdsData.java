package com.casttotv.screenmirroring.chromecast.smart.tv.Ads;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class AdsData {

    @SerializedName("app_a")
    @Expose
    private int appA;

    @SerializedName("app_v")
    @Expose
    private int appV;

    @SerializedName("t_sec")
    @Expose
    private int tSec;

    @SerializedName("inter_count")
    @Expose
    private int interCount;

    @SerializedName("g_ao_tag")
    @Expose
    private String gAoTag;

    @SerializedName("g_i_tag")
    @Expose
    private String gITag;

    @SerializedName("g_b_tag")
    @Expose
    private String gBTag;

    @SerializedName("g_r_tag")
    @Expose
    private String gRTag;

    @SerializedName("g_n_tag")
    @Expose
    private String gNTag;

    @SerializedName("g_n1_tag")
    @Expose
    private String gN1Tag;

    public int getAppA() {
        return appA;
    }

    public int getAppV() {
        return appV;
    }

    public int getAdSec() {
        return tSec;
    }

    public int getInterCount() {
        return interCount;
    }

    public String getAppOpenTag() {
        return gAoTag;
    }

    public String getInterTag() {
        return gITag;
    }

    public String getBannerTag() {
        return gBTag;
    }

    public String getRewardTag() {
        return gRTag;
    }

    public String getNativeTag() {
        return gNTag;
    }

    public String getNative1Tag() {
        return gN1Tag;
    }
}