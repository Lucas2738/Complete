package com.example.demo.facade;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;

public class CompanyFilesSocketClient {


    public static void main(String[] args) {
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/chat"), session -> session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(System.out::println)
                .then())
                .block(); // to subscribe and return the value
    }
}
