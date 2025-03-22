package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class LaunchAppRequest extends JakuRequestData {
    private String appId;

    public String getMethod() {
        return "POST";
    }

    public LaunchAppRequest(String str, String str2) {
        super(str);
        this.appId = str2;
    }

    public String getPath() {
        return "/launch/" + this.appId;
    }
}
