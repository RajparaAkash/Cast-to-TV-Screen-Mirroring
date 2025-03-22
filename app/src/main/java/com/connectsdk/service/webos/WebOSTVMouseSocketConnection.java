package com.connectsdk.service.webos;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

public class WebOSTVMouseSocketConnection {

    WebOSTVTrustManager customTrustManager;
    WebOSTVMouseSocketListener listener;
    String socketPath;

    WebSocketClient ws;

    public enum ButtonType {
        HOME,
        BACK,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    
    public interface WebOSTVMouseSocketListener {
        void onConnected();
    }

    public WebOSTVMouseSocketConnection(String socketPath, WebOSTVMouseSocketListener listener) {
        Log.d("PtrAndKeyboardFragment", "got socketPath: " + socketPath);
        this.listener = listener;
        this.socketPath = socketPath;
        try {
            connectPointer(new URI(this.socketPath));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connectPointer(URI uri) {
        WebSocketClient webSocketClient = this.ws;
        if (webSocketClient != null) {
            webSocketClient.close();
            this.ws = null;
        }
        this.ws = new WebSocketClient(uri) {
            @Override
            public void onClose(int arg0, String arg1, boolean arg2) {
            }

            @Override 
            public void onError(Exception arg0) {
            }

            @Override 
            public void onMessage(String arg0) {
            }

            @Override 
            public void onOpen(ServerHandshake arg0) {
                Log.d("PtrAndKeyboardFragment", "connected to " + this.uri.toString());
                if (WebOSTVMouseSocketConnection.this.listener != null) {
                    WebOSTVMouseSocketConnection.this.listener.onConnected();
                }
            }
        };
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            WebOSTVTrustManager webOSTVTrustManager = new WebOSTVTrustManager();
            this.customTrustManager = webOSTVTrustManager;
            sSLContext.init(null, new WebOSTVTrustManager[]{webOSTVTrustManager}, null);
            this.ws.setSocket(sSLContext.getSocketFactory().createSocket());
            this.ws.setConnectionLostTimeout(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (KeyException e3) {
            e3.printStackTrace();
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
        }
        this.ws.connect();
    }

    public void disconnect() {
        WebSocketClient webSocketClient = this.ws;
        if (webSocketClient != null) {
            webSocketClient.close();
            this.ws = null;
        }
    }

    public boolean isConnected() {
        WebSocketClient webSocketClient = this.ws;
        if (webSocketClient == null) {
            System.out.println("ws is null");
        } else if (webSocketClient.getReadyState() != ReadyState.OPEN) {
            System.out.println("ws state is not ready");
        }
        WebSocketClient webSocketClient2 = this.ws;
        return webSocketClient2 != null && webSocketClient2.getReadyState() == ReadyState.OPEN;
    }

    public void click() {
        if (isConnected()) {
            this.ws.send("type:click\n\n");
        }
    }

    public void button(ButtonType type) {
        String keyName;
        switch (type) {
            case HOME:
                keyName = "HOME";
                break;
            case BACK:
                keyName = "BACK";
                break;
            case UP:
                keyName = "UP";
                break;
            case DOWN:
                keyName = "DOWN";
                break;
            case LEFT:
                keyName = "LEFT";
                break;
            case RIGHT:
                keyName = "RIGHT";
                break;

            default:
                keyName = "NONE";
                break;
        }

        button(keyName);
    }

    public void button(String keyName) {
        if (isConnected()) {
            this.ws.send("type:button\nname:" + keyName + "\n\n");
        }
    }

    public void move(double dx, double dy) {
        if (isConnected()) {
            this.ws.send("type:move\ndx:" + dx + "\ndy:" + dy + "\ndown:0\n\n");
        }
    }

    public void move(double dx, double dy, boolean drag) {
        if (isConnected()) {
            this.ws.send("type:move\ndx:" + dx + "\ndy:" + dy + "\ndown:" + (drag ? 1 : 0) + "\n\n");
        }
    }

    public void scroll(double dx, double dy) {
        if (isConnected()) {
            this.ws.send("type:scroll\ndx:" + dx + "\ndy:" + dy + "\n\n");
        }
    }
}
