package com.jaku.core;

public class JakuResponse {
    private Object jakuResponseData;

    public JakuResponse(Object obj) {
        this.jakuResponseData = obj;
    }

    public Object getResponseData() {
        return this.jakuResponseData;
    }

    public void setResponseData(JakuResponseData jakuResponseData2) {
        this.jakuResponseData = jakuResponseData2;
    }
}
