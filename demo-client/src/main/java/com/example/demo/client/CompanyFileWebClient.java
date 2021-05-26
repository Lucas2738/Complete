package com.example.demo.client;

import com.example.demo.model.CompanyFiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CompanyFileWebClient {

    @Value("${microservice.demo.address}")
    private String demoAddress;



    public void consume() {
        WebClient client = WebClient.create(demoAddress + "/rx");

        Flux<CompanyFiles> employeeFlux = client.get()
                .uri("/filesNonBlocking")
                .retrieve()
                .bodyToFlux(CompanyFiles.class);

        employeeFlux.subscribe(System.out::println);
    }
}