package com.connectsdk.device;

import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;

import org.json.JSONObject;


public interface ConnectableDeviceStore {
    void addDevice(ConnectableDevice device);

    ConnectableDevice getDevice(String uuid);

    ServiceConfig getServiceConfig(ServiceDescription serviceDescription);

    JSONObject getStoredDevices();

    void removeAll();

    void removeDevice(ConnectableDevice device);

    void updateDevice(ConnectableDevice device);
}
