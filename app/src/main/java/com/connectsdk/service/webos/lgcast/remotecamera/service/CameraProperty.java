package com.connectsdk.service.webos.lgcast.remotecamera.service;

import com.connectsdk.service.webos.lgcast.common.utils.JSONObjectEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;

import org.json.JSONObject;


public class CameraProperty {
    public static final String AUDIO = "audio";
    public static final String AUTO_WHITE_BALANCE = "autoWhiteBalance";
    public static final String BRIGHTNESS = "brightness";
    public static final String FACING = "facing";
    public static final String HEIGHT = "height";
    public static final String ROTATION = "rotation";
    public static final String WHITE_BALANCE = "whiteBalance";
    public static final String WIDTH = "width";
    public int width = 1280;
    public int height = 720;
    public int facing = 0;
    public int brightness = 50;
    public int whiteBalance = 7500;
    public boolean autoWhiteBalance = false;
    public boolean audio = true;
    public int rotation = -1;

    public JSONObject toJSONObject() {
        JSONObjectEx jSONObjectEx = new JSONObjectEx();
        jSONObjectEx.put("width", this.width);
        jSONObjectEx.put("height", this.height);
        jSONObjectEx.put(FACING, this.facing);
        jSONObjectEx.put(BRIGHTNESS, this.brightness);
        jSONObjectEx.put(WHITE_BALANCE, this.whiteBalance);
        jSONObjectEx.put(AUTO_WHITE_BALANCE, this.autoWhiteBalance);
        jSONObjectEx.put(AUDIO, this.audio);
        jSONObjectEx.put(ROTATION, this.rotation);
        return jSONObjectEx.toJSONObject();
    }

    public void debug() {
        Logger.debug("width=" + this.width, new Object[0]);
        Logger.debug("height=" + this.height, new Object[0]);
        Logger.debug("facing=" + this.facing, new Object[0]);
        Logger.debug("brightness=" + this.brightness, new Object[0]);
        Logger.debug("whiteBalance=" + this.whiteBalance, new Object[0]);
        Logger.debug("autoWhiteBalance=" + this.autoWhiteBalance, new Object[0]);
        Logger.debug("audio=" + this.audio, new Object[0]);
        Logger.debug("rotation=" + this.rotation, new Object[0]);
        Logger.debug("", new Object[0]);
    }
}
