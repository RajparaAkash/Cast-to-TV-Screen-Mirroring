package com.casttotv.screenmirroring.chromecast.smart.tv.Sony;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HTTPLogger {
    public static final HTTPLogger INSTANCE = new HTTPLogger();

    private HTTPLogger() {
    }

    public OkHttpClient getLogger() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }
}
