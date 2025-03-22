package com.connectsdk.service.webos.lgcast.screenmirroring.capability;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraProperty;
import com.lge.lib.lgcast.iface.MasterKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class MirroringSourceCapability {
    public int audioChannels;
    public int audioClockRate;
    public String audioCodec;
    public int audioFrequency;
    public String audioStreamMuxConfig;
    public ArrayList<MasterKey> masterKeys;
    public String screenOrientation;
    public boolean uibcEnabled;
    public int videoActiveHeight;
    public int videoActiveWidth;
    public int videoClockRate;
    public String videoCodec;
    public int videoFramerate;
    public int videoHeight;
    public String videoOrientation;
    public int videoWidth;

    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("codec", this.videoCodec);
            jSONObject.put("clockRate", this.videoClockRate);
            jSONObject.put("framerate", this.videoFramerate);
            jSONObject.put("width", this.videoWidth);
            jSONObject.put("height", this.videoHeight);
            jSONObject.put("activeWidth", this.videoActiveWidth);
            jSONObject.put("activeHeight", this.videoActiveHeight);
            jSONObject.put("orientation", this.videoOrientation);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("codec", this.audioCodec);
            jSONObject2.put("clockRate", this.audioClockRate);
            jSONObject2.put("frequency", this.audioFrequency);
            jSONObject2.put("streamMuxConfig", this.audioStreamMuxConfig);
            jSONObject2.put("channels", this.audioChannels);
            JSONArray jSONArray = new JSONArray();
            Iterator<MasterKey> it = this.masterKeys.iterator();
            while (it.hasNext()) {
                MasterKey next = it.next();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("mki", next.mkiSecureText);
                jSONObject3.put("key", next.keySecureText);
                jSONArray.put(jSONObject3);
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("screenOrientation", this.screenOrientation);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("video", jSONObject);
            jSONObject5.put(CameraProperty.AUDIO, jSONObject2);
            jSONObject5.put("crypto", jSONArray);
            jSONObject5.put("uibcEnabled", this.uibcEnabled);
            jSONObject5.put("supportedFeatures", jSONObject4);
            return jSONObject5;
        } catch (JSONException exception) {
            return new JSONObject();
        }
    }

    public void debug() {
        Logger.debug("videoCodec=" + this.videoCodec, new Object[0]);
        Logger.debug("videoClockRate=" + this.videoClockRate, new Object[0]);
        Logger.debug("videoFramerate=" + this.videoFramerate, new Object[0]);
        Logger.debug("videoWidth=" + this.videoWidth, new Object[0]);
        Logger.debug("videoHeight=" + this.videoHeight, new Object[0]);
        Logger.debug("videoActiveWidth=" + this.videoActiveWidth, new Object[0]);
        Logger.debug("videoActiveHeight=" + this.videoActiveHeight, new Object[0]);
        Logger.debug("videoOrientation=" + this.videoOrientation, new Object[0]);
        Logger.debug("audioCodec=" + this.audioCodec, new Object[0]);
        Logger.debug("audioClockRate=" + this.audioClockRate, new Object[0]);
        Logger.debug("audioFrequency=" + this.audioFrequency, new Object[0]);
        Logger.debug("audioStreamMuxConfig=" + this.audioStreamMuxConfig, new Object[0]);
        Logger.debug("audioChannels=" + this.audioChannels, new Object[0]);
        Logger.debug("uibcEnabled=" + this.uibcEnabled, new Object[0]);
        Logger.debug("screenOrientation=" + this.screenOrientation, new Object[0]);
        Logger.debug("", new Object[0]);
    }
}
