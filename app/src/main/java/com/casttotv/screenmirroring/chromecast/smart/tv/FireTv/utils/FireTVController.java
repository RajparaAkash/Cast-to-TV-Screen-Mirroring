package com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Api;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireTVController {
    public static final FireTVController INSTANCE = new FireTVController();
    public static final String apiKeyFireTV = "0987654321";
    private static Retrofit retrofit;

    private static Retrofit f99retrofit2;


    private FireTVController() {
    }

    public static boolean m313getUnsafeOkHttpClient$lambda1(String str, SSLSession sSLSession) {
        return true;
    }

    public Retrofit getRetrofit2() {
        return f99retrofit2;
    }

    public void setRetrofit2(Retrofit retrofit3) {
        f99retrofit2 = retrofit3;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit3) {
        retrofit = retrofit3;
    }

    public String getToken(Context context) {
        return String.valueOf(context.getSharedPreferences("APP", 0).getString("accessToken", ""));
    }

    public void setToken(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("APP", 0).edit();
        edit.putString("accessToken", str);
        edit.apply();
    }

    public void buildRetrofit(String str) {
        retrofit = new Retrofit.Builder().baseUrl("https://" + str + ":8080").addConverterFactory(GsonConverterFactory.create()).client(getUnsafeOkHttpClient()).build();
    }

    public Api getApi() {
        Retrofit retrofit3 = retrofit;
        if (retrofit3 != null) {
            return (Api) retrofit3.create(Api.class);
        }
        return null;
    }

    public void buildRetrofit2(String str) {
        f99retrofit2 = new Retrofit.Builder().baseUrl("http://" + str + ":8009").addConverterFactory(GsonConverterFactory.create()).client(getUnsafeOkHttpClient()).build();
    }

    public Api getApi2() {
        Retrofit retrofit3 = f99retrofit2;
        if (retrofit3 != null) {
            return (Api) retrofit3.create(Api.class);
        }
        return null;
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        boolean z = true;
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init((KeyStore) null);
            TrustManager[] trustManagers = instance2.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                z = false;
            }
            if (z) {
                TrustManager trustManager = trustManagers[0];
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(socketFactory, (X509TrustManager) trustManager);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String str, SSLSession sslSession) {
                        return FireTVController.m313getUnsafeOkHttpClient$lambda1(str, sslSession);
                    }
                });
                return builder.build();
            }
            StringBuilder append = new StringBuilder().append("Unexpected default trust managers:");
            String arrays = Arrays.toString(trustManagers);
            throw new IllegalStateException(append.append(arrays).toString().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
