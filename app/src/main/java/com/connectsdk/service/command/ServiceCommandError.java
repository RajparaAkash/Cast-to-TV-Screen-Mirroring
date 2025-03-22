package com.connectsdk.service.command;


public class ServiceCommandError extends Error {
    private static final long serialVersionUID = 4232138682873631468L;
    protected int code;
    protected Object payload;

    public ServiceCommandError() {
    }

    public ServiceCommandError(String detailMessage) {
        super(detailMessage);
    }

    public ServiceCommandError(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    public ServiceCommandError(int code, String desc, Object payload) {
        super(desc);
        this.code = code;
        this.payload = payload;
    }

    public static ServiceCommandError notSupported() {
        return new NotSupportedServiceCommandError();
    }

    public static ServiceCommandError getError(int code) {
        return new ServiceCommandError(code, code == 400 ? "Bad Request" : code == 401 ? "Unauthorized" : code == 500 ? "Internal Server Error" : code == 503 ? "Service Unavailable" : "Unknown Error", null);
    }

    public int getCode() {
        return this.code;
    }

    public Object getPayload() {
        return this.payload;
    }
}
