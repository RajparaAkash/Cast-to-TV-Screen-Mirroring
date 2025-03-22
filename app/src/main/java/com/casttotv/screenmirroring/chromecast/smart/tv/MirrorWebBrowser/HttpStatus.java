package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

public enum HttpStatus {
    OK(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found");

    private final int statusCode;
    private final String name;

    HttpStatus(int statusCode, String name) {
        this.statusCode = statusCode;
        this.name = name;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }
}
