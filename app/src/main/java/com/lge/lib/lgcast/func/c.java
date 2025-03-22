package com.lge.lib.lgcast.func;

import android.util.Base64;

import com.lge.lib.lgcast.iface.MasterKey;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;

public class c {

    private static final int f3056a = 2;
    private static final int b = 30;
    private int c = 10;

    private String a(PublicKey publicKey, byte[] bArr) {
        try {
            if (publicKey != null) {
                if (bArr != null) {
                    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
                    cipher.init(1, publicKey);
                    return Base64.encodeToString(cipher.doFinal(bArr), 0);
                }
                throw new Exception("Invalid data");
            }
            throw new Exception("Invalid key");
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            return null;
        }
    }

    private byte[] a() {
        byte[] bArr = new byte[30];
        try {
            new Random().nextBytes(bArr);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            for (int i = 0; i < 30; i++) {
                bArr[i] = (byte) i;
            }
        }
        for (int i2 = 0; i2 < 30; i2++) {
            if (bArr[i2] == 0) {
                bArr[i2] = 99;
            }
        }
        return bArr;
    }

    private String b(byte[] bArr) {
        try {
            if (bArr != null) {
                return Base64.encodeToString(bArr, 0);
            }
            throw new Exception("Invalid data");
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            return null;
        }
    }

    private PublicKey b(String str) {
        byte[] bArr;
        if (str != null) {
            try {
                bArr = Base64.decode(str, 0);
            } catch (Exception e) {
                com.lge.lib.lgcast.common.a.a(e);
                return null;
            }
        } else {
            bArr = null;
        }
        X509EncodedKeySpec x509EncodedKeySpec = bArr != null ? new X509EncodedKeySpec(bArr) : null;
        PublicKey generatePublic = null;
        try {
            generatePublic = x509EncodedKeySpec != null ? KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec) : null;
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (generatePublic != null) {
            return generatePublic;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] b() {
        int i = this.c;
        this.c = i + 1;
        return new byte[]{(byte) i};
    }

    public ArrayList<MasterKey> a(String str) {
        PublicKey b2;
        if (str != null) {
            try {
                b2 = b(str);
            } catch (Exception e) {
                com.lge.lib.lgcast.common.a.a(e);
                return new ArrayList<>();
            }
        } else {
            b2 = null;
        }
        if (b2 != null) {
            ArrayList<MasterKey> arrayList = new ArrayList<>();
            arrayList.clear();
            for (int i = 0; i < 2; i++) {
                MasterKey masterKey = new MasterKey();
                masterKey.mki = b();
                masterKey.key = a();
                masterKey.mkiSecureText = b(masterKey.mki);
                masterKey.keySecureText = a(b2, masterKey.key);
                arrayList.add(masterKey);
            }
            return arrayList;
        }
        try {
            throw new Exception("Invalid public Key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MasterKey> a(byte[] bArr) {
        ArrayList<MasterKey> arrayList = new ArrayList<>();
        arrayList.clear();
        int i = 0;
        while (i < 2) {
            MasterKey masterKey = new MasterKey();
            i++;
            masterKey.mki = new byte[]{(byte) i};
            masterKey.key = bArr;
            masterKey.mkiSecureText = "unused";
            masterKey.keySecureText = "unused";
            arrayList.add(masterKey);
        }
        return arrayList;
    }
}
