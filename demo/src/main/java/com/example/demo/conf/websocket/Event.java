package com.example.demo.conf.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    private String employeeId;
    private String creationTime;
}
