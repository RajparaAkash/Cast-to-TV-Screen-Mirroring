package com.connectsdk.service.sessions;


public interface WebAppSessionListener {
    void onReceiveMessage(WebAppSession webAppSession, Object message);

    void onWebAppSessionDisconnect(WebAppSession webAppSession);
}
