package com.connectsdk.service.airplay.auth.crypt.srp6;

import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.ClientEvidenceRoutine;
import com.nimbusds.srp6.SRP6ClientEvidenceContext;
import com.nimbusds.srp6.SRP6ClientSession;
import com.nimbusds.srp6.SRP6CryptoParams;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class ClientEvidenceRoutineImpl implements ClientEvidenceRoutine {
    private final SRP6ClientSession srp6ClientSession;

    public ClientEvidenceRoutineImpl(SRP6ClientSession srp6ClientSession) {
        this.srp6ClientSession = srp6ClientSession;
    }

    @Override
    public BigInteger computeClientEvidence(SRP6CryptoParams cryptoParams, SRP6ClientEvidenceContext ctx) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(cryptoParams.H);
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(cryptoParams.N));
            byte[] digest = messageDigest.digest();
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(cryptoParams.g));
            byte[] xor = xor(digest, messageDigest.digest());
            messageDigest.update(ctx.userID.getBytes(StandardCharsets.UTF_8));
            byte[] digest2 = messageDigest.digest();
            messageDigest.update(xor);
            messageDigest.update(digest2);
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.s));
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.A));
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.B));
            messageDigest.update(this.srp6ClientSession.getSessionKeyHash());
            return new BigInteger(1, messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not locate requested algorithm", e);
        }
    }

    private static byte[] xor(byte[] b1, byte[] b2) {
        byte[] bArr = new byte[b1.length];
        for (int i = 0; i < b1.length; i++) {
            bArr[i] = (byte) (b1[i] ^ b2[i]);
        }
        return bArr;
    }
}
