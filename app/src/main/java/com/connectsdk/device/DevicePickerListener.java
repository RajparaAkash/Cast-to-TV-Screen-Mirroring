package com.connectsdk.device;


public interface DevicePickerListener {
    void onPickDevice(ConnectableDevice device);

    void onPickDeviceFailed(boolean canceled);
}
