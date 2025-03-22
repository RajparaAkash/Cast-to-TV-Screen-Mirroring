package com.jaku.api;

import com.jaku.core.JakuRequest;
import com.jaku.model.Device;
import com.jaku.parser.DeviceDiscoveryParser;
import com.jaku.request.DiscoveryRequest;

import java.io.IOException;
import java.util.List;

public class DeviceRequests {
    public static final List<Device> discoverDevices() throws IOException {
        return (List) new JakuRequest(new DiscoveryRequest("http://239.255.255.250:1900"), new DeviceDiscoveryParser()).send().getResponseData();
    }
}
