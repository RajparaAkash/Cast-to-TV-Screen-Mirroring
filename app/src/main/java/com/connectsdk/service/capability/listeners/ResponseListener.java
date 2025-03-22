package com.connectsdk.service.capability.listeners;


public interface ResponseListener<T> extends ErrorListener {
    void onSuccess(T object);
}
