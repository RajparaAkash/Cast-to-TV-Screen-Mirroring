package com.jaku.request;

import com.jaku.core.JakuRequestData;

public class DiscoveryRequest extends JakuRequestData {
    public String getMethod() {
        return "DISCOVERY";
    }

    public String getPath() {
        return "";
    }

    public DiscoveryRequest(String str) {
        super(str);
    }
}
