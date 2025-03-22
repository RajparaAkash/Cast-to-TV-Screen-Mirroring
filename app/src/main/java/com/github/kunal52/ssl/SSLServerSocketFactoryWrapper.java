package com.github.kunal52.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;

public class SSLServerSocketFactoryWrapper extends SSLServerSocketFactory {
    private SSLServerSocketFactory mFactory;

    public SSLServerSocketFactoryWrapper(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(keyManagerArr, trustManagerArr, null);
        this.mFactory = instance.getServerSocketFactory();
    }

    public static SSLServerSocketFactoryWrapper CreateWithDummyTrustManager(KeyManager[] keyManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        return new SSLServerSocketFactoryWrapper(keyManagerArr, new TrustManager[]{new DummyTrustManager()});
    }

    @Override
    public ServerSocket createServerSocket(int i) throws IOException {
        return this.mFactory.createServerSocket(i);
    }

    @Override
    public ServerSocket createServerSocket(int i, int i2) throws IOException {
        return this.mFactory.createServerSocket(i, i2);
    }

    @Override
    public ServerSocket createServerSocket(int i, int i2, InetAddress inetAddress) throws IOException {
        return this.mFactory.createServerSocket(i, i2, inetAddress);
    }

    public String[] getDefaultCipherSuites() {
        return this.mFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.mFactory.getSupportedCipherSuites();
    }
}
