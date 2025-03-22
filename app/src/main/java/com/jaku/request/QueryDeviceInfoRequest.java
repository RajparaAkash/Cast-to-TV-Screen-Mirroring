package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class QueryDeviceInfoRequest extends JakuRequestData {
    public String getMethod() {
        return "GET";
    }

    public String getPath() {
        return "/query/device-info";
    }

    public QueryDeviceInfoRequest(String str) {
        super(str);
    }
}
