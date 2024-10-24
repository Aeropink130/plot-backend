package com.aeropink.plot.configuration;

import com.aeropink.plot.model.CustomPrincipal;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(
            org.springframework.http.server.ServerHttpRequest request,
            org.springframework.web.socket.WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        // Generar un ID único para el usuario
        String userId = UUID.randomUUID().toString();
        return new CustomPrincipal(userId);
    }
}
