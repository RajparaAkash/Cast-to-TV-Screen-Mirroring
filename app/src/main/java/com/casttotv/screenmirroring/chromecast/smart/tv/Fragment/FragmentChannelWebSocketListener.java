package com.casttotv.screenmirroring.chromecast.smart.tv.Fragment;

import android.util.Log;
import android.view.View;

import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.AppSS;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.AppSamSung;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.Data;
import com.google.gson.GsonBuilder;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

import java.util.List;
import java.util.Map;

public class FragmentChannelWebSocketListener implements WebSocketListener {
    final FragmentChannel fragmentChannel;

    FragmentChannelWebSocketListener(FragmentChannel fragmentChannel) {
        this.fragmentChannel = fragmentChannel;
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
    public void onFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onFrameSent(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onFrameUnsent(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onPingFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
    }

    @Override
    public void onPongFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) {
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
    public void onTextMessage(WebSocket webSocket, byte[] bArr) {
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
    public void onTextMessage(WebSocket webSocket, String str) {
        List<AppSS> data;
        List<AppSS> data2;
        try {
            Object fromJson = new GsonBuilder().create().fromJson(str, AppSamSung.class);
            AppSamSung appSamSung = (AppSamSung) fromJson;
            Data data3 = appSamSung.getData();
            Log.d("AAA", String.valueOf((data3 == null || (data2 = data3.getData()) == null) ? null : data2.size()));
            Data data4 = appSamSung.getData();
            if (data4 != null && (data = data4.getData()) != null) {
                if (fragmentChannel.listApp != null) {
                    fragmentChannel.listApp.addAll(data);
                }
                if (fragmentChannel.listApp != null) {
                    fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Common.INSTANCE.isChannel()) {
                                fragmentChannel.binding.rvChannel.setVisibility(View.VISIBLE);
                                if (fragmentChannel.mAdapterSS != null) {
                                    fragmentChannel.mAdapterSS.setListApp(fragmentChannel.listApp);
                                }
                                fragmentChannel.binding.clEmptySs.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("AAA", String.valueOf(e.getMessage()));
        }
    }

    @Override
    public void onError(WebSocket webSocket, WebSocketException webSocketException) {
        Log.d("AAA", "onError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onFrameError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
        Log.d("AAA", "onFrameError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onMessageError(WebSocket webSocket, WebSocketException webSocketException, List<WebSocketFrame> list) {
        Log.d("AAA", "onMessageError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onMessageDecompressionError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) {
        Log.d("AAA", "onMessageDecompressionError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onTextMessageError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) {
        Log.d("AAA", "onTextMessageError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onSendError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
        Log.d("AAA", "onSendError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onUnexpectedError(WebSocket webSocket, WebSocketException webSocketException) {
        Log.d("AAA", "onUnexpectedError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void handleCallbackError(WebSocket webSocket, Throwable th) {
        Log.d("AAA", "handleCallbackError");
        fragmentChannel.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.INSTANCE.isChannel()) {
                    fragmentChannel.binding.clEmptySs.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
