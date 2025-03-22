package com.connectsdk.core;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class ChannelInfo implements JSONSerializable {
    String channelId;
    String channelName;
    String channelNumber;
    int majorNumber;
    int minorNumber;
    JSONObject rawData;

    public JSONObject getRawData() {
        return this.rawData;
    }

    public void setRawData(JSONObject rawData) {
        this.rawData = rawData;
    }

    public String getName() {
        return this.channelName;
    }

    public void setName(String channelName) {
        this.channelName = channelName;
    }

    public String getId() {
        return this.channelId;
    }

    public void setId(String channelId) {
        this.channelId = channelId;
    }

    public String getNumber() {
        return this.channelNumber;
    }

    public void setNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int getMinorNumber() {
        return this.minorNumber;
    }

    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }

    public int getMajorNumber() {
        return this.majorNumber;
    }

    public void setMajorNumber(int majorNumber) {
        this.majorNumber = majorNumber;
    }

    public boolean equals(Object o) {
        if (o instanceof ChannelInfo) {
            ChannelInfo channelInfo = (ChannelInfo) o;
            String str = this.channelId;
            if (str != null) {
                if (str.equals(channelInfo.channelId)) {
                    return true;
                }
            } else {
                String str2 = this.channelName;
                if (str2 != null && this.channelNumber != null) {
                    return str2.equals(channelInfo.channelName) && this.channelNumber.equals(channelInfo.channelNumber) && this.majorNumber == channelInfo.majorNumber && this.minorNumber == channelInfo.minorNumber;
                }
            }
            Log.d(Util.T, "Could not compare channel values, no data to compare against");
            Log.d(Util.T, "This channel info: \n" + this.rawData.toString());
            Log.d(Util.T, "Other channel info: \n" + channelInfo.rawData.toString());
            return false;
        }
        return super.equals(o);
    }

    @Override 
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.channelName);
        jSONObject.put("id", this.channelId);
        jSONObject.put("number", this.channelNumber);
        jSONObject.put("majorNumber", this.majorNumber);
        jSONObject.put("minorNumber", this.minorNumber);
        jSONObject.put("rawData", this.rawData);
        return jSONObject;
    }
}
