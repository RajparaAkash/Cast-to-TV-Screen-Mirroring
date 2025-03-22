package com.casttotv.screenmirroring.chromecast.smart.tv.Sony.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class ListApp {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("result")
    @Expose
    private List<List<AppSony>> result;

    public List<List<AppSony>> getResult() {
        return this.result;
    }

    public void setResult(List<List<AppSony>> list) {
        this.result = list;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }
}
