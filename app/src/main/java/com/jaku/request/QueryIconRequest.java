package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class QueryIconRequest extends JakuRequestData {
    private String appId;

    public String getMethod() {
        return "GET";
    }

    public QueryIconRequest(String str, String str2) {
        super(str);
        this.appId = str2;
    }

    public String getPath() {
        return "/query/icon/" + this.appId;
    }
}
