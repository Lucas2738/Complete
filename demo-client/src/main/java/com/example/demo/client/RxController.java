package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rx")
public class RxController {

    private static final Logger logger = LoggerFactory.getLogger(RxController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CompanyFileWebClient webClient;

    @Value("${microservice.demo.host}")
    private String demoMSHost;

    @Value("${microservice.demo.port}")
    private String demoMSPort;

    @GetMapping("/getFiles")
    public String getFiles(){
        logger.info("GET: /rx/files");
        ResponseEntity<String> message = restTemplate.getForEntity("http://" + demoMSHost + ":" + demoMSPort + "/rx/filesNonBlocking",String.class);
        return "demo: " + message;
    }

    @GetMapping("/getFilesWebClient")
    public String getFilesWebClient(){
        webClient.consume();;
        return "demo: " ;
    }
}
