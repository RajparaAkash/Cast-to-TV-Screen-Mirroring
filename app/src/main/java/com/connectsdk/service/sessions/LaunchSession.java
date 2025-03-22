package com.connectsdk.service.sessions;

import com.connectsdk.core.JSONDeserializable;
import com.connectsdk.core.JSONSerializable;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.listeners.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LaunchSession implements JSONSerializable, JSONDeserializable {

    protected String appId;
    protected String appName;
    protected Object rawData;
    protected DeviceService service;
    protected String sessionId;
    protected LaunchSessionType sessionType;

    public enum LaunchSessionType {
        Unknown,
        App,
        ExternalInputPicker,
        Media,
        WebApp
    }

    public static LaunchSession launchSessionForAppId(String appId) {
        LaunchSession launchSession = new LaunchSession();
        launchSession.appId = appId;
        return launchSession;
    }

    public static LaunchSession launchSessionFromJSONObject(JSONObject json) {
        LaunchSession launchSession = new LaunchSession();
        try {
            launchSession.fromJSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return launchSession;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public DeviceService getService() {
        return this.service;
    }

    public void setService(DeviceService service) {
        this.service = service;
    }

    public Object getRawData() {
        return this.rawData;
    }

    public void setRawData(Object rawData) {
        this.rawData = rawData;
    }

    public LaunchSessionType getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(LaunchSessionType sessionType) {
        this.sessionType = sessionType;
    }

    public void close(ResponseListener<Object> listener) {
        this.service.closeLaunchSession(this, listener);
    }

    @Override 
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("appId", this.appId);
        jSONObject.putOpt("sessionId", this.sessionId);
        jSONObject.putOpt("name", this.appName);
        jSONObject.putOpt("sessionType", this.sessionType.name());
        DeviceService deviceService = this.service;
        if (deviceService != null) {
            jSONObject.putOpt("serviceName", deviceService.getServiceName());
        }
        Object obj = this.rawData;
        if (obj != null) {
            if (obj instanceof JSONObject) {
                jSONObject.putOpt("rawData", obj);
            }
            if (this.rawData instanceof List) {
                JSONArray jSONArray = new JSONArray();
                for (Object obj2 : (List) this.rawData) {
                    jSONArray.put(obj2);
                }
                jSONObject.putOpt("rawData", jSONArray);
            }
            if (this.rawData instanceof Object[]) {
                JSONArray jSONArray2 = new JSONArray();
                for (Object obj3 : (Object[]) this.rawData) {
                    jSONArray2.put(obj3);
                }
                jSONObject.putOpt("rawData", jSONArray2);
            }
            Object obj4 = this.rawData;
            if (obj4 instanceof String) {
                jSONObject.putOpt("rawData", obj4);
            }
        }
        return jSONObject;
    }

    public void fromJSONObject(JSONObject obj) throws JSONException {
        this.appId = obj.optString("appId");
        this.sessionId = obj.optString("sessionId");
        this.appName = obj.optString("name");
        this.sessionType = LaunchSessionType.valueOf(obj.optString("sessionType"));
        this.rawData = obj.opt("rawData");
    }

    public boolean equals(Object launchSession) {
        return super.equals(launchSession);
    }
}
