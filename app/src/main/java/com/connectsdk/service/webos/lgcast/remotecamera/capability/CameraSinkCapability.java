package com.connectsdk.service.webos.lgcast.remotecamera.capability;

import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;

import org.json.JSONObject;


public class CameraSinkCapability {
    public String devicePlatform;
    public String deviceSoC;
    public String deviceType;
    public String deviceVersion;
    public String ipAddress;
    public int keepAliveTimeout;
    public String publicKey;

    public CameraSinkCapability(JSONObject jsonObj) {
        if (jsonObj == null) {
            throw new IllegalArgumentException();
        }
        this.ipAddress = jsonObj.optString(ServiceDescription.KEY_IP_ADDRESS, "0.0.0.0");
        this.keepAliveTimeout = jsonObj.optInt("keepAliveTimeout", 60) * 1000;
        this.publicKey = jsonObj.optString("publicKey");
        if (jsonObj.has("deviceInfo")) {
            JSONObject optJSONObject = jsonObj.optJSONObject("deviceInfo");
            this.deviceType = optJSONObject.optString("type");
            this.deviceVersion = optJSONObject.optString("version");
            this.devicePlatform = optJSONObject.optString("platform");
            this.deviceSoC = optJSONObject.optString("SoC");
        }
    }

    public void debug() {
        Logger.debug("ipAddress=" + this.ipAddress, new Object[0]);
        Logger.debug("keepAliveTimeout=" + this.keepAliveTimeout, new Object[0]);
        Logger.debug("deviceType=" + this.deviceType, new Object[0]);
        Logger.debug("deviceVersion=" + this.deviceVersion, new Object[0]);
        Logger.debug("devicePlatform=" + this.devicePlatform, new Object[0]);
        Logger.debug("deviceSoC=" + this.deviceSoC, new Object[0]);
        Logger.debug("", new Object[0]);
    }
}
