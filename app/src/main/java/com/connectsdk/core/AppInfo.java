package com.connectsdk.core;

import org.json.JSONException;
import org.json.JSONObject;


public class AppInfo implements JSONSerializable {

    String id;
    String name;
    JSONObject raw;

    public AppInfo() {
    }

    public AppInfo(String id) {
        this.id = id;
    }

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
        this.name = name.trim();
    }

    public JSONObject getRawData() {
        return this.raw;
    }

    public void setRawData(JSONObject data) {
        this.raw = data;
    }

    @Override 
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.name);
        jSONObject.put("id", this.id);
        return jSONObject;
    }

    public boolean equals(Object o) {
        if (o instanceof AppInfo) {
            return this.id.equals(((AppInfo) o).id);
        }
        return super.equals(o);
    }
}
