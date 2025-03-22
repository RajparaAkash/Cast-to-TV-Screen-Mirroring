package com.casttotv.screenmirroring.chromecast.smart.tv.Roku;

import androidx.annotation.NonNull;

import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Api;

import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CastClient {
    public static final CastClient INSTANCE = new CastClient();
    private static Api mCastApi;
    private static String mIp;
    private static Retrofit mRetrofit;

    private CastClient() {
    }

    private Retrofit retrofit(String str) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().build()).build();
        }
        return mRetrofit;
    }

    public void setIp(String str) {
        mIp = str;
        mRetrofit = null;
    }

    public Api castApi() {
        String str = mIp;
        Api api = null;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        if (mCastApi == null) {
            Retrofit retrofit = retrofit("http://" + mIp + ":8060/");
            if (retrofit != null) {
                api = (Api) retrofit.create(Api.class);
            }
            mCastApi = api;
        }
        return mCastApi;
    }

    public void sendRemoteKey(RokuKey rokuKey) {
        Call<ResponseBody> sendKey;
        Api castApi = castApi();
        if (castApi != null && (sendKey = castApi.sendKey(rokuKey.getKey())) != null) {
            sendKey.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                }
            });
        }
    }

    public void sendRemoteKey(String str) {
        Call<ResponseBody> sendKey;
        Api castApi = castApi();
        if (castApi != null && (sendKey = castApi.sendKey(str)) != null) {
            sendKey.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                }
            });
        }
    }

}
