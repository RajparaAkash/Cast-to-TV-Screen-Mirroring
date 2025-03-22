package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

import java.util.List;
import java.util.Map;

public class ActivityRemoteWebSocketListener implements WebSocketListener {
    ActivityRemoteWebSocketListener() {
    }

    @Override
    public void handleCallbackError(WebSocket webSocket, Throwable th) {
    }

    @Override
    public void onBinaryFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onBinaryMessage(WebSocket webSocket, byte[] bArr) {
    }

    @Override
    public void onCloseFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onConnectError(WebSocket webSocket, WebSocketException webSocketException) {
    }

    @Override
    public void onConnected(WebSocket webSocket, Map<String, List<String>> map) {
    }

    @Override
    public void onContinuationFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onDisconnected(WebSocket webSocket, WebSocketFrame webSocketFrame, WebSocketFrame webSocketFrame2, boolean z) {
    }

    @Override
    public void onError(WebSocket webSocket, WebSocketException webSocketException) {
    }

    @Override
    public void onFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onFrameError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onFrameSent(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onFrameUnsent(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onMessageDecompressionError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) {
    }

    @Override
    public void onMessageError(WebSocket webSocket, WebSocketException webSocketException, List<WebSocketFrame> list) {
    }

    @Override
    public void onPingFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onPongFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onSendError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onSendingFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onSendingHandshake(WebSocket webSocket, String str, List<String[]> list) {
    }

    @Override
    public void onStateChanged(WebSocket webSocket, WebSocketState webSocketState) {
    }

    @Override
    public void onTextFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onTextMessage(WebSocket webSocket, String str) {
    }

    @Override
    public void onTextMessage(WebSocket webSocket, byte[] bArr) {
    }

    @Override
    public void onTextMessageError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) {
    }

    @Override
    public void onThreadCreated(WebSocket webSocket, ThreadType threadType, Thread thread) {
    }

    @Override
    public void onThreadStarted(WebSocket webSocket, ThreadType threadType, Thread thread) {
    }

    @Override
    public void onThreadStopping(WebSocket webSocket, ThreadType threadType, Thread thread) {
    }

    @Override
    public void onUnexpectedError(WebSocket webSocket, WebSocketException webSocketException) {
    }

}
