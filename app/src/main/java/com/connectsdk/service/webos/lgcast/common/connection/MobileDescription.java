package com.connectsdk.service.webos.lgcast.common.connection;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.connectsdk.service.webos.lgcast.common.utils.IOUtil;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import org.json.JSONException;
import org.json.JSONObject;


public class MobileDescription {
    private static final String KEY_DEVICE_NAME = "deviceName";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_MODEL_NAME = "modelName";
    private static final String KEY_PLATFORM = "platform";
    private static final String KEY_TYPE = "type";
    private static final String KEY_VERSION = "version";
    private static final String VAL_ANDROID = "android";
    private static final String VAL_PHONE = "phone";
    public String deviceName;
    public String version;
    public String type = VAL_PHONE;
    public String platform = "android";
    public String manufacturer = Build.MANUFACTURER;
    public String modelName = Build.MODEL;

    public MobileDescription(Context context) {
        this.version = IOUtil.readRawResourceText(context, R.raw.lgcast_version);
        String string = Settings.Global.getString(context.getContentResolver(), "device_name");
        this.deviceName = string;
        if (string == null || string.length() == 0) {
            this.deviceName = Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        }
        String str = this.deviceName;
        if (str == null || str.length() == 0) {
            this.deviceName = Build.MODEL;
        }
    }

    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.type);
            jSONObject.put(KEY_PLATFORM, this.platform);
            jSONObject.put("version", this.version);
            jSONObject.put("manufacturer", this.manufacturer);
            jSONObject.put("modelName", this.modelName);
            jSONObject.put(KEY_DEVICE_NAME, this.deviceName);
            return jSONObject;
        } catch (JSONException exception) {
            return new JSONObject();
        }
    }

    public void debug() {
        Logger.debug("type=" + this.type, new Object[0]);
        Logger.debug("platform=" + this.platform, new Object[0]);
        Logger.debug("version=" + this.version, new Object[0]);
        Logger.debug("manufacturer=" + this.manufacturer, new Object[0]);
        Logger.debug("modelName=" + this.modelName, new Object[0]);
        Logger.debug("deviceName=" + this.deviceName, new Object[0]);
    }
}
