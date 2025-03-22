package com.connectsdk.service.airplay.auth;


class PairSetupPin3Response {
    public final byte[] AUTH_TAG;
    public final byte[] EPK;

    public PairSetupPin3Response(byte[] epk, byte[] authTag) {
        this.EPK = epk;
        this.AUTH_TAG = authTag;
    }
}
