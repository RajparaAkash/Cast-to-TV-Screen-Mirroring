package com.connectsdk.service.config;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class WebOSTVServiceConfig extends ServiceConfig {
    public static final String KEY_CERT = "serverCertificate";
    public static final String KEY_CLIENT_KEY = "clientKey";
    X509Certificate cert;
    String clientKey;

    public WebOSTVServiceConfig(String serviceUUID) {
        super(serviceUUID);
    }

    public WebOSTVServiceConfig(String serviceUUID, String clientKey) {
        super(serviceUUID);
        this.clientKey = clientKey;
        this.cert = null;
    }

    public WebOSTVServiceConfig(String serviceUUID, String clientKey, X509Certificate cert) {
        super(serviceUUID);
        this.clientKey = clientKey;
        this.cert = cert;
    }

    public WebOSTVServiceConfig(String serviceUUID, String clientKey, String cert) {
        super(serviceUUID);
        this.clientKey = clientKey;
        this.cert = loadCertificateFromPEM(cert);
    }

    public WebOSTVServiceConfig(JSONObject json) {
        super(json);
        this.clientKey = json.optString(KEY_CLIENT_KEY);
        this.cert = null;
    }

    public String getClientKey() {
        return this.clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
        notifyUpdate();
    }

    public X509Certificate getServerCertificate() {
        return this.cert;
    }

    public void setServerCertificate(X509Certificate cert) {
        this.cert = cert;
        notifyUpdate();
    }

    public void setServerCertificate(String cert) {
        this.cert = loadCertificateFromPEM(cert);
        notifyUpdate();
    }

    public String getServerCertificateInString() {
        return exportCertificateToPEM(this.cert);
    }

    private String exportCertificateToPEM(X509Certificate cert) {
        if (cert == null) {
            return null;
        }
        try {
            return Base64.encodeToString(cert.getEncoded(), 0);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private X509Certificate loadCertificateFromPEM(String pemString) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(pemString.getBytes("US-ASCII")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (CertificateException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        try {
            jSONObject.put(KEY_CLIENT_KEY, this.clientKey);
            jSONObject.put(KEY_CERT, exportCertificateToPEM(this.cert));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
