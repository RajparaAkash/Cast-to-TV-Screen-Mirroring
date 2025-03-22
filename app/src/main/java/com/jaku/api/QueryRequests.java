package com.jaku.api;

import com.jaku.core.JakuRequest;
import com.jaku.model.Channel;
import com.jaku.model.Device;
import com.jaku.parser.AppsParser;
import com.jaku.parser.DeviceParser;
import com.jaku.parser.IconParser;
import com.jaku.request.QueryActiveAppRequest;
import com.jaku.request.QueryAppsRequest;
import com.jaku.request.QueryDeviceInfoRequest;
import com.jaku.request.QueryIconRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class QueryRequests {
    private QueryRequests() {
    }

    public static List<Channel> queryAppsRequest(String str) throws IOException {
        return (List<Channel>) new JakuRequest(new QueryAppsRequest(str), new AppsParser()).send().getResponseData();
    }

    public static List<Channel> queryActiveAppRequest(String str) throws IOException {
        return (List) new JakuRequest(new QueryActiveAppRequest(str), new AppsParser()).send().getResponseData();
    }

    public static Device queryDeviceInfo(String str) throws IOException {
        return (Device) new JakuRequest(new QueryDeviceInfoRequest(str), new DeviceParser()).send().getResponseData();
    }

    public static byte[] queryIconRequest(String str, String str2) throws IOException {
        return ((ByteArrayOutputStream) new JakuRequest(new QueryIconRequest(str, str2), new IconParser()).send().getResponseData()).toByteArray();
    }
}
