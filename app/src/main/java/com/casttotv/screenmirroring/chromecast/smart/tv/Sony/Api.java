package com.casttotv.screenmirroring.chromecast.smart.tv.Sony;

import androidx.annotation.Keep;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

@Keep
public interface Api {
    @POST("/sony/appControl")
    Call<String> getAllApp(@Body RequestBody requestBody, @Header("Content-type") String str, @Header("SOAPACTION") String str2, @Header("Cookie") String str3, @Header("X-Auth-PSK") String str4);

    @GET("country")
    Call<List> getCountry();

    @POST("/sony/appControl")
    Call<String> openApp(@Body RequestBody requestBody, @Header("Content-type") String str, @Header("SOAPACTION") String str2, @Header("Cookie") String str3, @Header("X-Auth-PSK") String str4);

    @POST("/sony/accessControl")
    Call<String> requestKeyCode(@Body RequestBody requestBody, @Header("Authorization") String str);

    @POST("/sony/ircc")
    Call<String> sendKeyWithCookie(@Body RequestBody requestBody, @Header("Content-type") String str, @Header("SOAPACTION") String str2, @Header("Cookie") String str3);

    @POST("/sony/ircc")
    Call<String> sendKeyWithPsk(@Body RequestBody requestBody, @Header("Content-type") String str, @Header("SOAPACTION") String str2, @Header("X-Auth-PSK") String str3);
}
