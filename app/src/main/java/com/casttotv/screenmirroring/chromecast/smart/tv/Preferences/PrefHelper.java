package com.casttotv.screenmirroring.chromecast.smart.tv.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsData;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefHelper {

    public static PrefHelper instance;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences settings;

    private PrefHelper() {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences("CastToTv", Context.MODE_PRIVATE);
        this.settings = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public static PrefHelper getInstance() {
        if (instance == null) {
            instance = new PrefHelper();
        }
        return instance;
    }

    public String getString(String key, String defValue) {
        return this.settings.getString(key, defValue);
    }

    public PrefHelper setString(String key, String value) {
        this.editor.putString(key, value);
        this.editor.commit();
        return this;
    }

    public int getInt(String key, int defValue) {
        return this.settings.getInt(key, defValue);
    }

    public PrefHelper setInt(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.commit();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.settings.getBoolean(key, defValue);
    }

    public PrefHelper setBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
        return this;
    }

    public void clear() {
        this.editor.clear();
        this.editor.commit();
    }

    public <T> PrefHelper setArrayList(String key, ArrayList<T> arrayList) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(arrayList);
            editor.putString(key, json);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public <T> ArrayList<T> getArrayList(String key, Class<T> clazz) {
        try {
            Gson gson = new Gson();
            String json = settings.getString(key, "");
            Type type = TypeToken.getParameterized(ArrayList.class, clazz).getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public AdsData getAdsData() {
        String response = settings.getString("adsData", "");
        if (!response.isEmpty()) {
            return new Gson().fromJson(response, AdsData.class);
        }
        return null;
    }

    public void setAdsData(String response) {
        editor.putString("adsData", response).apply();
    }
}
