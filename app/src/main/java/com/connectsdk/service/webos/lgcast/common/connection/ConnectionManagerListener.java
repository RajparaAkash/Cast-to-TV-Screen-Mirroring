package com.connectsdk.service.webos.lgcast.common.connection;

import org.json.JSONObject;


public interface ConnectionManagerListener {
    void onConnectionCompleted(JSONObject jsonObj);

    void onConnectionFailed(String failureMessage);

    void onError(ConnectionManagerError connectionError, String errorMessage);

    void onPairingRejected();

    void onPairingRequested();

    void onReceiveGetParameter(JSONObject jsonObj);

    void onReceivePlayCommand(JSONObject jsonObj);

    void onReceiveSetParameter(JSONObject jsonObj);

    void onReceiveStopCommand(JSONObject jsonObj);
}
