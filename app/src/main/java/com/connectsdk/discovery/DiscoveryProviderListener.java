package com.connectsdk.discovery;

import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.config.ServiceDescription;


public interface DiscoveryProviderListener {
    void onServiceAdded(DiscoveryProvider provider, ServiceDescription serviceDescription);

    void onServiceDiscoveryFailed(DiscoveryProvider provider, ServiceCommandError error);

    void onServiceRemoved(DiscoveryProvider provider, ServiceDescription serviceDescription);
}
