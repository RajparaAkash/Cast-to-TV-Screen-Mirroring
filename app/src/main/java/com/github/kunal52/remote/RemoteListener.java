package com.github.kunal52.remote;

import com.github.kunal52.exception.PairingException;

public interface RemoteListener {
    void onConnected();

    void onDisconnected();

    void onError(String str);

    void onLog(String str);

    void onPerformInputDeviceRole() throws PairingException;

    void onPerformOutputDeviceRole(byte[] bArr) throws PairingException;

    void onSessionEnded();

    void onVolume();

    void sSLException();
}
