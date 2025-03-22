package com.connectsdk.service.config;

import org.json.JSONException;
import org.json.JSONObject;


public class NetcastTVServiceConfig extends ServiceConfig {
    public static final String KEY_PAIRING = "pairingKey";
    String pairingKey;

    public NetcastTVServiceConfig(String serviceUUID) {
        super(serviceUUID);
    }

    public NetcastTVServiceConfig(String serviceUUID, String pairingKey) {
        super(serviceUUID);
        this.pairingKey = pairingKey;
    }

    public NetcastTVServiceConfig(JSONObject json) {
        super(json);
        this.pairingKey = json.optString(KEY_PAIRING, null);
    }

    public String getPairingKey() {
        return this.pairingKey;
    }

    public void setPairingKey(String pairingKey) {
        this.pairingKey = pairingKey;
        notifyUpdate();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        try {
            jSONObject.put(KEY_PAIRING, this.pairingKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
