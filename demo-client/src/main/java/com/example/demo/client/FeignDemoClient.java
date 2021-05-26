package com.example.demo.client;

import com.example.demo.model.SecretDocumentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-ms", url = "${microservice.demo.address}")
public interface FeignDemoClient {
    @RequestMapping("/inheritance/msc-motorcycle")
    String motorcycles();
    
    @RequestMapping("/rest/users")
    String users();

    @RequestMapping(method = RequestMethod.POST, value = "/rest/verifySignatureViaAOPAndJCA")
    boolean verifySign(@RequestBody SecretDocumentRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/sendEncrypetdData")
    String sendEncrypetdData(@RequestBody SecretDocumentRequest encryptedData);
}
