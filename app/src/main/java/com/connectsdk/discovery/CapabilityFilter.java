package com.connectsdk.discovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CapabilityFilter {
    public List<String> capabilities = new ArrayList<>();

    public CapabilityFilter() {
    }

    public CapabilityFilter(String... capabilities) {
        for (String str : capabilities) {
            addCapability(str);
        }
    }

    public CapabilityFilter(List<String> capabilities) {
        addCapabilities(capabilities);
    }

    public void addCapability(String capability) {
        this.capabilities.add(capability);
    }

    public void addCapabilities(List<String> capabilities) {
        this.capabilities.addAll(capabilities);
    }

    public void addCapabilities(String... capabilities) {
        Collections.addAll(this.capabilities, capabilities);
    }
}
