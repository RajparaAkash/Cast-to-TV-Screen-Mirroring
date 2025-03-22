package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NaiveSSLContext {
    public static final NaiveSSLContext INSTANCE = new NaiveSSLContext();

    private NaiveSSLContext() {
    }

    public SSLContext getInstance(String str) throws NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance(str);
        return init(instance);
    }

    public SSLContext getInstance(String str, Provider provider) throws NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance(str, provider);
        return init(instance);
    }

    public SSLContext getInstance(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        SSLContext instance = SSLContext.getInstance(str, str2);
        return init(instance);
    }

    private SSLContext init(SSLContext sSLContext) {
        try {
            sSLContext.init(null, new TrustManager[]{new NaiveTrustManager()}, null);
            return sSLContext;
        } catch (KeyManagementException e) {
            throw new RuntimeException("Failed to initialize an SSLContext.", e);
        }
    }

    public static final class NaiveTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
