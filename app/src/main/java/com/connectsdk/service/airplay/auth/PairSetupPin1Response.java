package com.connectsdk.service.airplay.auth;


class PairSetupPin1Response {

    public final byte[] PK;
    public final byte[] SALT;

    public PairSetupPin1Response(byte[] pk, byte[] salt) {
        this.PK = pk;
        this.SALT = salt;
    }
}
