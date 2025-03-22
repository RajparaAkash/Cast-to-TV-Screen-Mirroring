package com.casttotv.screenmirroring.chromecast.smart.tv.Api;

import androidx.annotation.Keep;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Keep
public interface Api {
    @GET("/v1/FireTV/apps")
    Call<List<App>> getApps(@Header("x-api-key") String str, @Header("x-client-token") String str2);

    @POST("/v1/FireTV/app/{path}")
    Call<Description> openApp(@Header("x-api-key") String str, @Header("x-client-token") String str2, @Path("path") String str3);

    @POST("/v1/FireTV")
    Call<Description> remote(@Header("x-api-key") String str, @Header("x-client-token") String str2, @Query("action") String str3);

    @POST("/v1/media")
    Call<Description> remoteMedia(@Header("x-api-key") String str, @Header("x-client-token") String str2, @Query("action") String str3);

    @POST("keypress/{key}")
    Call<ResponseBody> sendKey(@Path("key") String str);

    @POST("/v1/FireTV/text")
    Call<Description> text(@Header("x-api-key") String str, @Header("x-client-token") String str2, @Body Text text);
}
