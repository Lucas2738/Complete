package com.example.demo.business.signature;

public abstract class CertificateHolder {

    private String signature;
    private String encryptedContent;
    private String dencryptedContent;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public void setEncryptedContent(String encryptedContent) {
        this.encryptedContent = encryptedContent;
    }

    public String getDencryptedContent() {
        return dencryptedContent;
    }

    public void setDencryptedContent(String dencryptedContent) {
        this.dencryptedContent = dencryptedContent;
    }

    public abstract String getMessage();
}
