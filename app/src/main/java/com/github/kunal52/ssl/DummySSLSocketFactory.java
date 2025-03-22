package com.github.kunal52.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

public class DummySSLSocketFactory extends SSLSocketFactoryWrapper {
    DummySSLSocketFactory(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        super(keyManagerArr, trustManagerArr);
    }

    public static DummySSLSocketFactory fromKeyManagers(KeyManager[] keyManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        return new DummySSLSocketFactory(keyManagerArr, new TrustManager[]{new DummyTrustManager()});
    }
}
