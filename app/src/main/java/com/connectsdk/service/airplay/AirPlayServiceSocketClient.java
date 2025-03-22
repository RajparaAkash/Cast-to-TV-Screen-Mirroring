package com.connectsdk.service.airplay;

import android.util.Log;

import com.connectsdk.core.Util;
import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.airplay.auth.AirPlayAuth;
import com.connectsdk.service.airplay.auth.AuthUtils;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.AirPlayServiceConfig;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class AirPlayServiceSocketClient implements ServiceCommand.ServiceCommandProcessor {
    int PORT = 7000;
    AirPlayAuth airPlayAuth;
    AirPlayServiceSocketClientListener mListener;
    DeviceService.PairingType mPairingType;
    AirPlayServiceConfig mconfig;
    Socket socket;
    State state;


    public interface AirPlayServiceSocketClientListener {
        void onBeforeRegister(DeviceService.PairingType pairingType);

        void onCloseWithError(ServiceCommandError error);

        void onConnect();

        void onFailWithError(ServiceCommandError error);

        Boolean onReceiveMessage(JSONObject message);

        void onRegistrationFailed(ServiceCommandError error);
    }


    public enum State {
        NONE,
        INITIAL,
        CONNECTING,
        REGISTERING,
        REGISTERED,
        DISCONNECTING
    }

    @Override
    public void unsubscribe(ServiceSubscription<?> subscription) {
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
    }

    public String getAuthToken() {
        return this.mconfig.getAuthToken();
    }

    public void setListener(AirPlayServiceSocketClientListener mListener) {
        this.mListener = mListener;
    }

    public AirPlayServiceSocketClient(AirPlayServiceConfig config, DeviceService.PairingType pairingType, String ipAddress) {
        this.state = State.INITIAL;
        this.mPairingType = pairingType;
        this.mconfig = config;
        if (config.getAuthToken() == "") {
            this.mconfig.setAuthToken(AirPlayAuth.generateNewAuthToken());
        }
        this.airPlayAuth = new AirPlayAuth(new InetSocketAddress(ipAddress, this.PORT), this.mconfig.getAuthToken());
        this.state = State.INITIAL;
    }

    public State getState() {
        return this.state;
    }

    public boolean isConnected() {
        return getState() == State.REGISTERED;
    }

    public void pair(final String pin) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AirPlayServiceSocketClient.this.airPlayAuth.doPairing(pin);
                    AirPlayServiceSocketClient airPlayServiceSocketClient = AirPlayServiceSocketClient.this;
                    airPlayServiceSocketClient.socket = airPlayServiceSocketClient.airPlayAuth.authenticate();
                    AirPlayServiceSocketClient.this.state = State.REGISTERED;
                    if (AirPlayServiceSocketClient.this.mListener != null) {
                        AirPlayServiceSocketClient.this.mListener.onConnect();
                    }
                } catch (Exception e) {
                    AirPlayServiceSocketClient.this.state = State.INITIAL;
                    e.printStackTrace();
                    AirPlayServiceSocketClient.this.mListener.onRegistrationFailed(new ServiceCommandError(e.toString()));
                }
            }
        }).start();
    }

    public void connect() {
        synchronized (this) {
            if (this.state != State.INITIAL) {
                Log.w(Util.T, "already connecting; not trying to connect again: " + this.state);
                return;
            }
            this.state = State.CONNECTING;
            try {
                try {
                    this.socket = this.airPlayAuth.authenticate();
                    this.state = State.REGISTERED;
                    AirPlayServiceSocketClientListener airPlayServiceSocketClientListener = this.mListener;
                    if (airPlayServiceSocketClientListener != null) {
                        airPlayServiceSocketClientListener.onConnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.mListener.onRegistrationFailed(new ServiceCommandError(e.toString()));
                }
            } catch (Exception exception) {
                try {
                    this.airPlayAuth.startPairing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AirPlayServiceSocketClientListener airPlayServiceSocketClientListener2 = this.mListener;
                if (airPlayServiceSocketClientListener2 != null) {
                    airPlayServiceSocketClientListener2.onBeforeRegister(this.mPairingType);
                }
            }
            int i = 0;
            while (this.state == State.CONNECTING) {
                i++;
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    this.mListener.onRegistrationFailed(new ServiceCommandError(e2.toString()));
                }
                if (i > 200) {
                    this.mListener.onRegistrationFailed(new ServiceCommandError("Pairing Timeout"));
                    return;
                }
                continue;
            }
        }
    }

    public void disconnect() {
        this.mPairingType = null;
        this.mconfig = null;
        this.airPlayAuth = null;
        this.state = State.INITIAL;
        try {
            Socket socket = this.socket;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectWithError(ServiceCommandError error) {
        this.state = State.INITIAL;
        AirPlayServiceSocketClientListener airPlayServiceSocketClientListener = this.mListener;
        if (airPlayServiceSocketClientListener != null) {
            airPlayServiceSocketClientListener.onCloseWithError(error);
        }
    }

    @Override
    public void sendCommand(final ServiceCommand<?> serviceCommand) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bArr = (byte[]) serviceCommand.getPayload();
                String target = serviceCommand.getTarget();
                try {
                    if (serviceCommand.getHttpMethod().equalsIgnoreCase("GET")) {
                        Util.postSuccess(serviceCommand.getResponseListener(), AuthUtils.getData(AirPlayServiceSocketClient.this.socket, target));
                    } else if (serviceCommand.getHttpMethod().equalsIgnoreCase("POST")) {
                        Util.postSuccess(serviceCommand.getResponseListener(), AuthUtils.postData(AirPlayServiceSocketClient.this.socket, target, HttpMessage.CONTENT_TYPE_APPLICATION_PLIST, bArr));
                    } else if (serviceCommand.getHttpMethod().equalsIgnoreCase("PUT")) {
                        Util.postSuccess(serviceCommand.getResponseListener(), AuthUtils.putData(AirPlayServiceSocketClient.this.socket, target, HttpMessage.CONTENT_TYPE_APPLICATION_PLIST, bArr));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    String[] split = e.getMessage().split(StringUtil.SPACE);
                    Util.postError(serviceCommand.getResponseListener(), ServiceCommandError.getError(Integer.parseInt(split[split.length - 1])));
                }
            }
        }).start();
    }
}
