package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class KeypressRequest extends JakuRequestData {
    private String key;

    public String getMethod() {
        return "POST";
    }

    public KeypressRequest(String str, String str2) {
        super(str);
        this.key = str2;
    }

    public String getPath() {
        return "/keypress/" + this.key;
    }
}
