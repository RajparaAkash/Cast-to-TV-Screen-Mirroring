package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

public enum RokuRequestTypes {
    query_active_app("query/active-app"),
    query_device_info("query/device-info"),
    launch("launch"),
    keypress("keypress"),
    query_icon("query/icon"),
    search("search/browse?");
    
    private final String method;

    private RokuRequestTypes(String str) {
        this.method = str;
    }

    public String getValue() {
        return this.method;
    }
}
