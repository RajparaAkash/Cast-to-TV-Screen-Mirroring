package com.github.kunal52;

public interface AndroidTvListener {
    void onConnected();

    void onConnectingToRemote();

    void onDisconnect();

    void onError(String str);

    void onPaired();

    void onSecretRequested();

    void onSessionCreated();
}
