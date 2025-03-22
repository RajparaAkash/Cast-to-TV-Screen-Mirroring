package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung;

import android.content.Context;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.SharePreferenceUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebSocketUtils {
    public static final WebSocketUtils INSTANCE = new WebSocketUtils();
    private static final String TAG = "WebSocketUtils";
    private static WebSocket webSocket;

    private WebSocketUtils() {
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket2) {
        webSocket = webSocket2;
    }

    public String getTAG() {
        return TAG;
    }

    public void initWebSocket(Context context, String str, String str2, WebSocketListener webSocketListener) {
        WebSocketFactory verifyHostname = new WebSocketFactory().setConnectionTimeout(5000).setVerifyHostname(false);
        try {
            verifyHostname.setSSLContext(NaiveSSLContext.INSTANCE.getInstance("TLS"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            webSocket = verifyHostname.createSocket("wss://" + str + ":8002/api/v2/channels/samsung.remote.control?name=" + str2 + "&token=" + SharePreferenceUtils.getPrefsString(context, "samsung_token"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        if (webSocket != null) {
            webSocket.connect(newSingleThreadExecutor);
        }
        if (webSocket != null) {
            webSocket.addListener(webSocketListener);
        }
    }

    public void sendKey(String str) {
        if (webSocket != null) {
            webSocket.sendText("{\"method\": \"ms.remote.control\",\"params\": {\"Cmd\": \"Click\",\"DataOfCmd\": \"" + str + "\",\"Option\": \"false\",\"TypeOfRemote\": \"SendRemoteKey\"}}");
        }
    }

    public void getAppOnSamSung(WebSocketListener webSocketListener) {
        if (webSocket != null) {
            webSocket.sendText("{\"method\": \"ms.channel.emit\", \"params\": { \"data\": \"\", \"event\": \"ed.installedApp.get\", \"to\": \"host\" }} ");
        }
        if (webSocket != null) {
            webSocket.addListener(webSocketListener);
        }
    }

    public void openAppOnSamSung(String str, int i) {
        String str2 = i == 2 ? "DEEP_LINK" : "NATIVE_LAUNCH";
        if (webSocket != null) {
            webSocket.sendText("{\"method\": \"ms.channel.emit\", \"params\": { \"data\": { \"action_type\" : \"" + str2 + "\", \"appId\": \"" + str + "\" }, \"event\": \"ed.apps.launch\", \"to\": \"host\" }}");
        }
    }

    public void getAppIcon(String str, WebSocketListener webSocketListener) {
        if (webSocket != null) {
            webSocket.sendText("{\"method\": \"ms.channel.emit\", \"params\": { \"data\":{ \"iconPath\": \"" + str + "\" } , \"event\": \"ed.apps.icon\", \"to\": \"host\" }} ");
        }
        if (webSocket != null) {
            webSocket.addListener(webSocketListener);
        }
    }

    public boolean isConnected() {
        if (webSocket != null) {
            return webSocket.isOpen();
        }
        return false;
    }

    public void closeConnection() {
        if (webSocket != null) {
            webSocket.disconnect();
        }
        webSocket = null;
    }
}
