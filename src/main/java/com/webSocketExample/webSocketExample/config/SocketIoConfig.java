package com.webSocketExample.webSocketExample.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIoConfig {


    @Value("${socket-server.port}")
    private int port;
    @Value("${socket-server.host}")
    private String host;

    @Bean
    public SocketIOServer socketIoServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        return new SocketIOServer(config);

    }
}
