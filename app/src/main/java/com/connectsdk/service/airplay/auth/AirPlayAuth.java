package com.connectsdk.service.airplay.auth;

import android.util.Log;

import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.airplay.auth.crypt.Curve25519;
import com.connectsdk.service.airplay.auth.crypt.srp6.AppleSRP6ClientSessionImpl;
import com.dd.plist.NSData;
import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;

import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AirPlayAuth {
    private static final int SOCKET_TIMEOUT = 60000;
    private final InetSocketAddress address;
    private final EdDSAPrivateKey authKey;
    private final String clientId;

    public AirPlayAuth(InetSocketAddress address, String authToken) {
        try {
            this.address = address;
            String[] split = authToken.split("@");
            this.clientId = split[0];
            this.authKey = new EdDSAPrivateKey(new PKCS8EncodedKeySpec(Utils.hexToBytes(split[1])));
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateNewAuthToken() {
        return AuthUtils.randomString(16) + "@" + Utils.bytesToHex(new KeyPairGenerator().generateKeyPair().getPrivate().getEncoded());
    }

    public void startPairing() throws IOException {
        Socket connect = connect();
        AuthUtils.postData(connect, "/pair-pin-start", null, null);
        connect.close();
    }

    private Socket connect() throws IOException {
        Socket socket = new Socket();
        socket.setReuseAddress(true);
        socket.setSoTimeout(60000);
        socket.connect(this.address, 60000);
        return socket;
    }

    public void doPairing(String pin) throws Exception {
        Socket connect = connect();
        PairSetupPin1Response doPairSetupPin1 = doPairSetupPin1(connect);
        AppleSRP6ClientSessionImpl appleSRP6ClientSessionImpl = new AppleSRP6ClientSessionImpl();
        appleSRP6ClientSessionImpl.step1(this.clientId, pin);
        appleSRP6ClientSessionImpl.step2(SRP6CryptoParams.getInstance(2048, "SHA-1"), BigIntegerUtils.bigIntegerFromBytes(doPairSetupPin1.SALT), BigIntegerUtils.bigIntegerFromBytes(doPairSetupPin1.PK));
        appleSRP6ClientSessionImpl.step3(BigIntegerUtils.bigIntegerFromBytes(doPairSetupPin2(connect, BigIntegerUtils.bigIntegerToBytes(appleSRP6ClientSessionImpl.getPublicClientValue()), BigIntegerUtils.bigIntegerToBytes(appleSRP6ClientSessionImpl.getClientEvidenceMessage())).PROOF));
        doPairSetupPin3(connect, appleSRP6ClientSessionImpl.getSessionKeyHash());
        connect.close();
    }

    public Socket authenticate() throws Exception {
        Socket connect = connect();
        byte[] bArr = new byte[32];
        new Random().nextBytes(bArr);
        byte[] bArr2 = new byte[32];
        Curve25519.keygen(bArr2, null, bArr);
        doPairVerify2(connect, doPairVerify1(connect, bArr2), bArr, bArr2);
        Log.i("LSH", "Pair Verify finished!");
        return connect;
    }

    private PairSetupPin1Response doPairSetupPin1(Socket socket) throws Exception {
        NSDictionary nSDictionary = (NSDictionary) PropertyListParser.parse(AuthUtils.postData(socket, "/pair-setup-pin", HttpMessage.CONTENT_TYPE_APPLICATION_PLIST, AuthUtils.createPList(new HashMap<String, String>() {
            {
                put("method", "pin");
                put("user", AirPlayAuth.this.clientId);
            }
        })));
        if (nSDictionary.containsKey("pk") && nSDictionary.containsKey("salt")) {
            return new PairSetupPin1Response(((NSData) nSDictionary.get((Object) "pk")).bytes(), ((NSData) nSDictionary.get((Object) "salt")).bytes());
        }
        throw new Exception();
    }

    private PairSetupPin2Response doPairSetupPin2(Socket socket, final byte[] publicClientValueA, final byte[] clientEvidenceMessageM1) throws Exception {
        byte[] pairSetupPinRequestData = AuthUtils.createPList(new HashMap<String, byte[]>() {{
            put("pk", publicClientValueA);
            put("proof", clientEvidenceMessageM1);
        }});

        byte[] pairSetupPin2ResponseBytes = AuthUtils.postData(socket, "/pair-setup-pin", "application/x-apple-binary-plist", pairSetupPinRequestData);

        NSDictionary pairSetupPin2Response = (NSDictionary) PropertyListParser.parse(pairSetupPin2ResponseBytes);
        if (pairSetupPin2Response.containsKey("proof")) {
            byte[] proof = ((NSData) pairSetupPin2Response.get("proof")).bytes();
            return new PairSetupPin2Response(proof);
        }
        throw new Exception();
    }

    private PairSetupPin3Response doPairSetupPin3(Socket socket, final byte[] sessionKeyHashK) throws Exception {

        MessageDigest sha512Digest = MessageDigest.getInstance("SHA-512");
        sha512Digest.update("Pair-Setup-AES-Key".getBytes(StandardCharsets.UTF_8));
        sha512Digest.update(sessionKeyHashK);
        byte[] aesKey = Arrays.copyOfRange(sha512Digest.digest(), 0, 16);

        sha512Digest.update("Pair-Setup-AES-IV".getBytes(StandardCharsets.UTF_8));
        sha512Digest.update(sessionKeyHashK);
        byte[] aesIV = Arrays.copyOfRange(sha512Digest.digest(), 0, 16);

        int lengthB;
        int lengthA = lengthB = aesIV.length - 1;
        for (; lengthB >= 0 && 256 == ++aesIV[lengthA]; lengthA = lengthB += -1) ;

        Cipher aesGcm128Encrypt = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
        aesGcm128Encrypt.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, aesIV));
        final byte[] aesGcm128ClientLTPK = aesGcm128Encrypt.doFinal(authKey.getAbyte());

        byte[] pairSetupPinRequestData = AuthUtils.createPList(new HashMap<String, byte[]>() {{
            put("epk", Arrays.copyOfRange(aesGcm128ClientLTPK, 0, aesGcm128ClientLTPK.length - 16));
            put("authTag", Arrays.copyOfRange(aesGcm128ClientLTPK, aesGcm128ClientLTPK.length - 16, aesGcm128ClientLTPK.length));
        }});

        byte[] pairSetupPin3ResponseBytes = AuthUtils.postData(socket, "/pair-setup-pin", "application/x-apple-binary-plist", pairSetupPinRequestData);

        NSDictionary pairSetupPin3Response = (NSDictionary) PropertyListParser.parse(pairSetupPin3ResponseBytes);

        if (pairSetupPin3Response.containsKey("epk") && pairSetupPin3Response.containsKey("authTag")) {
            byte[] epk = ((NSData) pairSetupPin3Response.get("epk")).bytes();
            byte[] authTag = ((NSData) pairSetupPin3Response.get("authTag")).bytes();

            return new PairSetupPin3Response(epk, authTag);
        }
        throw new Exception();
    }

    private byte[] doPairVerify1(Socket socket, byte[] randomPublicKey) throws Exception {
        return AuthUtils.postData(socket, "/pair-verify", "application/octet-stream", AuthUtils.concatByteArrays(new byte[]{1, 0, 0, 0}, randomPublicKey, this.authKey.getAbyte()));
    }

    private void doPairVerify2(Socket socket, byte[] pairVerify1Response, byte[] randomPrivateKey, byte[] randomPublicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException, SignatureException {
        byte[] copyOfRange = Arrays.copyOfRange(pairVerify1Response, 0, 32);
        byte[] bArr = new byte[32];
        Curve25519.curve(bArr, randomPrivateKey, copyOfRange);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update("Pair-Verify-AES-Key".getBytes(StandardCharsets.UTF_8));
        messageDigest.update(bArr);
        byte[] copyOfRange2 = Arrays.copyOfRange(messageDigest.digest(), 0, 16);
        messageDigest.update("Pair-Verify-AES-IV".getBytes(StandardCharsets.UTF_8));
        messageDigest.update(bArr);
        byte[] copyOfRange3 = Arrays.copyOfRange(messageDigest.digest(), 0, 16);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(1, new SecretKeySpec(copyOfRange2, "AES"), new IvParameterSpec(copyOfRange3));
        cipher.update(Arrays.copyOfRange(pairVerify1Response, 32, pairVerify1Response.length));
        EdDSAEngine edDSAEngine = new EdDSAEngine();
        edDSAEngine.initSign(this.authKey);
        AuthUtils.postData(socket, "/pair-verify", "application/octet-stream", AuthUtils.concatByteArrays(new byte[]{0, 0, 0, 0}, cipher.update(edDSAEngine.signOneShot(AuthUtils.concatByteArrays(randomPublicKey, copyOfRange)))));
    }
}
