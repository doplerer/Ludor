package com.doplerer.ludor.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler WebSocketHandler;

    public WebSocketConfig(WebSocketHandler WebSocketHandler) {
        this.WebSocketHandler = WebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(WebSocketHandler, "/game")
                .setAllowedOrigins("*")
                .withSockJS(); // Si necesitas compatibilidad con navegadores antiguos
    }

    // Habilita compresión en WebSockets y HTTP
    @Bean
    public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> nettyCompressionConfig() {
        return factory -> factory.addServerCustomizers(httpServer ->
                httpServer.compress(true)
        );
    }
}
