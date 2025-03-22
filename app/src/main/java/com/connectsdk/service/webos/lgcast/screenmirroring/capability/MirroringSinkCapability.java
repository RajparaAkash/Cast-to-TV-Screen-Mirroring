package com.connectsdk.service.webos.lgcast.screenmirroring.capability;

import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraProperty;

import org.json.JSONObject;


public class MirroringSinkCapability {
    public String audioCodec;
    public int audioUdpPort;
    public String devicePlatform;
    public String deviceSoC;
    public String deviceType;
    public String deviceVersion;
    public String displayOrientation;
    public String ipAddress;
    public int keepAliveTimeout;
    public String publicKey;
    public String supportedOrientation;
    public String videoCodec;
    public int videoLandscapeMaxHeight;
    public int videoLandscapeMaxWidth;
    public int videoPortraitMaxHeight;
    public int videoPortraitMaxWidth;
    public int videoUdpPort;

    public MirroringSinkCapability() {
    }

    public MirroringSinkCapability(JSONObject jsonObj) {
        if (jsonObj == null) {
            throw new IllegalArgumentException();
        }
        this.ipAddress = jsonObj.optString(ServiceDescription.KEY_IP_ADDRESS, "127.0.0.1");
        this.keepAliveTimeout = jsonObj.optInt("keepAliveTimeout", 60) * 1000;
        this.publicKey = jsonObj.optString("publicKey");
        JSONObject optJSONObject = jsonObj.optJSONObject("deviceInfo");
        if (optJSONObject != null) {
            this.deviceType = optJSONObject.optString("type");
            this.deviceVersion = optJSONObject.optString("version");
            this.devicePlatform = optJSONObject.optString("platform");
            this.deviceSoC = optJSONObject.optString("SoC");
        }
        JSONObject optJSONObject2 = jsonObj.optJSONObject("mirroring");
        if (optJSONObject2 != null) {
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("video");
            if (optJSONObject3 != null) {
                this.videoCodec = optJSONObject3.optString("codec");
                this.videoUdpPort = optJSONObject3.optInt("udpPort");
                JSONObject optJSONObject4 = optJSONObject3.optJSONObject("portrait");
                if (optJSONObject4 != null) {
                    this.videoPortraitMaxWidth = optJSONObject4.optInt("maxWidth", 1080);
                    this.videoPortraitMaxHeight = optJSONObject4.optInt("maxHeight", 1920);
                }
                JSONObject optJSONObject5 = optJSONObject3.optJSONObject("landscape");
                if (optJSONObject5 != null) {
                    this.videoLandscapeMaxWidth = optJSONObject5.optInt("maxWidth", 1920);
                    this.videoLandscapeMaxHeight = optJSONObject5.optInt("maxHeight", 1080);
                } else {
                    this.videoLandscapeMaxWidth = 1920;
                    this.videoLandscapeMaxHeight = 1080;
                }
            }
            JSONObject optJSONObject6 = optJSONObject2.optJSONObject(CameraProperty.AUDIO);
            if (optJSONObject6 != null) {
                this.audioCodec = optJSONObject6.optString("codec", "none");
                this.audioUdpPort = optJSONObject6.optInt("udpPort");
            }
            JSONObject optJSONObject7 = optJSONObject2.optJSONObject("supportedFeatures");
            if (optJSONObject7 != null) {
                this.supportedOrientation = optJSONObject7.optString("screenOrientation", "landscape");
            }
            this.displayOrientation = optJSONObject2.optString("displayOrientation", "landscape");
        }
    }

    public boolean isSupportLandscapeMode() {
        String str = this.supportedOrientation;
        return str != null && str.contains("landscape");
    }

    public boolean isSupportPortraitMode() {
        String str = this.supportedOrientation;
        return str != null && str.contains("portrait");
    }

    public boolean isDisplayLandscape() {
        return "landscape".equals(this.displayOrientation);
    }

    public boolean isDisplayPortrait() {
        return "portrait".equals(this.displayOrientation);
    }

    public void debug() {
        Logger.debug("ipAddress=" + this.ipAddress, new Object[0]);
        Logger.debug("keepAliveTimeout=" + this.keepAliveTimeout, new Object[0]);
        Logger.debug("deviceType=" + this.deviceType, new Object[0]);
        Logger.debug("deviceVersion=" + this.deviceVersion, new Object[0]);
        Logger.debug("devicePlatform=" + this.devicePlatform, new Object[0]);
        Logger.debug("deviceSoC=" + this.deviceSoC, new Object[0]);
        Logger.debug("videoCodec=" + this.videoCodec, new Object[0]);
        Logger.debug("videoUdpPort=" + this.videoUdpPort, new Object[0]);
        Logger.debug("videoPortraitMaxWidth=" + this.videoPortraitMaxWidth, new Object[0]);
        Logger.debug("videoPortraitMaxHeight=" + this.videoPortraitMaxHeight, new Object[0]);
        Logger.debug("videoLandscapeMaxWidth=" + this.videoLandscapeMaxWidth, new Object[0]);
        Logger.debug("videoLandscapeMaxHeight=" + this.videoLandscapeMaxHeight, new Object[0]);
        Logger.debug("audioCodec=" + this.audioCodec, new Object[0]);
        Logger.debug("audioUdpPort=" + this.audioUdpPort, new Object[0]);
        Logger.debug("supportedOrientation=" + this.supportedOrientation, new Object[0]);
        Logger.debug("displayOrientation=" + this.displayOrientation, new Object[0]);
        Logger.debug("", new Object[0]);
    }
}
