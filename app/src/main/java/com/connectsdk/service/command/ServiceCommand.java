package com.connectsdk.service.command;

import com.connectsdk.service.capability.listeners.ResponseListener;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;


public class ServiceCommand<T extends ResponseListener<? extends Object>> {
    public static final String TYPE_DEL = "DELETE";
    public static final String TYPE_GET = "GET";
    public static final String TYPE_POST = "POST";
    public static final String TYPE_PUT = "PUT";
    public static final String TYPE_REQ = "request";
    public static final String TYPE_SUB = "subscribe";
    protected String httpMethod;
    protected Object payload;
    protected ServiceCommandProcessor processor;
    int requestId;
    ResponseListener<Object> responseListener;
    protected String target;

    
    public interface ServiceCommandProcessor {
        void sendCommand(ServiceCommand<?> command);

        void unsubscribe(ServiceSubscription<?> subscription);

        void unsubscribe(URLServiceSubscription<?> subscription);
    }

    public ServiceCommand(ServiceCommandProcessor processor, String targetURL, Object payload, ResponseListener<Object> listener) {
        this.processor = processor;
        this.target = targetURL;
        this.payload = payload;
        this.responseListener = listener;
        this.httpMethod = "POST";
    }

    public ServiceCommand(ServiceCommandProcessor processor, String uri, JSONObject payload, boolean isWebOS, ResponseListener<Object> listener) {
        this.processor = processor;
        this.target = uri;
        this.payload = payload;
        this.requestId = -1;
        this.httpMethod = TYPE_REQ;
        this.responseListener = listener;
    }

    public void send() {
        this.processor.sendCommand(this);
    }

    public ServiceCommandProcessor getCommandProcessor() {
        return this.processor;
    }

    public void setCommandProcessor(ServiceCommandProcessor processor) {
        this.processor = processor;
    }

    public Object getPayload() {
        return this.payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public HttpRequestBase getRequest() {
        if (this.target == null) {
            throw new IllegalStateException("ServiceCommand has no target url");
        }
        if (this.httpMethod.equalsIgnoreCase("GET")) {
            return new HttpGet(this.target);
        }
        if (this.httpMethod.equalsIgnoreCase("POST")) {
            return new HttpPost(this.target);
        }
        if (this.httpMethod.equalsIgnoreCase("DELETE")) {
            return new HttpDelete(this.target);
        }
        if (this.httpMethod.equalsIgnoreCase("PUT")) {
            return new HttpPut(this.target);
        }
        return null;
    }

    public ResponseListener<Object> getResponseListener() {
        return this.responseListener;
    }
}
