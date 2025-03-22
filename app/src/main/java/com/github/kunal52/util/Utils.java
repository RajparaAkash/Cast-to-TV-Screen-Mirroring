package com.github.kunal52.util;

import java.math.BigInteger;
import java.security.cert.Certificate;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class Utils {

    public static final byte[] intToBigEndianIntBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static final long intBigEndianBytesToLong(byte[] bArr) {
        return ((((((((long) bArr[0]) & 255) << 8) | (((long) bArr[1]) & 255)) << 8) | (((long) bArr[2]) & 255)) << 8) | (255 & ((long) bArr[3]));
    }

    public static Certificate getPeerCert(SSLSession sSLSession) {
        try {
            Certificate[] peerCertificates = sSLSession.getPeerCertificates();
            if (peerCertificates == null || peerCertificates.length < 1) {
                System.out.println("No Certificate");
            }
            return peerCertificates[0];
        } catch (SSLPeerUnverifiedException unused) {
            System.out.println("No Certificate");
            return null;
        }
    }

    public static Certificate getLocalCert(SSLSession sSLSession) {
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null && localCertificates.length >= 1) {
            return localCertificates[0];
        }
        System.out.println("No Certificate");
        return null;
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format("%0" + (bArr.length * 2) + "x", bigInteger);
    }

    public static byte[] hexStringToBytes1(String str) {
        if (str == null || str.length() == 0 || str.length() % 2 != 0) {
            throw new IllegalArgumentException("Bad input string.");
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, i2 * 2), 16);
            i = i2;
        }
        return bArr;
    }
    public static byte[] hexStringToBytes(String hexstr) {
        if (hexstr == null || hexstr.length() == 0 || (hexstr.length() % 2) != 0) {
            throw new IllegalArgumentException("Bad input string.");
        }

        byte[] result = new byte[hexstr.length() / 2];
        for (int i=0; i < result.length; i++) {
            result[i] = (byte) Integer.parseInt(hexstr.substring(2 * i, 2 * (i + 1)),
                    16);
        }
        return result;
    }

    public static boolean isHexString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!Character.isDigit(charAt) && ((charAt < 'A' || charAt > 'F') && (charAt < 'a' || charAt > 'f'))) {
                return false;
            }
        }
        return true;
    }
}
