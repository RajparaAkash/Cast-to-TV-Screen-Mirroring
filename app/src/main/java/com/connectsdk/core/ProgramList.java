package com.connectsdk.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProgramList implements JSONSerializable {
    ChannelInfo channel;
    JSONArray programList;

    public ProgramList(ChannelInfo channel, JSONArray programList) {
        this.channel = channel;
        this.programList = programList;
    }

    public ChannelInfo getChannel() {
        return this.channel;
    }

    public JSONArray getProgramList() {
        return this.programList;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ChannelInfo channelInfo = this.channel;
        jSONObject.put("channel", channelInfo != null ? channelInfo.toString() : null);
        JSONArray jSONArray = this.programList;
        jSONObject.put("programList", jSONArray != null ? jSONArray.toString() : null);
        return jSONObject;
    }
}
