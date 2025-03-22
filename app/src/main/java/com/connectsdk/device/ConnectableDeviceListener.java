package com.connectsdk.device;

import com.connectsdk.service.DeviceService;
import com.connectsdk.service.command.ServiceCommandError;

import java.util.List;


public interface ConnectableDeviceListener {
    void onCapabilityUpdated(ConnectableDevice device, List<String> added, List<String> removed);

    void onConnectionFailed(ConnectableDevice device, ServiceCommandError error);

    void onDeviceDisconnected(ConnectableDevice device);

    void onDeviceReady(ConnectableDevice device);

    void onPairingRequired(ConnectableDevice device, DeviceService service, DeviceService.PairingType pairingType);
}
