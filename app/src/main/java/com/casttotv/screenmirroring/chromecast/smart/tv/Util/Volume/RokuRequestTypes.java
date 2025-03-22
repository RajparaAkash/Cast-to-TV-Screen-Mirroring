package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

public enum RokuRequestTypes {
    query_active_app("query/active-app"),
    query_device_info("query/device-info"),
    launch("launch"),
    keypress("keypress"),
    query_icon("query/icon"),
    search("search/browse?");

    RokuRequestTypes(String str) {
    }
}
