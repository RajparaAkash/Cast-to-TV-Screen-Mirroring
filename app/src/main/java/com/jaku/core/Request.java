package com.jaku.core;

import java.io.IOException;

public abstract class Request {
    protected String body;
    protected String url;

    public abstract Response send() throws IOException;

    public Request(String str, String str2) {
        this.url = str;
        this.body = str2;
    }
}
