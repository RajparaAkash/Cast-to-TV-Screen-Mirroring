package com.connectsdk.discovery;

import java.util.List;


public interface DiscoveryProvider {
    public static final int RESCAN_ATTEMPTS = 6;
    public static final int RESCAN_INTERVAL = 10000;
    public static final int TIMEOUT = 60000;

    void addDeviceFilter(DiscoveryFilter filter);

    void addListener(DiscoveryProviderListener listener);

    boolean isEmpty();

    void removeDeviceFilter(DiscoveryFilter filter);

    void removeListener(DiscoveryProviderListener listener);

    void rescan();

    void reset();

    void restart();

    void setFilters(List<DiscoveryFilter> filters);

    void start();

    void stop();
}
