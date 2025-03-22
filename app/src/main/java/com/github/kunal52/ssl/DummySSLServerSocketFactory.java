package com.github.kunal52.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

public class DummySSLServerSocketFactory extends SSLServerSocketFactoryWrapper {
    DummySSLServerSocketFactory(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        super(keyManagerArr, trustManagerArr);
    }

    public static DummySSLServerSocketFactory fromKeyManagers(KeyManager[] keyManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        return new DummySSLServerSocketFactory(keyManagerArr, new TrustManager[]{new DummyTrustManager()});
    }
}
