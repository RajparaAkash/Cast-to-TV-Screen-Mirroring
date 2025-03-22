package com.github.kunal52.ssl;

import com.github.kunal52.remote.Remotemessage;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V1CertificateGenerator;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.security.auth.x500.X500Principal;

public class SslUtil {
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance("RSA").generateKeyPair();
    }

    public static KeyStore getEmptyKeyStore() throws GeneralSecurityException, IOException {
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        instance.load(null, null);
        return instance;
    }

    public static X509Certificate generateX509V1Certificate(KeyPair keyPair, String str) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        Calendar instance = Calendar.getInstance();
        instance.set(2009, 0, 1);
        Date date = new Date(instance.getTimeInMillis());
        instance.set(2029, 0, 1);
        Date date2 = new Date(instance.getTimeInMillis());
        BigInteger valueOf = BigInteger.valueOf(Math.abs(System.currentTimeMillis()));
        X509V1CertificateGenerator x509V1CertificateGenerator = new X509V1CertificateGenerator();
        X500Principal x500Principal = new X500Principal(str);
        x509V1CertificateGenerator.setSerialNumber(valueOf);
        x509V1CertificateGenerator.setIssuerDN(x500Principal);
        x509V1CertificateGenerator.setNotBefore(date);
        x509V1CertificateGenerator.setNotAfter(date2);
        x509V1CertificateGenerator.setSubjectDN(x500Principal);
        x509V1CertificateGenerator.setPublicKey(keyPair.getPublic());
        x509V1CertificateGenerator.setSignatureAlgorithm("SHA256WithRSAEncryption");
        return x509V1CertificateGenerator.generate(keyPair.getPrivate(), BouncyCastleProvider.PROVIDER_NAME);
    }

    public static X509Certificate generateX509V3Certificate(KeyPair keyPair, String str, Date date, Date date2, BigInteger bigInteger) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        X509V3CertificateGenerator x509V3CertificateGenerator = new X509V3CertificateGenerator();
        X500Name x500Name = new X500Name(str);
        X500Principal x500Principal = new X500Principal(str);
        x509V3CertificateGenerator.setSerialNumber(bigInteger);
        x509V3CertificateGenerator.setIssuerDN(x500Principal);
        x509V3CertificateGenerator.setSubjectDN(x500Principal);
        x509V3CertificateGenerator.setNotBefore(date);
        x509V3CertificateGenerator.setNotAfter(date2);
        x509V3CertificateGenerator.setPublicKey(keyPair.getPublic());
        x509V3CertificateGenerator.setSignatureAlgorithm("SHA256WithRSAEncryption");
        x509V3CertificateGenerator.addExtension(X509Extension.basicConstraints, true, (ASN1Encodable) new BasicConstraints(false));
        x509V3CertificateGenerator.addExtension(X509Extension.keyUsage, true, (ASN1Encodable) new KeyUsage((int) Remotemessage.KEYCODE_VOLUME_MUTE_VALUE));
        x509V3CertificateGenerator.addExtension(X509Extension.extendedKeyUsage, true, (ASN1Encodable) new ExtendedKeyUsage(KeyPurposeId.id_kp_serverAuth));
        x509V3CertificateGenerator.addExtension(X509Extension.authorityKeyIdentifier, true, (ASN1Encodable) createAuthorityKeyIdentifier(keyPair.getPublic(), x500Name, bigInteger));
        x509V3CertificateGenerator.addExtension(X509Extension.subjectAlternativeName, false, (ASN1Encodable) new GeneralNames(new GeneralName(1, "googletv@test.test")));
        return x509V3CertificateGenerator.generate(keyPair.getPrivate());
    }

    private static AuthorityKeyIdentifier createAuthorityKeyIdentifier(PublicKey publicKey, X500Name x500Name, BigInteger bigInteger) {
        GeneralName generalName = new GeneralName(x500Name);
        try {
            return new AuthorityKeyIdentifier(new SubjectPublicKeyInfo((ASN1Sequence) new ASN1InputStream(publicKey.getEncoded()).readObject()), new GeneralNames(generalName), bigInteger);
        } catch (IOException unused) {
            throw new RuntimeException("Error encoding public key");
        }
    }

    public static X509Certificate generateX509V3Certificate(KeyPair keyPair, String str) throws GeneralSecurityException {
        Calendar instance = Calendar.getInstance();
        instance.set(2009, 0, 1);
        Date date = new Date(instance.getTimeInMillis());
        instance.set(2099, 0, 1);
        return generateX509V3Certificate(keyPair, str, date, new Date(instance.getTimeInMillis()), BigInteger.valueOf(Math.abs(System.currentTimeMillis())));
    }

    public static X509Certificate generateX509V3Certificate(KeyPair keyPair, String str, BigInteger bigInteger) throws GeneralSecurityException {
        Calendar instance = Calendar.getInstance();
        instance.set(2009, 0, 1);
        Date date = new Date(instance.getTimeInMillis());
        instance.set(2099, 0, 1);
        return generateX509V3Certificate(keyPair, str, date, new Date(instance.getTimeInMillis()), bigInteger);
    }

    public SSLContext generateTestSslContext() throws GeneralSecurityException, IOException {
        SSLContext instance = SSLContext.getInstance("SSLv3");
        instance.init(generateTestServerKeyManager("SunX509", "test"), new TrustManager[]{new DummyTrustManager()}, null);
        return instance;
    }

    public static KeyManager[] getFileBackedKeyManagers(String str, String str2, String str3) throws GeneralSecurityException, IOException {
        KeyManagerFactory instance = KeyManagerFactory.getInstance(str);
        KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
        instance2.load(new FileInputStream(str2), str3.toCharArray());
        instance.init(instance2, str3.toCharArray());
        return instance.getKeyManagers();
    }

    public static KeyManager[] generateTestServerKeyManager(String str, String str2) throws GeneralSecurityException, IOException {
        KeyManagerFactory instance = KeyManagerFactory.getInstance(str);
        KeyPair generateRsaKeyPair = generateRsaKeyPair();
        Certificate[] certificateArr = {generateX509V1Certificate(generateRsaKeyPair, "CN=Test Server Cert")};
        KeyStore emptyKeyStore = getEmptyKeyStore();
        emptyKeyStore.setKeyEntry("test-server", generateRsaKeyPair.getPrivate(), str2.toCharArray(), certificateArr);
        instance.init(emptyKeyStore, str2.toCharArray());
        return instance.getKeyManagers();
    }
}
