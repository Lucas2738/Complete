package com.example.demo.client;

import com.example.demo.model.SecretDocumentRequest;
import com.example.demo.model.SecretDocumentResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

@EnableFeignClients
@RestController
public class DemoClient {

    @Value("${cert.mine.certPath}")
    private String certPath;

    @Value("${cert.mine.alias}")
    private String alias;

    @Value("${cert.mine.pwd}")
    private String pwd;

    @Value("${cert.demo.certPath}")
    private String demoCertPath;

    @Value("${cert.demo.alias}")
    private String demoAlias;

    @Value("${cert.demo.pwd}")
    private String demoPwd;

    @Autowired
    private FeignDemoClient feignDemoClient;

    @RequestMapping("/get-users")
    public String users() {
        return feignDemoClient.motorcycles();
    }
    
    @RequestMapping("/get-motorcycle")
    public String motorcycles() {
        return feignDemoClient.users();
    }

    @PostMapping("/verify-sign")
    public boolean verifySign(@RequestBody SecretDocumentRequest request) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(certPath), pwd.toCharArray());
        ///////////

        String message = request.getName() + request.getSurname() + request.getEmail() + request.getRole();

        byte[] messageBytes = message.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = md.digest(messageBytes);

        ///////////
        PrivateKey privateKey =
                (PrivateKey) keyStore.getKey(alias, pwd.toCharArray());

        Signature sing = Signature.getInstance("SHA256withRSA");
        sing.initSign(privateKey);
        sing.update(messageHash);
        byte[] digitalSignature = sing.sign();

        /////////////

        String sign = Base64.getUrlEncoder().encodeToString(digitalSignature);
        request.setSignature(sign);

        return feignDemoClient.verifySign(request);
    }

    @PostMapping("/send-encrypted-data")
    public SecretDocumentResponse encryptedData(@RequestBody SecretDocumentRequest request) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(demoCertPath), demoPwd.toCharArray());
        ///////////
        PublicKey publicKey = getPublicKey(keyStore, demoAlias);

        ////////////
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String message = objectMapper.writeValueAsString(request);
        String encryptedData = encrypt(message, publicKey);

        request.setEncryptedContent(encryptedData);

        encryptedData = feignDemoClient.sendEncrypetdData(request);

        keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(certPath), pwd.toCharArray());
        PrivateKey privateKey =
                (PrivateKey) keyStore.getKey(alias, pwd.toCharArray());

        String decrypted = decrypt(encryptedData, privateKey);

        return objectMapper.readValue(decrypted, SecretDocumentResponse.class);
    }

    public PublicKey getPublicKey(KeyStore keyStore, String alias) throws KeyStoreException {
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate.getPublicKey();
    }

    public String encrypt(String content, PublicKey pubKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] contentBytes = content.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherContent = cipher.doFinal(contentBytes);
        String encoded = Base64.getEncoder().encodeToString(cipherContent);
        return encoded;
    }

    public String decrypt(String cipherContent, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherContentBytes = Base64.getDecoder().decode(cipherContent.getBytes());
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
        String decoded = new String(decryptedContent);
        return decoded;
    }
}
