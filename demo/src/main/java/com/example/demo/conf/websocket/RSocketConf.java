package com.example.demo.conf.websocket;

import org.springframework.boot.rsocket.server.RSocketServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSocketConf {

    @Bean
    public RSocketServerCustomizer fragmentationCustomizer() {
        return (server) -> server.fragment(16384); // use a non-zero value that tells the server to fragment messages at this size
        // you should use a value that's not too small to avoid overhead, but not larger than the `65536` websocket max frame size
    }
}
