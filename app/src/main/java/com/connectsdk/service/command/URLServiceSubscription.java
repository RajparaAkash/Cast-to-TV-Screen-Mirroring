package com.connectsdk.service.command;

import com.connectsdk.service.capability.listeners.ResponseListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class URLServiceSubscription<T extends ResponseListener<?>> extends ServiceCommand<T> implements ServiceSubscription<T> {
    private List<T> listeners;

    public URLServiceSubscription(ServiceCommandProcessor processor, String uri, JSONObject payload, ResponseListener<Object> listener) {
        super(processor, uri, payload, listener);
        this.listeners = new ArrayList<>();
    }

    public URLServiceSubscription(ServiceCommandProcessor processor, String uri, JSONObject payload, boolean isWebOS, ResponseListener<Object> listener) {
        super(processor, uri, payload, isWebOS, listener);
        this.listeners = new ArrayList<>();
        if (isWebOS) {
            this.httpMethod = ServiceCommand.TYPE_SUB;
        }
    }

    @Override
    public void send() {
        subscribe();
    }

    public void subscribe() {
        if (!this.httpMethod.equalsIgnoreCase("GET") && !this.httpMethod.equalsIgnoreCase("POST")) {
            this.httpMethod = ServiceCommand.TYPE_SUB;
        }
        this.processor.sendCommand(this);
    }

    @Override
    public void unsubscribe() {
        this.processor.unsubscribe((URLServiceSubscription<?>) this);
    }

    public T addListener(T listener) {
        this.listeners.add(listener);
        return listener;
    }

    public void removeListener(T listener) {
        this.listeners.remove(listener);
    }

    public void removeListeners() {
        this.listeners.clear();
    }

    @Override
    public List<T> getListeners() {
        return this.listeners;
    }
}
