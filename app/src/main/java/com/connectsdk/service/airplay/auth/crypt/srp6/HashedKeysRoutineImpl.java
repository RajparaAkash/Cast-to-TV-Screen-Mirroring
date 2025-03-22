package com.connectsdk.service.airplay.auth.crypt.srp6;

import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.URoutine;
import com.nimbusds.srp6.URoutineContext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class HashedKeysRoutineImpl implements URoutine {
    @Override
    public BigInteger computeU(SRP6CryptoParams cryptoParams, URoutineContext ctx) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(cryptoParams.H);
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.A));
            messageDigest.update(BigIntegerUtils.bigIntegerToBytes(ctx.B));
            return BigIntegerUtils.bigIntegerFromBytes(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not locate requested algorithm", e);
        }
    }
}
