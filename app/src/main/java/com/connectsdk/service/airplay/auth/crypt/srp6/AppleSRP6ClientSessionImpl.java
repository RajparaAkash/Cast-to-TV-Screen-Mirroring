package com.connectsdk.service.airplay.auth.crypt.srp6;

import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6ClientSession;
import com.nimbusds.srp6.XRoutineWithUserIdentity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;


public class AppleSRP6ClientSessionImpl extends SRP6ClientSession {
    public AppleSRP6ClientSessionImpl() {
        setClientEvidenceRoutine(new ClientEvidenceRoutineImpl(this));
        setServerEvidenceRoutine(new ServerEvidenceRoutineImpl(this));
        setXRoutine(new XRoutineWithUserIdentity());
        setHashedKeysRoutine(new HashedKeysRoutineImpl());
    }

    @Override
    public byte[] getSessionKeyHash() {
        if (this.S == null) {
            return null;
        }
        MessageDigest messageDigestInstance = this.config.getMessageDigestInstance();
        if (messageDigestInstance == null) {
            throw new IllegalArgumentException("Unsupported hash algorithm 'H': " + this.config.H);
        }
        messageDigestInstance.update(BigIntegerUtils.bigIntegerToBytes(this.S));
        messageDigestInstance.update(new byte[]{0, 0, 0, 0});
        byte[] digest = messageDigestInstance.digest();
        messageDigestInstance.update(BigIntegerUtils.bigIntegerToBytes(this.S));
        messageDigestInstance.update(new byte[]{0, 0, 0, 1});
        byte[] digest2 = messageDigestInstance.digest();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(digest);
            byteArrayOutputStream.write(digest2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
