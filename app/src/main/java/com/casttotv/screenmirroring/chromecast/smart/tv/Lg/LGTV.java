package com.casttotv.screenmirroring.chromecast.smart.tv.Lg;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.google.android.gms.cast.CastStatusCodes;
import com.stealthcopter.networktools.ARPInfo;
import com.stealthcopter.networktools.WakeOnLan;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class LGTV extends ContextWrapper {
    public static final String BTN_DOWN = "DOWN";
    public static final String BTN_UP = "UP";
    public static final String DEFAULT_LGTV_IP = "192.168.1.10";
    private static final String APP_AMAZON = "amazon";
    private static final String APP_AV1 = "com.webos.app.externalinput.av1";
    private static final String APP_COMPONENT = "com.webos.app.externalinput.component";
    private static final String APP_HDMI1 = "com.webos.app.hdmi1";
    private static final String APP_HDMI2 = "com.webos.app.hdmi2";
    private static final String APP_HDMI3 = "com.webos.app.hdmi3";
    private static final String APP_ID = "id";
    private static final String APP_LIVE_TV = "com.webos.app.livetv";
    private static final String APP_NETFLIX = "netflix";
    private static final String APP_SMART_SHARE = "com.webos.app.smartshare";
    private static final String APP_USER_GUIDE = "com.webos.app.tvuserguide";
    private static final String APP_YOUTUBE = "youtube.leanback.v4";
    private static final String BTN_BACK = "BACK";
    private static final String BTN_BLUE = "BLUE";
    private static final String BTN_DASH = "DASH";
    private static final String BTN_ENTER = "ENTER";
    private static final String BTN_EXIT = "EXIT";
    private static final String BTN_GOTONEXT = "GOTONEXT";
    private static final String BTN_GOTOPREV = "GOTOPREV";
    private static final String BTN_GREEN = "GREEN";
    private static final String BTN_HOME = "HOME";
    private static final String BTN_LEFT = "LEFT";
    private static final String BTN_RED = "RED";
    private static final String BTN_RIGHT = "RIGHT";
    private static final String BTN_YELLOW = "YELLOW";
    private static final String CHANNEL_NUMBER = "channelNumber";
    private static final String CST_WS = "ws://";
    private static final String DEFAULT_LGTV_PORT = "3000";
    private static final String JS_CLIENT_KEY = "client-key";
    private static final String JS_ERROR = "error";
    private static final String JS_ID = "id";
    private static final String JS_PAYLOAD = "payload";
    private static final String JS_REGISTERED = "registered";
    private static final String JS_RESPONSE = "response";
    private static final String JS_SOCKET_PATH = "socketPath";
    private static final String JS_TYPE = "type";
    private static final String JS_URI = "uri";
    private static final String KEY_CLIENT_KEY = "key_client_key";
    private static final String KEY_TV_IP = "key_tv_ip";
    private static final String KEY_TV_PORT = "key_tv_port";
    private static final String LGTV = "LGTV";
    private static final String MUTE = "mute";
    private static final String PAIRING = "pairing";
    private static final String PAIRING_FILE = "pairing.json";
    private static final String SSAP_3D_OFF = "ssap://com.webos.service.tv.display/set3DOff";
    private static final String SSAP_3D_ON = "ssap://com.webos.service.tv.display/set3DOn";
    private static final String SSAP_APP_BROWSER = "ssap://system.launcher/open";
    private static final String SSAP_APP_LAUNCH = "ssap://system.launcher/launch";
    private static final String SSAP_CHANNEL_DOWN = "ssap://tv/channelDown";
    private static final String SSAP_CHANNEL_UP = "ssap://tv/channelUp";
    private static final String SSAP_EPG = "ssap://tv/getChannelProgramInfo";
    private static final String SSAP_FORWARD = "ssap://media.controls/fastForward";
    private static final String SSAP_INSERT_TEXT = "ssap://com.webos.service.ime/insertText";
    private static final String SSAP_MOUSE_SOCKET = "ssap://com.webos.service.networkinput/getPointerInputSocket";
    private static final String SSAP_MUTE = "ssap://audio/setMute";
    private static final String SSAP_OFF = "ssap://system/turnOff";
    private static final String SSAP_ON = "ssap://system/turnOn";
    private static final String SSAP_OPEN_CHANNEL = "ssap://tv/openChannel";
    private static final String SSAP_PAUSE = "ssap://media.controls/pause";
    private static final String SSAP_PLAY = "ssap://media.controls/play";
    private static final String SSAP_REWIND = "ssap://media.controls/rewind";
    private static final String SSAP_STOP = "ssap://media.controls/stop";
    private static final String SSAP_UPDATE_INPUT = "ssap://tv/switchInput";
    private static final String SSAP_VOLUME_DOWN = "ssap://audio/volumeDown";
    private static final String SSAP_VOLUME_UP = "ssap://audio/volumeUp";
    private static final int WEBSOCKET_TIMEOUT = 5;
    private static final String WS_REGISTER = "register";
    private static final String WS_REQUEST = "request";
    public static WebSocketClient mWebSocketClient = null;
    private static String client_key = null;
    private static boolean mConnected = false;
    private static WebSocketClient mInputSocket = null;
    private static boolean m_3d_on = false;
    private static boolean m_debugMode = false;
    private static boolean m_isMute = true;
    private static String m_keyName = null;
    private static int nextRequestId = 1;
    private Context context;
    private String myIP;
    private String myPort;

    public LGTV(Context context2) {
        super(context2);
        this.context = context2;
    }

    private static boolean is_registered() {
        return !client_key.isEmpty();
    }

    private static String getSimpleURL(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "request");
            if (str == null) {
                jSONObject.put("id", 0);
            } else {
                StringBuilder append = new StringBuilder().append(str);
                int i = nextRequestId;
                nextRequestId = i + 1;
                jSONObject.put("id", append.append(i).toString());
            }
            jSONObject.put("uri", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String getSimpleURL(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "request");
            int i = nextRequestId;
            nextRequestId = i + 1;
            jSONObject.put("id", String.valueOf(i));
            jSONObject.put("uri", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String getPayloadURL(String str, String str2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str2, z);
            jSONObject.put("type", "request");
            int i = nextRequestId;
            nextRequestId = i + 1;
            jSONObject.put("id", String.valueOf(i));
            jSONObject.put("uri", str);
            jSONObject.put("payload", jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String getPayloadURL(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str2, str3);
            jSONObject.put("type", "request");
            int i = nextRequestId;
            nextRequestId = i + 1;
            jSONObject.put("id", String.valueOf(i));
            jSONObject.put("uri", str);
            jSONObject.put("payload", jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String getRegisterURL(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", WS_REGISTER);
            jSONObject.put("id", "register_0");
            if (is_registered()) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str);
                    jSONObject2.put(JS_CLIENT_KEY, client_key);
                    jSONObject.put("payload", jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                jSONObject.put("payload", str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String getButtonURL(String str) {
        m_keyName = str;
        return getSimpleURL(null, SSAP_MOUSE_SOCKET);
    }

    public static boolean isWifiAvailable(Context context2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || !networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return false;
            }
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public void sendText(String str) {
    }

    public String getMyIP() {
        return this.myIP;
    }

    public void setMyIP(String str) {
        this.myIP = str;
    }

    public String getMyPort() {
        return this.myPort;
    }

    public void resolveIP() {
        if (this.myIP.split("\\.").length < 4) {
            try {
                Thread thread = new Thread(new Runnable() {

                    public void run() {
                        try {
                            InetAddress byName = InetAddress.getByName(LGTV.this.myIP);
                            LGTV.this.myIP = byName.getHostAddress();
                            Log.d("IP", LGTV.this.myIP);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (e.getMessage() != null) {
                                Log.e("MESSAGE_ERROR", e.getMessage());
                                LGTV.this.postToastMessage("Exception: " + e.getMessage(), 1);
                            }
                        }
                    }
                });
                thread.start();
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage() != null) {
                    Log.e("MESSAGE_ERROR", e.getMessage());
                    postToastMessage("Exception: " + e.getMessage(), 1);
                }
            }
        }
    }

    public void saveIPPreference() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putString(KEY_TV_IP, getMyIP());
        edit.apply();
    }

    public void loadMainPreferences() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.myIP = defaultSharedPreferences.getString(KEY_TV_IP, DEFAULT_LGTV_IP);
        this.myPort = defaultSharedPreferences.getString(KEY_TV_PORT, DEFAULT_LGTV_PORT);
        client_key = defaultSharedPreferences.getString(KEY_CLIENT_KEY, "");
    }

    public String getDecodeurURL(String str) {
        return getDecodeurURL(getMyIP(), str);
    }

    public String getDecodeurURL(String str, String str2) {
        return getDecodeurURL(str, str2, "");
    }

    public String getDecodeurURL(String str, String str2, String str3) {
        return CST_WS + str + ":" + getMyPort();
    }

    public String getDecodeurBasicURL(String str) {
        String str2;
        try {
            InputStream open = getAssets().open(PAIRING_FILE);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            str2 = new String(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            str2 = "";
        }
        return getRegisterURL(str2);
    }

    public void ExecuteURL(String str, String str2) {
        resolveIP();
        ExecuteURL(getMyIP(), str, str2);
    }


    public void ExecuteURL(String str, String str2, String str3) {
        try {
            WebSocketClient webSocketClient = mWebSocketClient;
            if (webSocketClient != null && webSocketClient.isOpen() && mWebSocketClient.getConnection() != null && (mWebSocketClient.getConnection() == null || mWebSocketClient.getConnection().isOpen())) {
                String str4 = m_keyName;
                if (str4 == null) {
                    if (str4 != null) {
                        WebSocketClient webSocketClient2 = mInputSocket;
                        if (webSocketClient2 != null) {
                            webSocketClient2.send("type:button\nname:" + m_keyName + "\n\n");
                            m_keyName = null;
                            return;
                        } else if (m_debugMode) {
                            Toast.makeText(this, context.getString(R.string.toast_input_socket_is_null), Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            return;
                        }
                    } else {
                        mWebSocketClient.send(str2);
                        return;
                    }
                }
            }
            connectWebSocket(getDecodeurURL(str, ""), str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
            if (m_debugMode) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void wakeOnLan() {
        if (!isWifiAvailable(this)) {
            postToastMessage(getString(R.string.wifi_not_connected), 1);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String myIP2 = getMyIP();
                        if (myIP2.split("\\.").length != 4) {
                            myIP2 = new String(InetAddress.getByName(myIP2).getAddress());
                        }
                        Log.d("WOL", "IP: " + myIP2);
                        String mACFromIPAddress = ARPInfo.getMACFromIPAddress(myIP2);
                        Log.d("WOL", "MAC address: " + mACFromIPAddress);
                        WakeOnLan.sendWakeOnLan(myIP2, mACFromIPAddress);
                        if (m_debugMode) {
                            postToastMessage("Power On (MAC: " + mACFromIPAddress + ")", 1);
                        } else {
                            postToastMessage("Power On", 0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("WOL", "Exception: " + e.getMessage());
                        if (m_debugMode) {
                            postToastMessage("Exception: " + e.getMessage(), 1);
                        }
                    }
                }
            }).start();
        }
    }

    public void send_key(String str, KEY_INDEX key_index) {
        switch (AnonymousClass6.$SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[key_index.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                ExecuteURL(getPayloadURL(SSAP_OPEN_CHANNEL, CHANNEL_NUMBER, str), str);
                return;
            case 11:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_LIVE_TV), str);
                return;
            case 12:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_YOUTUBE), str);
                return;
            case 13:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_NETFLIX), str);
                return;
            case 14:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_AMAZON), str);
                return;
            case 15:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_HDMI1), str);
                return;
            case 16:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_HDMI2), str);
                return;
            case 17:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_HDMI3), str);
                return;
            case 18:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_COMPONENT), str);
                return;
            case 19:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_AV1), str);
                return;
            case 20:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_USER_GUIDE), str);
                return;
            case 21:
                ExecuteURL(getPayloadURL(SSAP_APP_LAUNCH, "id", APP_SMART_SHARE), str);
                return;
            case 22:
                ExecuteURL(getSimpleURL(SSAP_ON), str);
                wakeOnLan();
                return;
            case 23:
                ExecuteURL(getSimpleURL(SSAP_OFF), str);
                return;
            case 24:
                ExecuteURL(getPayloadURL(SSAP_MUTE, "mute", m_isMute), str);
                m_isMute = !m_isMute;
                return;
            case 25:
                ExecuteURL(getSimpleURL(SSAP_VOLUME_UP), str);
                return;
            case 26:
                ExecuteURL(getSimpleURL(SSAP_VOLUME_DOWN), str);
                return;
            case 27:
                ExecuteURL(getSimpleURL(SSAP_CHANNEL_UP), str);
                return;
            case 28:
                ExecuteURL(getSimpleURL(SSAP_CHANNEL_DOWN), str);
                return;
            case 29:
                ExecuteURL(getSimpleURL(SSAP_PLAY), str);
                return;
            case 30:
                ExecuteURL(getSimpleURL(SSAP_PAUSE), str);
                return;
            case 31:
                ExecuteURL(getSimpleURL(SSAP_STOP), str);
                return;
            case 32:
                ExecuteURL(getSimpleURL(SSAP_REWIND), str);
                return;
            case 33:
                ExecuteURL(getSimpleURL(SSAP_FORWARD), str);
                return;
            case 34:
                ExecuteURL(getButtonURL(BTN_GOTONEXT), str);
                return;
            case 35:
                ExecuteURL(getButtonURL(BTN_GOTOPREV), str);
                return;
            case 36:
                ExecuteURL(getSimpleURL(SSAP_EPG), str);
                return;
            case 37:
                ExecuteURL(getSimpleURL(SSAP_UPDATE_INPUT), str);
                return;
            case 38:
                ExecuteURL(getSimpleURL(SSAP_APP_BROWSER), str);
                return;
            case 39:
                ExecuteURL(getButtonURL(BTN_BACK), str);
                return;
            case 40:
                ExecuteURL(getButtonURL(BTN_UP), str);
                return;
            case 41:
                ExecuteURL(getButtonURL(BTN_DOWN), str);
                return;
            case 42:
                ExecuteURL(getButtonURL(BTN_LEFT), str);
                return;
            case 43:
                ExecuteURL(getButtonURL(BTN_RIGHT), str);
                return;
            case 44:
                ExecuteURL(getButtonURL(BTN_ENTER), str);
                return;
            case 45:
                ExecuteURL(getButtonURL(BTN_EXIT), str);
                return;
            case 46:
                ExecuteURL(getButtonURL(BTN_DASH), str);
                return;
            case 47:
                ExecuteURL(getButtonURL(BTN_HOME), str);
                return;
            case 48:
                ExecuteURL(getButtonURL(BTN_RED), str);
                return;
            case 49:
                ExecuteURL(getButtonURL(BTN_GREEN), str);
                return;
            case 50:
                ExecuteURL(getButtonURL(BTN_YELLOW), str);
                return;
            case 51:
                ExecuteURL(getButtonURL(BTN_BLUE), str);
                return;
            case 52:
                if (m_3d_on) {
                    ExecuteURL(getSimpleURL(SSAP_3D_OFF), str + " off");
                } else {
                    ExecuteURL(getSimpleURL(SSAP_3D_ON), str + " on");
                }
                m_3d_on = !m_3d_on;
                return;
            default:
                return;
        }
    }

    public void TV_Pairing() {
        try {
            if (isWifiAvailable(this)) {
                ExecuteURL(getDecodeurBasicURL(""), "pairing");
            } else {
                Toast.makeText(this, getString(R.string.wifi_not_connected), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (m_debugMode) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void connectWebSocket(String str, final String str2, String str3) {
        try {
            try {
                WebSocketClient r9 = new WebSocketClient(new URI(str), new Draft_6455(), null, 1000) {

                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        Log.i("Websocket", "Opened");
                        if (!LGTV.this.getDecodeurBasicURL("").equalsIgnoreCase(str2)) {
                            send(LGTV.this.getDecodeurBasicURL(""));
                            SystemClock.sleep(400);
                        }
                        send(str2);
                    }

                    @Override
                    public void onMessage(String str) {
                        Log.i("Websocket", "onMessage: " + str);
                        try {
                            LGTV.this.handleMessage(new JSONObject(str));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onClose(int i, String str, boolean z) {
                        Log.i("Websocket", "Closed: " + str);
                    }

                    @Override
                    public void onError(Exception exc) {
                        Log.e("Websocket", "Error: " + exc.getMessage());
                        LGTV.this.postToastMessage(exc.getMessage(), 1);
                    }
                };
                mWebSocketClient = r9;
                r9.setConnectionLostTimeout(5);
                mWebSocketClient.connect();
            } catch (Exception e) {
                e.printStackTrace();
                mConnected = false;
                Log.e("Websocket", "Exception: " + e.getMessage());
                if (m_debugMode) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            mConnected = false;
            if (m_debugMode) {
                Toast.makeText(this, e2.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /* CODE */
    public void handleMessage1(JSONObject jSONObject) {
        int i;
        String str;
        Exception e;
        String optString = jSONObject.optString("type");
        Object opt = jSONObject.opt("payload");
        if (optString.length() != 0) {
            if (JS_RESPONSE.equals(optString)) {
                if (opt != null) {
                    try {
                        String str2 = (String) ((JSONObject) opt).get(JS_SOCKET_PATH);
                        if (!str2.isEmpty()) {
                            try {
                                connectPointer(new URI(str2));
                            } catch (URISyntaxException e2) {
                                e2.printStackTrace();
                                if (m_debugMode) {
                                    postToastMessage("URISyntaxException: " + e2.getMessage(), 1);
                                }
                            }
                        }
                    } catch (JSONException e3) {
                        Log.d(LGTV, "JSONException: " + e3.getMessage());
                    }
                }
            } else if (JS_REGISTERED.equals(optString)) {
                if (opt instanceof JSONObject) {
                    client_key = ((JSONObject) opt).optString(JS_CLIENT_KEY);
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                    edit.putString(KEY_CLIENT_KEY, client_key);
                    edit.apply();
                }
            } else if ("error".equals(optString)) {
                String optString2 = jSONObject.optString("error");
                if (optString2.length() != 0) {
                    String str3 = null;
                    try {
                        String[] split = optString2.split(" ", 2);
                        i = Integer.parseInt(split[0]);
                        try {
                            str3 = split[1];
                        } catch (Exception e4) {
                            e = e4;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        i = -1;
                        e.printStackTrace();
                        if (opt == null) {
                        }
                        if (jSONObject.has("id")) {
                        }
                        if (i != -1) {
                        }
                        if (m_debugMode) {
                            return;
                        }
                    }
                    if (opt == null) {
                        str = "Error Payload: " + opt.toString() + " ";
                        Log.d(LGTV, str);
                    } else {
                        str = "";
                    }
                    if (jSONObject.has("id")) {
                        str = str + "Error Desc: " + str3 + " ";
                        Log.d(LGTV, str);
                    }
                    if (i != -1) {
                        str = str + "Error code: " + i + " ";
                        Log.d(LGTV, str);
                    }
                    if (m_debugMode && !str.isEmpty()) {
                        postToastMessage(str, 1);
                    }
                }
            }
        }
    }

    protected void handleMessage(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("type");
        final Object opt = jsonObject.opt("payload");
        if (optString.length() == 0) {
            return;
        }
        if ("response".equals(optString)) {
            if (opt == null) {
                return;
            }
            try {
                final String str = (String) ((JSONObject) opt).get("socketPath");
                if (!str.isEmpty()) {
                    try {
                        this.connectPointer(new URI(str));
                    } catch (final URISyntaxException ex) {
                        ex.printStackTrace();
                        if (com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.m_debugMode) {
                            this.postToastMessage("URISyntaxException: " + ex.getMessage(), 1);
                        }
                    }
                }
                return;
            } catch (final JSONException ex2) {
                Log.d("LGTV", "JSONException: " + ex2.getMessage());
                return;
            }
        }
        if ("registered".equals(optString)) {
            if (opt instanceof JSONObject) {
                com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.client_key = ((JSONObject) opt).optString("client-key");
                final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).edit();
                edit.putString("key_client_key", com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.client_key);
                edit.apply();
            }
        } else if ("error".equals(optString)) {
            final String optString2 = jsonObject.optString("error");
            if (optString2.length() == 0) {
                return;
            }
            String str2 = null;
            int int1;
            try {
                final String[] split = optString2.split(" ", 2);
                int1 = Integer.parseInt(split[0]);
                str2 = split[1];
            } catch (final Exception ex3) {
                int1 = -1;
                ex3.printStackTrace();
            }
            String string;
            if (opt != null) {
                string = "Error Payload: " + opt.toString() + " ";
                Log.d("LGTV", string);
            } else {
                string = "";
            }
            String string2 = string;
            if (jsonObject.has("id")) {
                string2 = string + "Error Desc: " + str2 + " ";
                Log.d("LGTV", string2);
            }
            String string3 = string2;
            if (int1 != -1) {
                string3 = string2 + "Error code: " + int1 + " ";
                Log.d("LGTV", string3);
            }
            if (com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.m_debugMode && !string3.isEmpty()) {
                this.postToastMessage(string3, 1);
            }
        }
    }

    public void postToastMessage(String str, int i) {
        try {
            Context context2 = this.context;
            if (context2 instanceof Activity) {
                ((Activity) context2).isFinishing();
            } else {
                Toast.makeText(context2, context2.getString(R.string.error_try_again), i).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void trustEveryone() {
        try {
            Log.d(" TV", "Trust everyone !");
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String str, SSLSession sSLSession) {
                    Log.d("TRUST", str);
                    return true;
                }
            });
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new X509TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                    Log.d("TRUST", "checkClientTrusted - authType = " + str);
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                    Log.d("TRUST", "checkServerTrusted - authType = " + str);
                }

                public X509Certificate[] getAcceptedIssuers() {
                    Log.d("TRUST", "getAcceptedIssuers");
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TRUST", "Trust exception = " + e.getMessage());
            if (m_debugMode) {
                postToastMessage("Trust exception = " + e.getMessage(), 1);
            }
        }
    }

    public void connectPointer(URI uri) {
        try {
            WebSocketClient webSocketClient = mInputSocket;
            if (webSocketClient != null) {
                webSocketClient.close();
                mInputSocket = null;
            }
            mInputSocket = new WebSocketClient(uri, new Draft_6455(), null, CastStatusCodes.AUTHENTICATION_FAILED) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.d("PtrAndKeyboardFragment", "connected to " + this.uri.toString());
                    com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.mInputSocket.send("type:button\nname:" + com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV.m_keyName + "\n\n");
                }

                @Override
                public void onMessage(String str) {
                    Log.i("Inputsocket", "onMessage: " + str);
                }

                @Override
                public void onError(Exception exc) {
                    Log.e("Inputsocket", "Error: " + exc.getMessage());
                    LGTV.this.postToastMessage("Inputsocket: " + exc.getMessage(), 1);
                }

                @Override
                public void onClose(int i, String str, boolean z) {
                    Log.i("Inputsocket", "Closed: " + str);
                }
            };
        } catch (Exception e) {
            Log.e("WebSocketClient", "Exception: " + e.getMessage());
            e.printStackTrace();
            postToastMessage("WebSocketClient: " + e.getMessage(), 1);
        }
        mInputSocket.connect();
    }

    public enum KEY_INDEX {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        HEIGHT,
        NINE,
        TV,
        YOUTUBE,
        NETFLIX,
        AMAZON,
        HDMI1,
        HDMI2,
        HDMI3,
        COMPONENT,
        AV1,
        GUIDE,
        SMART_SHARE,
        ON,
        OFF,
        MUTE,
        VOLUME_INCREASE,
        VOLUME_DECREASE,
        CHANNEL_INCREASE,
        CHANNEL_DECREASE,
        PLAY,
        PAUSE,
        STOP,
        REWIND,
        FORWARD,
        NEXT,
        PREVIOUS,
        PROGRAM,
        SOURCE,
        INTERNET,
        BACK,
        UP,
        DOWN,
        LEFT,
        RIGHT,
        ENTER,
        EXIT,
        DASH,
        HOME,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        THREE_D;

        public static KEY_INDEX fromInt(int i) {
            return values()[i];
        }
    }

    public static class AnonymousClass6 {
        static final int[] $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX;

        static {
            int[] iArr = new int[KEY_INDEX.values().length];
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX = iArr;
            iArr[KEY_INDEX.ZERO.ordinal()] = 1;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.ONE.ordinal()] = 2;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.TWO.ordinal()] = 3;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.THREE.ordinal()] = 4;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.FOUR.ordinal()] = 5;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.FIVE.ordinal()] = 6;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.SIX.ordinal()] = 7;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.SEVEN.ordinal()] = 8;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.HEIGHT.ordinal()] = 9;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.NINE.ordinal()] = 10;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.TV.ordinal()] = 11;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.YOUTUBE.ordinal()] = 12;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.NETFLIX.ordinal()] = 13;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.AMAZON.ordinal()] = 14;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.HDMI1.ordinal()] = 15;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.HDMI2.ordinal()] = 16;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.HDMI3.ordinal()] = 17;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.COMPONENT.ordinal()] = 18;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.AV1.ordinal()] = 19;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.GUIDE.ordinal()] = 20;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.SMART_SHARE.ordinal()] = 21;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.ON.ordinal()] = 22;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.OFF.ordinal()] = 23;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.MUTE.ordinal()] = 24;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.VOLUME_INCREASE.ordinal()] = 25;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.VOLUME_DECREASE.ordinal()] = 26;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.CHANNEL_INCREASE.ordinal()] = 27;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.CHANNEL_DECREASE.ordinal()] = 28;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.PLAY.ordinal()] = 29;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.PAUSE.ordinal()] = 30;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.STOP.ordinal()] = 31;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.REWIND.ordinal()] = 32;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.FORWARD.ordinal()] = 33;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.NEXT.ordinal()] = 34;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.PREVIOUS.ordinal()] = 35;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.PROGRAM.ordinal()] = 36;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.SOURCE.ordinal()] = 37;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.INTERNET.ordinal()] = 38;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.BACK.ordinal()] = 39;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.UP.ordinal()] = 40;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.DOWN.ordinal()] = 41;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.LEFT.ordinal()] = 42;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.RIGHT.ordinal()] = 43;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.ENTER.ordinal()] = 44;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.EXIT.ordinal()] = 45;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.DASH.ordinal()] = 46;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.HOME.ordinal()] = 47;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.RED.ordinal()] = 48;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.GREEN.ordinal()] = 49;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.YELLOW.ordinal()] = 50;
            $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.BLUE.ordinal()] = 51;
            try {
                $SwitchMap$com$ezremote$allremotetv$lg$LGTV$KEY_INDEX[KEY_INDEX.THREE_D.ordinal()] = 52;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

}
