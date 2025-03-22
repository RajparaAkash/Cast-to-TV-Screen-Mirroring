package com.github.kunal52.pairing;

import android.util.Log;

import com.github.kunal52.exception.PairingException;
import com.github.kunal52.util.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

public class PairingChallengeResponse {
    private static final String HASH_ALGORITHM = "SHA-256";
    private Certificate mClientCertificate;
    private Certificate mServerCertificate;

    public interface DebugLogger {
        void debug(String str);

        void verbose(String str);
    }

    public PairingChallengeResponse(Certificate certificate, Certificate certificate2) {
        this.mClientCertificate = certificate;
        this.mServerCertificate = certificate2;
    }

    public byte[] getAlpha(byte[] bArr) throws PairingException {
        PublicKey publicKey = mClientCertificate.getPublicKey();
        PublicKey publicKey2 = mServerCertificate.getPublicKey();
        logDebug("getAlpha, nonce=" + Utils.bytesToHexString(bArr));
        if (!(publicKey instanceof RSAPublicKey) || !(publicKey2 instanceof RSAPublicKey)) {
            throw new PairingException("Only supports RSA public keys");
        }
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        RSAPublicKey rSAPublicKey2 = (RSAPublicKey) publicKey2;
        try {
            MessageDigest instance = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] byteArray = rSAPublicKey.getModulus().abs().toByteArray();
            byte[] byteArray2 = rSAPublicKey.getPublicExponent().abs().toByteArray();
            byte[] byteArray3 = rSAPublicKey2.getModulus().abs().toByteArray();
            byte[] byteArray4 = rSAPublicKey2.getPublicExponent().abs().toByteArray();
            byte[] removeLeadingNullBytes = removeLeadingNullBytes(byteArray);
            byte[] removeLeadingNullBytes2 = removeLeadingNullBytes(byteArray2);
            byte[] removeLeadingNullBytes3 = removeLeadingNullBytes(byteArray3);
            byte[] removeLeadingNullBytes4 = removeLeadingNullBytes(byteArray4);
            logVerbose("Hash inputs, in order: ");
            logVerbose("   client modulus: " + Utils.bytesToHexString(removeLeadingNullBytes));
            logVerbose("  client exponent: " + Utils.bytesToHexString(removeLeadingNullBytes2));
            logVerbose("   server modulus: " + Utils.bytesToHexString(removeLeadingNullBytes3));
            logVerbose("  server exponent: " + Utils.bytesToHexString(removeLeadingNullBytes4));
            logVerbose("            nonce: " + Utils.bytesToHexString(bArr));
            instance.update(removeLeadingNullBytes);
            instance.update(removeLeadingNullBytes2);
            instance.update(removeLeadingNullBytes3);
            instance.update(removeLeadingNullBytes4);
            instance.update(bArr);
            byte[] digest = instance.digest();
            logDebug("Generated hash: " + Utils.bytesToHexString(digest));
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new PairingException("Could not get digest algorithm", e);
        }
    }

    public byte[] getGamma(byte[] bArr) throws PairingException {
        byte[] alpha = getAlpha(bArr);
        byte[] bArr2 = new byte[(bArr.length * 2)];
        System.arraycopy(alpha, 0, bArr2, 0, bArr.length);
        System.arraycopy(bArr, 0, bArr2, bArr.length, bArr.length);
        return bArr2;
    }

    public byte[] extractNonce(byte[] bArr) {
        if (bArr.length < 2 || bArr.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        int length = bArr.length / 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, length, bArr2, 0, length);
        return bArr2;
    }

    public boolean checkGamma(byte[] bArr) throws PairingException {
        try {
            byte[] extractNonce = extractNonce(bArr);
            logDebug("Nonce is: " + Utils.bytesToHexString(extractNonce));
            logDebug("User gamma is: " + Utils.bytesToHexString(bArr));
            logDebug("Generated gamma is: " + Utils.bytesToHexString(getGamma(extractNonce)));
            return Arrays.equals(bArr, getGamma(extractNonce));
        } catch (IllegalArgumentException unused) {
            logDebug("Illegal nonce value.");
            return false;
        }
    }

    private byte[] removeLeadingNullBytes1(byte[] bArr) {
        int i = 0;
        while (true) {
            boolean z = true;
            boolean z2 = i < bArr.length;
            if (bArr[i] != 0) {
                z = false;
            }
            if (!z2 || !z) {
                break;
            }
            i++;
        }
        byte[] bArr2 = new byte[(bArr.length - i)];
        for (int i2 = i; i2 < bArr.length; i2++) {
            bArr2[i2 - i] = bArr[i2];
        }
        return bArr2;
    }

    private byte[] removeLeadingNullBytes(byte[] inArray) {
        int offset = 0;
        while (offset < inArray.length & inArray[offset] == 0) {
            offset += 1;
        }
        byte[] result = new byte[inArray.length - offset];
        for (int i=offset; i < inArray.length; i++) {
            result[i - offset] = inArray[i];
        }
        return result;
    }

    private void logDebug(String str) {
        Log.e("fatal", "logDebug: " + str );
    }

    private void logVerbose(String str) {
        System.out.println(str);
    }

}
