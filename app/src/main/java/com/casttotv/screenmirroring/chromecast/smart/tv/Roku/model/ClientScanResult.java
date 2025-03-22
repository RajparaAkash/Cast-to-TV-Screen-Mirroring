package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model;

import androidx.annotation.Keep;

@Keep
public class ClientScanResult {
    private String Device;
    private String HWAddr;
    private String IpAddr;
    private boolean isReachable;

    public ClientScanResult(String str, String str2, String str3, boolean z) {
        this.IpAddr = str;
        this.HWAddr = str2;
        this.Device = str3;
        this.isReachable = z;
    }

    public String getIpAddr() {
        return this.IpAddr;
    }

    public void setIpAddr(String str) {
        this.IpAddr = str;
    }

    public String getHWAddr() {
        return this.HWAddr;
    }

    public void setHWAddr(String str) {
        this.HWAddr = str;
    }

    public String getDevice() {
        return this.Device;
    }

    public void setDevice(String str) {
        this.Device = str;
    }

    public boolean isReachable() {
        return this.isReachable;
    }

    public void setReachable(boolean z) {
        this.isReachable = z;
    }
}
