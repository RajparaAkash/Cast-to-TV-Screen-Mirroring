package com.connectsdk.service.webos;

import android.util.Log;

import com.connectsdk.core.Util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.X509TrustManager;


public class WebOSTVTrustManager implements X509TrustManager {
    X509Certificate expectedCert;
    X509Certificate lastCheckedCert;

    @Override 
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override 
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public void setExpectedCertificate(X509Certificate cert) {
        this.expectedCert = cert;
    }

    public X509Certificate getLastCheckedCertificate() {
        return this.lastCheckedCert;
    }

    @Override 
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        String str = Util.T;
        StringBuilder append = new StringBuilder().append("Expecting device cert ");
        X509Certificate x509Certificate = this.expectedCert;
        Log.d(str, append.append(x509Certificate != null ? x509Certificate.getSubjectDN() : "(any)").toString());
        if (chain != null && chain.length > 0) {
            X509Certificate x509Certificate2 = chain[0];
            this.lastCheckedCert = x509Certificate2;
            if (this.expectedCert != null) {
                byte[] encoded = x509Certificate2.getEncoded();
                byte[] encoded2 = this.expectedCert.getEncoded();
                Log.d(Util.T, "Device presented cert " + x509Certificate2.getSubjectDN());
                if (!Arrays.equals(encoded, encoded2)) {
                    throw new CertificateException("certificate does not match");
                }
                return;
            }
            return;
        }
        this.lastCheckedCert = null;
        throw new CertificateException("no server certificate");
    }
}
