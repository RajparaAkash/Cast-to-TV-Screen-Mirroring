package com.connectsdk.device;


public interface SimpleDevicePickerListener extends DevicePickerListener {
    @Override
    void onPickDevice(ConnectableDevice device);

    @Override
    void onPickDeviceFailed(boolean canceled);

    void onPrepareDevice(ConnectableDevice device);
}
