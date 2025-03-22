package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

import com.connectsdk.device.ConnectableDevice;

import java.util.ArrayList;

public class TVObject {
    private ArrayList<ConnectableDevice> arrType = new ArrayList<>();
    private String tvName;
    private String tvModelName;

    public TVObject(String tvName, String tvModelName, ArrayList<ConnectableDevice> arrayList) {
        this.tvName = tvName;
        this.tvModelName = tvModelName;
        this.arrType = arrayList;
    }

    public String getTvName() {
        return this.tvName;
    }

    public String getTvModelName() {
        return tvModelName;
    }

    public ArrayList<ConnectableDevice> getArrType() {
        return this.arrType;
    }
}
