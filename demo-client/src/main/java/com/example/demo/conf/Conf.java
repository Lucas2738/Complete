package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Conf {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
