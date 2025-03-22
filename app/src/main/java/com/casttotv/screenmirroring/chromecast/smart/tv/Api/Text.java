package com.casttotv.screenmirroring.chromecast.smart.tv.Api;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Text {
    @SerializedName("text")
    @Expose
    private String text;

    public Text(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
