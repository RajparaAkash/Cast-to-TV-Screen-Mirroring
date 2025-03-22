package com.connectsdk.service.config;

import org.json.JSONException;
import org.json.JSONObject;


public class AirPlayServiceConfig extends ServiceConfig {
    public static final String KEY_AUTH_TOKEN = "authToken";
    String authToken;

    public AirPlayServiceConfig(JSONObject json) {
        super(json);
        this.authToken = json.optString(KEY_AUTH_TOKEN);
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        notifyUpdate();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        try {
            jSONObject.put(KEY_AUTH_TOKEN, this.authToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
