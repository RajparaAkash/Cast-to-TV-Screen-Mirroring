package com.jaku.core;

import java.util.LinkedHashMap;

public abstract class JakuRequestData implements RequestParameters {
    private String url;

    protected JakuRequestData(String str) {
        this.url = str;
    }

    public LinkedHashMap<String, String> getQueryParameters() {
        return new LinkedHashMap<>();
    }

    public String getEndpointUrl() {
        return this.url + getPath();
    }
}
