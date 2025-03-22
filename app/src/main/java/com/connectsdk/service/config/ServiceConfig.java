package com.connectsdk.service.config;

import com.connectsdk.core.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;


public class ServiceConfig {
    public static final String KEY_CLASS = "class";
    public static final String KEY_LAST_DETECT = "lastDetection";
    public static final String KEY_UUID = "UUID";
    boolean connected;
    private long lastDetected;
    public ServiceConfigListener listener;
    private String serviceUUID;
    boolean wasConnected;

    
    public interface ServiceConfigListener {
        void onServiceConfigUpdate(ServiceConfig serviceConfig);
    }

    public ServiceConfig(String serviceUUID) {
        this.lastDetected = Long.MAX_VALUE;
        this.serviceUUID = serviceUUID;
    }

    public ServiceConfig(ServiceDescription desc) {
        this.lastDetected = Long.MAX_VALUE;
        this.serviceUUID = desc.getUUID();
        this.connected = false;
        this.wasConnected = false;
        this.lastDetected = Util.getTime();
    }

    public ServiceConfig(ServiceConfig config) {
        this.lastDetected = Long.MAX_VALUE;
        this.serviceUUID = config.serviceUUID;
        this.connected = config.connected;
        this.wasConnected = config.wasConnected;
        this.lastDetected = config.lastDetected;
        this.listener = config.listener;
    }

    public ServiceConfig(JSONObject json) {
        this.lastDetected = Long.MAX_VALUE;
        this.serviceUUID = json.optString("UUID");
        this.lastDetected = json.optLong("lastDetection");
    }

    public static ServiceConfig getConfig(JSONObject json) {
        try {
            return (ServiceConfig) Class.forName(ServiceConfig.class.getPackage().getName() + "." + json.optString("class")).getConstructor(JSONObject.class).newInstance(json);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public String getServiceUUID() {
        return this.serviceUUID;
    }

    public void setServiceUUID(String serviceUUID) {
        this.serviceUUID = serviceUUID;
        notifyUpdate();
    }

    public String toString() {
        return this.serviceUUID;
    }

    public long getLastDetected() {
        return this.lastDetected;
    }

    public void setLastDetected(long value) {
        this.lastDetected = value;
        notifyUpdate();
    }

    public void detect() {
        setLastDetected(Util.getTime());
    }

    public ServiceConfigListener getListener() {
        return this.listener;
    }

    public void setListener(ServiceConfigListener listener) {
        this.listener = listener;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("class", getClass().getSimpleName());
            jSONObject.put("lastDetection", this.lastDetected);
            jSONObject.put("UUID", this.serviceUUID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    
    public void notifyUpdate() {
        ServiceConfigListener serviceConfigListener = this.listener;
        if (serviceConfigListener != null) {
            serviceConfigListener.onServiceConfigUpdate(this);
        }
    }
}
