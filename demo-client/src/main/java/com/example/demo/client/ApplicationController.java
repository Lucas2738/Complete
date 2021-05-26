package com.example.demo.client;

import com.example.demo.model.KafkaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/restTemplate")
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservice.demo.host}")
    private String demoMSHost;

    @Value("${microservice.demo.port}")
    private String demoMSPort;

    @GetMapping("/get-motorcycle")
    public String getMotorcycle(){
        logger.info("GET: /restTemplate/get-motorcycle");
        ResponseEntity<String> message = restTemplate.getForEntity("http://" + demoMSHost + ":" + demoMSPort + "/inheritance/msc-motorcycle",String.class);
        return "demo: " + message;
    }

    @GetMapping("/users")
    public String users(){
        logger.info("GET: /restTemplate/users");
        String authStr = "user:password";
        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<?> request = new HttpEntity(headers);

        ResponseEntity<String> message = new RestTemplate().exchange("http://" + demoMSHost + ":" + demoMSPort + "/rest/users", HttpMethod.GET, request, String.class);

        return "demo: " + message;
    }

    @Autowired
    private KafkaTemplate<String, KafkaModel> kafkaTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage(@RequestParam  String message) {

        ListenableFuture<SendResult<String, KafkaModel>> future =
                kafkaTemplate.send("test", KafkaModel.builder()
                        .name("Luca").surname("Cirillo")
                        .message(message).build());

        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaModel>>() {

            @Override
            public void onSuccess(SendResult<String, KafkaModel> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
