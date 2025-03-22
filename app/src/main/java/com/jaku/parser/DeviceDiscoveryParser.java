package com.jaku.parser;

import com.jaku.core.Response;

public class DeviceDiscoveryParser extends JakuParser {
    public Object parse(Response response) {
        return response.getData();
    }
}
