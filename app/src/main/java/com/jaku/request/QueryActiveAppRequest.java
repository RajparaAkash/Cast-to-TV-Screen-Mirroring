package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class QueryActiveAppRequest extends JakuRequestData {
    public String getMethod() {
        return "GET";
    }

    public String getPath() {
        return "/query/active-app";
    }

    public QueryActiveAppRequest(String str) {
        super(str);
    }
}
