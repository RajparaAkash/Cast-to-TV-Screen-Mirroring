package com.connectsdk.service.google_cast;

import com.connectsdk.core.Util;
import com.connectsdk.service.sessions.CastWebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

import org.json.JSONException;
import org.json.JSONObject;


public class CastServiceChannel implements Cast.MessageReceivedCallback {
    final CastWebAppSession session;
    final String webAppId;

    public String getNamespace() {
        return "urn:x-cast:com.connectsdk";
    }

    public CastServiceChannel(String webAppId, CastWebAppSession session) {
        this.webAppId = webAppId;
        this.session = session;
    }

    @Override
    public void onMessageReceived(CastDevice castDevice, String namespace, final String message) {
        final WebAppSessionListener webAppSessionListener = this.session.getWebAppSessionListener();
        if (webAppSessionListener == null) {
            return;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(message);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        JSONObject finalJSONObject = jSONObject;
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (finalJSONObject == null) {
                    webAppSessionListener.onReceiveMessage(CastServiceChannel.this.session, message);
                } else {
                    webAppSessionListener.onReceiveMessage(CastServiceChannel.this.session, finalJSONObject);
                }
            }
        });
    }
}
