package com.example.demo.business.signature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

@Component("DemoSignature")
public class CertificateService {

    private static final String KEYSTORE_TYPE = "PKCS12";
    private static final String HASH_ALGH = "SHA-256";
    private static final String SIGN_ALGH = "SHA256withRSA";

    @Value("${cert.mine.certPath}")
    private String certPath;

    @Value("${cert.mine.alias}")
    private String alias;

    @Value("${cert.mine.pwd}")
    private String pwd;

    @Value("${cert.democlient.certPath}")
    private String demoClientCertPath;

    @Value("${cert.democlient.alias}")
    private String demoClientAlias;

    @Value("${cert.democlient.pwd}")
    private String demoClientPwd;

    private PublicKey demoClientPublicKey;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    private Signature demoClientSignature;

    @PostConstruct
    private void init() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, UnrecoverableKeyException {
        KeyStore senderKeyStore = loadKeyStore(certPath, pwd);
        publicKey = getPublicKey(senderKeyStore, alias);
        privateKey = getPrivateKey(senderKeyStore, alias, pwd);

        KeyStore demoClientKeyStore = loadKeyStore(demoClientCertPath, demoClientPwd);
        demoClientPublicKey = getPublicKey(demoClientKeyStore, demoClientAlias);
        demoClientSignature = getSignature(demoClientPublicKey);
    }


    private KeyStore loadKeyStore(String certPath, String pwd) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
        keyStore.load(new FileInputStream(certPath), pwd.toCharArray());
        return keyStore;
    }

    public PublicKey getPublicKey(KeyStore keyStore, String alias) throws KeyStoreException {
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate.getPublicKey();
    }

    public PrivateKey getPrivateKey(KeyStore keyStore, String alias, String pwd) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
       return  (PrivateKey) keyStore.getKey(alias, pwd.toCharArray());
    }

    private byte[] getHash(String message ) throws NoSuchAlgorithmException {
        byte[] messageBytes = message.getBytes();
        MessageDigest md = MessageDigest.getInstance(HASH_ALGH);
        return md.digest(messageBytes);
    }

    private Signature getSignature(PublicKey publicKey) throws InvalidKeyException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance(SIGN_ALGH);
        signature.initVerify(publicKey);
        return signature;
    }

    public boolean verifySenderSignature(String sign, String messagePart) throws NoSuchAlgorithmException, SignatureException {
        byte[] hashMessage = getHash(messagePart);
        byte[] signBytes = Base64.getUrlDecoder().decode(sign);

        demoClientSignature.update(hashMessage);
        return demoClientSignature.verify(signBytes);
    }

    public String encrypt(String content) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] contentBytes = content.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, demoClientPublicKey);
        byte[] cipherContent = cipher.doFinal(contentBytes);
        String encoded = Base64.getEncoder().encodeToString(cipherContent);
        return encoded;
    }

    public String decrypt(String cipherContent) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherContentBytes = Base64.getDecoder().decode(cipherContent.getBytes());
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
        String decoded = new String(decryptedContent);
        return decoded;
    }
}
