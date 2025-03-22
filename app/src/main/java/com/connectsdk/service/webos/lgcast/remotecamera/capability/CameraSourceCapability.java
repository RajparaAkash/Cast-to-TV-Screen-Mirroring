package com.connectsdk.service.webos.lgcast.remotecamera.capability;

import android.content.Context;
import android.util.Size;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraUtility;
import com.lge.lib.lgcast.iface.MasterKey;
import com.lge.lib.lgcast.iface.MasterKeyFactoryIF;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class CameraSourceCapability {
    public ArrayList<Size> mSupportedPreviewSizes;
    public ArrayList<MasterKey> masterKeys;

    public static CameraSourceCapability create(Context context, String encodedPublicKey) {
        CameraSourceCapability cameraSourceCapability = new CameraSourceCapability();
        cameraSourceCapability.masterKeys = new MasterKeyFactoryIF().createKeys(encodedPublicKey);
        cameraSourceCapability.mSupportedPreviewSizes = new ArrayList<>();
        for (Size size : CameraUtility.getSupportedPreviewSizes(context)) {
            boolean z = false;
            boolean z2 = size.getWidth() <= 1280 && size.getHeight() <= 720;
            if (size.getWidth() >= 320 && size.getHeight() >= 240) {
                z = true;
            }
            if (z2 && z) {
                cameraSourceCapability.mSupportedPreviewSizes.add(size);
            }
        }
        return cameraSourceCapability;
    }

    public JSONObject toJSONObject() {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            Iterator<MasterKey> it = this.masterKeys.iterator();
            while (it.hasNext()) {
                MasterKey next = it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mki", next.mkiSecureText);
                jSONObject.put("key", next.keySecureText);
                jSONArray.put(jSONObject);
            }
            Iterator<Size> it2 = this.mSupportedPreviewSizes.iterator();
            while (it2.hasNext()) {
                Size next2 = it2.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("w", next2.getWidth());
                jSONObject2.put("h", next2.getHeight());
                jSONArray2.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("crypto", jSONArray);
            jSONObject3.put("previewSize", jSONArray2);
            return jSONObject3;
        } catch (JSONException exception) {
            return new JSONObject();
        }
    }

    public void debug() {
        Iterator<Size> it = this.mSupportedPreviewSizes.iterator();
        while (it.hasNext()) {
            Size next = it.next();
            Logger.debug("Preview size: %d x %d", Integer.valueOf(next.getWidth()), Integer.valueOf(next.getHeight()));
        }
        Logger.debug("", new Object[0]);
    }
}
