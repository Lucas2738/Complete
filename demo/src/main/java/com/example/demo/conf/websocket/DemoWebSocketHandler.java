package com.example.demo.conf.websocket;

import com.example.demo.business.RxDemoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component("ReactiveWebSocketHandler")
public class DemoWebSocketHandler implements WebSocketHandler {

    @Autowired
    RxDemoService rxDemoService;

    ObjectMapper om = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<WebSocketMessage> webSocketMessageFlux = rxDemoService.getAllCompanyFilesNonBlocking()
                .delayElements(Duration.ofSeconds(4))
                .map(itm -> {
                    String result = null;
                    try {
                        result = om.writeValueAsString(itm);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return result;
                })
                .map (item -> webSocketSession.textMessage (item));

        return webSocketSession.send(webSocketMessageFlux);
    }
}
