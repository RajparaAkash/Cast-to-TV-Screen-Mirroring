package com.connectsdk.core;

import org.json.JSONException;
import org.json.JSONObject;


public class ExternalInputInfo implements JSONSerializable {
    boolean connected;
    String iconURL;

    String id;
    String name;
    JSONObject rawData;

    public String getId() {
        return this.id;
    }

    public void setId(String inputId) {
        this.id = inputId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public void setRawData(JSONObject rawData) {
        this.rawData = rawData;
    }

    public JSONObject getRawData() {
        return this.rawData;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getIconURL() {
        return this.iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    @Override 
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("name", this.name);
        jSONObject.put("connected", this.connected);
        jSONObject.put("icon", this.iconURL);
        jSONObject.put("rawData", this.rawData);
        return jSONObject;
    }

    public boolean equals(Object o) {
        if (o instanceof ExternalInputInfo) {
            ExternalInputInfo externalInputInfo = (ExternalInputInfo) o;
            return this.id.equals(externalInputInfo.id) && this.name.equals(externalInputInfo.name);
        }
        return false;
    }
}
