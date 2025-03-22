package com.connectsdk.discovery;


public class DiscoveryFilter {
    String serviceFilter;
    String serviceId;

    public DiscoveryFilter(String serviceId, String serviceFilter) {
        this.serviceId = null;
        this.serviceFilter = null;
        this.serviceId = serviceId;
        this.serviceFilter = serviceFilter;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceFilter() {
        return this.serviceFilter;
    }

    public void setServiceFilter(String serviceFilter) {
        this.serviceFilter = serviceFilter;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscoveryFilter discoveryFilter = (DiscoveryFilter) o;
        String str = this.serviceFilter;
        if (str == null ? discoveryFilter.serviceFilter == null : str.equals(discoveryFilter.serviceFilter)) {
            String str2 = this.serviceId;
            String str3 = discoveryFilter.serviceId;
            return str2 == null ? str3 == null : str2.equals(str3);
        }
        return false;
    }

    public int hashCode() {
        String str = this.serviceId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.serviceFilter;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
