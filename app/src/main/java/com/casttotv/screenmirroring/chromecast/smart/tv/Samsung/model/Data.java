package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class Data {
    @SerializedName("data")
    @Expose
    private List<AppSS> data = null;

    public List<AppSS> getData() {
        return this.data;
    }

    public void setData(List<AppSS> list) {
        this.data = list;
    }
}
