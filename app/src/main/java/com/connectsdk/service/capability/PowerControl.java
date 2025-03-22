package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;


public interface PowerControl extends CapabilityMethods {
    String Any = "PowerControl.Any";
    String Off = "PowerControl.Off";
    String On = "PowerControl.On";
    String[] Capabilities = {Off, On};

    PowerControl getPowerControl();

    CapabilityPriorityLevel getPowerControlCapabilityLevel();

    void powerOff(ResponseListener<Object> listener);

    void powerOn(ResponseListener<Object> listener);
}
