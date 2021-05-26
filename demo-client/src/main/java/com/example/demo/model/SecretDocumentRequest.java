package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecretDocumentRequest {
    private String name;
    private String surname;
    private String email;
    private String role;
    private String signature;
    private String encryptedContent;
    private String dencryptedContent;
}
