package com.connectsdk.core;


public class ProgramInfo {
    private ChannelInfo channelInfo;

    private String id;
    private String name;
    private Object rawData;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChannelInfo getChannelInfo() {
        return this.channelInfo;
    }

    public void setChannelInfo(ChannelInfo channelInfo) {
        this.channelInfo = channelInfo;
    }

    public Object getRawData() {
        return this.rawData;
    }

    public void setRawData(Object rawData) {
        this.rawData = rawData;
    }

    public boolean equals(Object o) {
        if (o instanceof ProgramInfo) {
            ProgramInfo programInfo = (ProgramInfo) o;
            String str = programInfo.id;
            if (str.equals(str)) {
                String str2 = programInfo.name;
                if (str2.equals(str2)) {
                    return true;
                }
            }
            return false;
        }
        return super.equals(o);
    }
}
