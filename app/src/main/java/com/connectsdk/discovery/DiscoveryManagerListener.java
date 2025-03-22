package com.connectsdk.discovery;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.command.ServiceCommandError;


public interface DiscoveryManagerListener {
    void onDeviceAdded(DiscoveryManager manager, ConnectableDevice device);

    void onDeviceRemoved(DiscoveryManager manager, ConnectableDevice device);

    void onDeviceUpdated(DiscoveryManager manager, ConnectableDevice device);

    void onDiscoveryFailed(DiscoveryManager manager, ServiceCommandError error);
}
