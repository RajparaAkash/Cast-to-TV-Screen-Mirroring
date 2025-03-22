package com.github.kunal52.pairing;

import com.github.kunal52.exception.PairingException;

public interface PairingListener {
    void onError(String str);

    void onLog(String str);

    void onPaired();

    void onPerformInputDeviceRole() throws PairingException;

    void onPerformOutputDeviceRole(byte[] bArr) throws PairingException;

    void onSecretRequested();

    void onSessionCreated();

    void onSessionEnded();
}
