package com.github.kunal52.ssl;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SSLSocketFactoryWrapper extends SSLSocketFactory {
    private SSLSocketFactory mFactory;

    public static SocketFactory getDefault() {
        throw new IllegalStateException("Not implemented.");
    }

    public SSLSocketFactoryWrapper() {
        throw new IllegalStateException("Not implemented.");
    }

    public SSLSocketFactoryWrapper(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws NoSuchAlgorithmException, KeyManagementException {
        Security.addProvider(new BouncyCastleProvider());
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(keyManagerArr, trustManagerArr, null);
        this.mFactory = instance.getSocketFactory();
    }

    public static SSLSocketFactoryWrapper CreateWithDummyTrustManager(KeyManager[] keyManagerArr) throws KeyManagementException, NoSuchAlgorithmException {
        return new SSLSocketFactoryWrapper(keyManagerArr, new TrustManager[]{new DummyTrustManager()});
    }

    @Override
    public Socket createSocket() throws IOException {
        return this.mFactory.createSocket();
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return this.mFactory.createSocket(inetAddress, i);
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return this.mFactory.createSocket(inetAddress, i, inetAddress2, i2);
    }

    @Override
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return this.mFactory.createSocket(socket, str, i, z);
    }

    @Override
    public Socket createSocket(String str, int i) throws IOException {
        return this.mFactory.createSocket(str, i);
    }

    @Override
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return this.mFactory.createSocket(str, i, inetAddress, i2);
    }

    public String[] getDefaultCipherSuites() {
        return this.mFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.mFactory.getSupportedCipherSuites();
    }
}
