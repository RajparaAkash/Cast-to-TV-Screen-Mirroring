package com.casttotv.screenmirroring.chromecast.smart.tv.Sony;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private Api myApi;

    private RetrofitClient(String str) {
        this.myApi = (Api) new Retrofit.Builder().baseUrl("http://" + str).addConverterFactory(ScalarsConverterFactory.create()).client(HTTPLogger.INSTANCE.getLogger()).build().create(Api.class);
    }

    public static synchronized RetrofitClient getInstance(String str) {
        RetrofitClient retrofitClient;
        synchronized (RetrofitClient.class) {
            if (instance == null) {
                instance = new RetrofitClient(str);
            }
            retrofitClient = instance;
        }
        return retrofitClient;
    }

    public Api getMyApi() {
        return this.myApi;
    }
}
