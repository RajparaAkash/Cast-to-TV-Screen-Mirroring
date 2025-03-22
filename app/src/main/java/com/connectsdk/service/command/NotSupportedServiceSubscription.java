package com.connectsdk.service.command;

import java.util.ArrayList;
import java.util.List;


public class NotSupportedServiceSubscription<T> implements ServiceSubscription<T> {
    private List<T> listeners = new ArrayList<>();

    @Override
    public void unsubscribe() {
    }

    @Override
    public T addListener(T listener) {
        this.listeners.add(listener);
        return listener;
    }

    @Override
    public List<T> getListeners() {
        return this.listeners;
    }

    @Override
    public void removeListener(T listener) {
        this.listeners.remove(listener);
    }
}
