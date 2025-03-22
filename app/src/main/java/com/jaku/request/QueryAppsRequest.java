package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class QueryAppsRequest extends JakuRequestData {
    public String getMethod() {
        return "GET";
    }

    public String getPath() {
        return "/query/apps";
    }

    public QueryAppsRequest(String str) {
        super(str);
    }
}
