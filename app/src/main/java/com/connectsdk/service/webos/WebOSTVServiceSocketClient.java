package com.connectsdk.service.webos;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;

import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.WebOSTVServiceConfig;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.net.ssl.SSLContext;

public class WebOSTVServiceSocketClient extends WebSocketClient implements ServiceCommand.ServiceCommandProcessor {

    static final int PORT = 3001;
    static final String Public_Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2At7fSUHuMw6bm/z3Q+X4oY9KpDa1s06\nmht9vNmSkZE5xMo9asOtZAWLLbJLxifY6qz6LWKgNw4Pyk6HVTLFdj4jrV//gNGQvYtCp3HRriqg\n2YoceBNG59+SW3xNzuhUqy5/nerQPfNQiz9z9RqtGj/YWItlJcKrNOBecNmHc7Xmu+3yPN6kD1G2\n6uU8wPBqzMdqFpPcubedIOmh4nNa2sNkfvMkbR4Pk/YupsDpic56dMxX0Twvg6SiaKGjv8NO9Lcv\nhLt2dR2XXi/z2F6uVjP5oYPvlSAK9GHVo96khpafKGPvIwPSSGtlHI4is/yT7WEeLuQs5FD/vAs9\neqQNkQIDAQAB\n";
    static final String WEBOS_PAIRING_COMBINED = "COMBINED";
    static final String WEBOS_PAIRING_PIN = "PIN";
    static final String WEBOS_PAIRING_PROMPT = "PROMPT";
    static boolean verification_status = false;
    LinkedHashSet<ServiceCommand<ResponseListener<Object>>> commandQueue;
    WebOSTVTrustManager customTrustManager;
    boolean mConnectSucceeded;
    Boolean mConnected;
    WebOSTVServiceSocketClientListener mListener;
    DeviceService.PairingType mPairingType;
    JSONObject manifest;
    WebOSTVServiceConfig mconfig;
    int nextRequestId;
    List<String> permissions;
    public SparseArray<ServiceCommand<? extends Object>> requests;
    State state;


    public enum State {
        NONE,
        INITIAL,
        CONNECTING,
        REGISTERING,
        REGISTERED,
        DISCONNECTING
    }

    public interface WebOSTVServiceSocketClientListener {
        void onBeforeRegister(DeviceService.PairingType pairingType);

        void onCloseWithError(ServiceCommandError error);

        void onConnect();

        void onFailWithError(ServiceCommandError error);

        Boolean onReceiveMessage(JSONObject message);

        void onRegistrationFailed(ServiceCommandError error);

        void updateClientKey(String ClientKey);

        void updateIPAddress(String IPAddress);

        void updateUUID(String UUID);
    }

    @Override
    public void unsubscribe(ServiceSubscription<?> subscription) {
    }

    public String getClientKey() {
        return this.mconfig.getClientKey();
    }

    public WebOSTVServiceSocketClient(WebOSTVServiceConfig config, DeviceService.PairingType pairingType, List<String> permissions, URI uri) {
        super(uri);
        this.nextRequestId = 1;
        this.state = State.INITIAL;
        this.commandQueue = new LinkedHashSet<>();
        this.requests = new SparseArray<>();
        this.mConnectSucceeded = false;
        this.mPairingType = pairingType;
        this.mconfig = config;
        this.state = State.INITIAL;
        this.permissions = permissions;
        this.state = State.INITIAL;
        setDefaultManifest();
    }

    public WebOSTVServiceSocketClient(WebOSTVService service, URI uri) {
        super(uri);
        this.nextRequestId = 1;
        this.state = State.INITIAL;
        this.commandQueue = new LinkedHashSet<>();
        this.requests = new SparseArray<>();
        this.mConnectSucceeded = false;
        this.mPairingType = service.getPairingType();
        this.mconfig = service.getWebOSTVServiceConfig();
        this.permissions = service.getPermissions();
        this.state = State.INITIAL;
        setDefaultManifest();
    }

    public static URI getURI(WebOSTVService service) {
        try {
            return new URI("wss://" + service.getServiceDescription().getIpAddress() + ":" + service.getServiceDescription().getPort());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URI getURI(String IpAddress) {
        try {
            return new URI("wss://" + IpAddress + ":3001");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URI getURI(String IpAddress, int port) {
        try {
            return new URI("wss://" + IpAddress + ":" + port);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebOSTVServiceSocketClientListener getListener() {
        return this.mListener;
    }

    public void setListener(WebOSTVServiceSocketClientListener mListener) {
        this.mListener = mListener;
    }

    public State getState() {
        return this.state;
    }

    @Override 
    public void connect() {
        synchronized (this) {
            if (this.state != State.INITIAL) {
                Log.w(Util.T, "already connecting; not trying to connect again: " + this.state);
                return;
            }
            this.state = State.CONNECTING;
            setupSSL();
            super.connect();
        }
    }

    public void disconnect() {
        disconnectWithError(null);
    }

    public void disconnectWithError(ServiceCommandError error) {
        close();
        this.state = State.INITIAL;
        WebOSTVServiceSocketClientListener webOSTVServiceSocketClientListener = this.mListener;
        if (webOSTVServiceSocketClientListener != null) {
            webOSTVServiceSocketClientListener.onCloseWithError(error);
        }
    }

    public void clearRequests() {
        SparseArray<ServiceCommand<? extends Object>> sparseArray = this.requests;
        if (sparseArray != null) {
            sparseArray.clear();
        }
    }

    private void setDefaultManifest() {
        JSONObject jSONObject = new JSONObject();
        this.manifest = jSONObject;
        try {
            jSONObject.put("manifestVersion", 1);
            this.manifest.put("permissions", convertStringListToJSONArray(this.permissions));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray convertStringListToJSONArray(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String str : list) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    @Override 
    public void onOpen(ServerHandshake handshakedata) {
        this.mConnectSucceeded = true;
        handleConnected();
    }

    @Override 
    public void onMessage(String data) {
        Log.d(Util.T, "webOS Socket [IN] : " + data);
        handleMessage(data);
    }

    @Override 
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose: " + code + ": " + reason);
        handleConnectionLost(true, null);
    }

    @Override 
    public void onError(Exception ex) {
        System.err.println("onError: " + ex);
        if (!this.mConnectSucceeded) {
            handleConnectError(ex);
        } else {
            handleConnectionLost(false, ex);
        }
    }

    protected void handleConnected() {
        helloTV();
    }

    protected void handleConnectError(Exception ex) {
        System.err.println("connect error: " + ex.toString());
        WebOSTVServiceSocketClientListener webOSTVServiceSocketClientListener = this.mListener;
        if (webOSTVServiceSocketClientListener != null) {
            webOSTVServiceSocketClientListener.onFailWithError(new ServiceCommandError(0, "connection error", null));
        }
    }

    protected void handleMessage(String data) {
        try {
            handleMessage(new JSONObject(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void handleMessage(JSONObject message) {
        Integer num;
        ServiceCommand<? extends Object> serviceCommand;
        Boolean bool = true;
        WebOSTVServiceSocketClientListener webOSTVServiceSocketClientListener = this.mListener;
        if (webOSTVServiceSocketClientListener != null) {
            bool = webOSTVServiceSocketClientListener.onReceiveMessage(message);
        }
        if (bool.booleanValue()) {
            String optString = message.optString("type");
            Object opt = message.opt("payload");
            String optString2 = message.optString("id");
            String str = null;
            if (isInteger(optString2)) {
                num = Integer.valueOf(optString2);
                try {
                    serviceCommand = this.requests.get(num.intValue());
                } catch (ClassCastException exception) {
                    serviceCommand = null;
                }
            } else {
                num = null;
                serviceCommand = null;
            }
            if (optString.length() == 0) {
                return;
            }
            int i = -1;
            if ("response".equals(optString)) {
                if (serviceCommand != null) {
                    if (opt != null) {
                        Util.postSuccess(serviceCommand.getResponseListener(), opt);
                    } else {
                        Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(-1, "JSON parse error", null));
                    }
                    if (serviceCommand instanceof URLServiceSubscription) {
                        return;
                    }
                    if ((opt instanceof JSONObject) && ((JSONObject) opt).has("pairingType")) {
                        return;
                    }
                    this.requests.remove(num);
                    return;
                }
                System.err.println("no matching request id: " + optString2 + ", payload: " + opt.toString());
            } else if ("registered".equals(optString)) {
                if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
                    this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
                }
                if (opt instanceof JSONObject) {
                    String optString3 = ((JSONObject) opt).optString("client-key");
                    this.mconfig.setClientKey(optString3);
                    this.mListener.updateClientKey(optString3);
                    sendVerification();
                    if (verification_status) {
                        this.mconfig.setServerCertificate(this.customTrustManager.getLastCheckedCertificate());
                        handleRegistered();
                        if (num != null) {
                            this.requests.remove(num.intValue());
                            return;
                        }
                        return;
                    }
                    Log.d(Util.T, "Certification Verification Failed");
                    this.mListener.onRegistrationFailed(new ServiceCommandError(0, "Certificate Registration failed", null));
                }
            } else if ("error".equals(optString)) {
                String optString4 = message.optString("error");
                if (optString4.length() == 0) {
                    return;
                }
                try {
                    String[] split = optString4.split(StringUtil.SPACE, 2);
                    i = Integer.parseInt(split[0]);
                    str = split[1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (opt != null) {
                    Log.d(Util.T, "Error Payload: " + opt.toString());
                }
                if (message.has("id")) {
                    Log.d(Util.T, "Error Desc: " + str);
                    if (serviceCommand != null) {
                        Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(i, str, opt));
                        if (serviceCommand instanceof URLServiceSubscription) {
                            return;
                        }
                        this.requests.remove(num.intValue());
                    }
                }
            } else if ("hello".equals(optString)) {
                JSONObject jSONObject = (JSONObject) opt;
                if (this.mconfig.getServiceUUID() != null) {
                    if (!this.mconfig.getServiceUUID().equals(jSONObject.optString("deviceUUID"))) {
                        this.mconfig.setClientKey(null);
                        this.mconfig.setServerCertificate((String) null);
                        this.mconfig.setServiceUUID(null);
                        this.mListener.updateClientKey(null);
                        this.mListener.updateUUID(null);
                        this.mListener.updateIPAddress(null);
                        this.mListener.updateIPAddress(null);
                        this.mListener.updateUUID(null);
                        disconnect();
                    }
                } else {
                    String optString5 = jSONObject.optString("deviceUUID");
                    this.mconfig.setServiceUUID(optString5);
                    this.mListener.updateUUID(optString5);
                }
                this.state = State.REGISTERING;
                sendRegister();
            }
        }
    }

    private void helloTV() {
        ApplicationInfo applicationInfo;
        Context context = DiscoveryManager.getInstance().getContext();
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String str = DiscoveryManager.CONNECT_SDK_VERSION;
        String str2 = Build.MODEL;
        String valueOf = String.valueOf(Build.VERSION.SDK_INT);
        Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        String format = String.format("%dx%d", Integer.valueOf(defaultDisplay.getWidth()), Integer.valueOf(defaultDisplay.getHeight()));
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException exception) {
            applicationInfo = null;
        }
        String str3 = (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "(unknown)");
        String displayCountry = context.getResources().getConfiguration().locale.getDisplayCountry();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdkVersion", str);
            jSONObject.put("deviceModel", str2);
            jSONObject.put("OSVersion", valueOf);
            jSONObject.put("resolution", format);
            jSONObject.put("appId", packageName);
            jSONObject.put("appName", str3);
            jSONObject.put("appRegion", displayCountry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int i = this.nextRequestId;
        this.nextRequestId = i + 1;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("id", i);
            jSONObject2.put("type", "hello");
            jSONObject2.put("payload", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        sendCommandImmediately(new ServiceCommand<>(this, null, jSONObject2, true, null));
    }


    protected void sendVerification() {
        ResponseListener<Object> listener = new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
                state = State.INITIAL;

                if (mListener != null)
                    mListener.onRegistrationFailed(error);
            }

            @Override
            public void onSuccess(Object object) {
                if (object instanceof JSONObject) {

                }
            }
        };

        int dataId = this.nextRequestId++;

        ServiceCommand<ResponseListener<Object>> command = new ServiceCommand<ResponseListener<Object>>(this, null, null, listener);
        command.setRequestId(dataId);

        JSONObject headers = new JSONObject();
        JSONObject payload = new JSONObject();
        int public_key_value = 0;
        int valid_value = 0;

        try {

            headers.put("type", "verification");
            headers.put("id", dataId);

            X509Certificate cert = customTrustManager.getLastCheckedCertificate();
            PublicKey pk = null;

            pk = cert.getPublicKey();
            String pubKey = Base64.encodeToString(pk.getEncoded(), Base64.DEFAULT);

            try {
                cert.verify(pk);
                verification_status = true;
            } catch (CertificateException | SignatureException e) {
                if (!(Public_Key == null || Public_Key.isEmpty())) {
                    boolean verified = pubKey.trim().equalsIgnoreCase(Public_Key.trim());
                    if (verified) {
                        payload.put("public-key", 1);
                        public_key_value = 1;
                    } else {
                        payload.put("public-key", -1);
                        public_key_value = -1;
                    }
                } else {
                    payload.put("public-key", 1);
                    public_key_value = 1;
                }

                try {
                    ((X509Certificate) cert).checkValidity();
                    payload.put("validity", 1);
                    valid_value = 1;
                } catch (CertificateExpiredException | CertificateNotYetValidException error) {
                    payload.put("validity", -1);
                    valid_value = -1;
                    error.printStackTrace();
                }

                if (public_key_value == 1 && valid_value == 1) {
                    verification_status = true;
                }

                requests.put(dataId, command);
                sendMessage(headers, payload);

            } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchProviderException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void sendRegister() {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
                WebOSTVServiceSocketClient.this.state = State.INITIAL;
                if (WebOSTVServiceSocketClient.this.mListener != null) {
                    WebOSTVServiceSocketClient.this.mListener.onRegistrationFailed(error);
                }
            }

            @Override
            public void onSuccess(Object object) {
                if (object instanceof JSONObject) {
                    DeviceService.PairingType pairingType = DeviceService.PairingType.NONE;
                    String optString = ((JSONObject) object).optString("pairingType");
                    if (optString.equalsIgnoreCase(WebOSTVServiceSocketClient.WEBOS_PAIRING_PROMPT)) {
                        pairingType = DeviceService.PairingType.FIRST_SCREEN;
                    } else if (optString.equalsIgnoreCase(WebOSTVServiceSocketClient.WEBOS_PAIRING_PIN)) {
                        pairingType = DeviceService.PairingType.PIN_CODE;
                    }
                    if (WebOSTVServiceSocketClient.this.mListener != null) {
                        WebOSTVServiceSocketClient.this.mListener.onBeforeRegister(pairingType);
                    }
                }
            }
        };
        int i = this.nextRequestId;
        this.nextRequestId = i + 1;
        ServiceCommand<? extends Object> serviceCommand = new ServiceCommand<>(this, null, null, responseListener);
        serviceCommand.setRequestId(i);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", "register");
            jSONObject.put("id", i);
            if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
                this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
            }
            if (this.mconfig.getClientKey() != null) {
                jSONObject2.put("client-key", this.mconfig.getClientKey());
            }
            if (DeviceService.PairingType.PIN_CODE.equals(this.mPairingType)) {
                jSONObject2.put("pairingType", WEBOS_PAIRING_PIN);
            }
            JSONObject jSONObject3 = this.manifest;
            if (jSONObject3 != null) {
                jSONObject2.put("manifest", jSONObject3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.requests.put(i, serviceCommand);
        sendMessage(jSONObject, jSONObject2);
    }

    public void sendPairingKey(String pairingKey) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object object) {
            }

            @Override
            public void onError(ServiceCommandError error) {
                WebOSTVServiceSocketClient.this.state = State.INITIAL;
                if (WebOSTVServiceSocketClient.this.mListener != null) {
                    WebOSTVServiceSocketClient.this.mListener.onFailWithError(error);
                }
            }
        };
        int i = this.nextRequestId;
        this.nextRequestId = i + 1;
        ServiceCommand<? extends Object> serviceCommand = new ServiceCommand<>(this, null, null, responseListener);
        serviceCommand.setRequestId(i);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", ServiceCommand.TYPE_REQ);
            jSONObject.put("id", i);
            jSONObject.put("uri", "ssap://pairing/setPin");
            jSONObject2.put("pin", pairingKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.requests.put(i, serviceCommand);
        sendMessage(jSONObject, jSONObject2);
    }

    protected void handleRegistered() {
        this.state = State.REGISTERED;
        if (!this.commandQueue.isEmpty()) {
            Iterator it = new LinkedHashSet(this.commandQueue).iterator();
            while (it.hasNext()) {
                ServiceCommand<?> serviceCommand = (ServiceCommand) it.next();
                Log.d(Util.T, "executing queued command for " + serviceCommand.getTarget());
                sendCommandImmediately(serviceCommand);
                this.commandQueue.remove(serviceCommand);
            }
        }
        WebOSTVServiceSocketClientListener webOSTVServiceSocketClientListener = this.mListener;
        if (webOSTVServiceSocketClientListener != null) {
            webOSTVServiceSocketClientListener.onConnect();
        }
    }

    @SuppressWarnings("unchecked")
    public void sendCommand(ServiceCommand<?> command) {
        Integer requestId;
        if (command.getRequestId() == -1) {
            requestId = this.nextRequestId++;
            command.setRequestId(requestId);
        } else {
            requestId = command.getRequestId();
        }

        requests.put(requestId, command);

        if (state == State.REGISTERED) {
            this.sendCommandImmediately(command);
        } else if (state == State.CONNECTING || state == State.DISCONNECTING) {
            Log.d(Util.T, "queuing command for " + command.getTarget());
            commandQueue.add((ServiceCommand<ResponseListener<Object>>) command);
        } else {
            Log.d(Util.T, "queuing command and restarting socket for " + command.getTarget());
            commandQueue.add((ServiceCommand<ResponseListener<Object>>) command);
            connect();
        }
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
        int requestId = subscription.getRequestId();
        if (this.requests.get(requestId) != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "unsubscribe");
                jSONObject.put("id", String.valueOf(requestId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendMessage(jSONObject, null);
            this.requests.remove(requestId);
        }
    }

    protected void sendCommandImmediately(ServiceCommand<?> command) {
        String str;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = (JSONObject) command.getPayload();
        try {
            str = jSONObject2.getString("type");
        } catch (Exception exception) {
            str = "";
        }
        if (str.equals("p2p")) {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, jSONObject2.get(next));
                } catch (JSONException unused2) {
                }
            }
            sendMessage(jSONObject, null);
        } else if (str.equals("hello")) {
            send(jSONObject2.toString());
        } else {
            try {
                jSONObject.put("type", command.getHttpMethod());
                jSONObject.put("id", String.valueOf(command.getRequestId()));
                jSONObject.put("uri", command.getTarget());
            } catch (JSONException unused3) {
            }
            sendMessage(jSONObject, jSONObject2);
        }
    }

    private void setSSLContext(SSLContext sslContext) {
        try {
            setSocket(sslContext.getSocketFactory().createSocket());
            setConnectionLostTimeout(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        }
    }

    protected void setupSSL() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            WebOSTVTrustManager webOSTVTrustManager = new WebOSTVTrustManager();
            this.customTrustManager = webOSTVTrustManager;
            sSLContext.init(null, new WebOSTVTrustManager[]{webOSTVTrustManager}, null);
            setSSLContext(sSLContext);
            if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
                this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
            }
            this.customTrustManager.setExpectedCertificate(this.mconfig.getServerCertificate());
        } catch (KeyException | NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isConnected() {
        return this.getReadyState() == ReadyState.OPEN;
    }

    public void sendMessage(JSONObject packet, JSONObject payload) {
        if (payload != null) {
            try {
                packet.put("payload", payload);
            } catch (JSONException e) {
                throw new Error(e);
            }
        }
        if (isConnected()) {
            String jSONObject = packet.toString();
            Log.d(Util.T, "webOS Socket [OUT] : " + jSONObject);
            send(jSONObject);
            return;
        }
        System.err.println("connection lost");
        handleConnectionLost(false, null);
    }

    private void handleConnectionLost(boolean cleanDisconnect, Exception ex) {
        ServiceCommandError serviceCommandError = (ex == null && cleanDisconnect) ? null : new ServiceCommandError(0, "conneciton error", ex);
        WebOSTVServiceSocketClientListener webOSTVServiceSocketClientListener = this.mListener;
        if (webOSTVServiceSocketClientListener != null) {
            webOSTVServiceSocketClientListener.onCloseWithError(serviceCommandError);
        }
        for (int i = 0; i < this.requests.size(); i++) {
            SparseArray<ServiceCommand<? extends Object>> sparseArray = this.requests;
            ServiceCommand<? extends Object> serviceCommand = sparseArray.get(sparseArray.keyAt(i));
            if (serviceCommand != null) {
                Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(0, "connection lost", null));
            }
        }
        clearRequests();
    }

    public void setServerCertificate(X509Certificate cert) {
        if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
            this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
        }
        this.mconfig.setServerCertificate(cert);
    }

    public void setServerCertificate(String cert) {
        if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
            this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
        }
        this.mconfig.setServerCertificate(loadCertificateFromPEM(cert));
    }

    public X509Certificate getServerCertificate() {
        if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
            this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
        }
        return this.mconfig.getServerCertificate();
    }

    public String getServerCertificateInString() {
        if (!(this.mconfig instanceof WebOSTVServiceConfig)) {
            this.mconfig = new WebOSTVServiceConfig(this.mconfig.getServiceUUID());
        }
        return exportCertificateToPEM(this.mconfig.getServerCertificate());
    }

    private String exportCertificateToPEM(X509Certificate cert) {
        try {
            return Base64.encodeToString(cert.getEncoded(), 0);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private X509Certificate loadCertificateFromPEM(String pemString) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(pemString.getBytes("US-ASCII")));
        } catch (UnsupportedEncodingException | CertificateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
