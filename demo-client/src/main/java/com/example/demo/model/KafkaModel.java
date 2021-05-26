package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaModel {

    private String name;
    private String surname;
    private String message;
}
