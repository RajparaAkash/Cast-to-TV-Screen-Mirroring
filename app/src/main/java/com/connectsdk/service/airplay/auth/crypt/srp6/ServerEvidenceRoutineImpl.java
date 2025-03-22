package com.connectsdk.service.airplay.auth.crypt.srp6;

import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6ClientSession;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6ServerEvidenceContext;
import com.nimbusds.srp6.ServerEvidenceRoutine;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class ServerEvidenceRoutineImpl implements ServerEvidenceRoutine {
    private final SRP6ClientSession srp6ClientSession;

    public ServerEvidenceRoutineImpl(SRP6ClientSession srp6ClientSession) {
        this.srp6ClientSession = srp6ClientSession;
    }

    @Override
    public BigInteger computeServerEvidence(SRP6CryptoParams cryptoParams, SRP6ServerEvidenceContext ctx) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(cryptoParams.H);
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.A));
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.M1));
            messageDigest.update(this.srp6ClientSession.getSessionKeyHash());
            return new BigInteger(1, messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not locate requested algorithm", e);
        }
    }
}
