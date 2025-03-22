package com.doplerer.ludor.config;

import com.doplerer.ludor.model.Player;
import com.doplerer.ludor.service.GameService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private GameService gameService;
    private final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();
    private Player player;
    private String gameID;

    @Autowired
    public WebSocketHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        activeSessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        String payload = message.getPayload();

        // Convertir JSON a objeto con Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue(payload, new TypeReference<>() {});

        String type = jsonMap.get("type");

        if ("JOIN".equals(type)) {
            String username = jsonMap.get("username");
            String partyCode = jsonMap.get("partyCode");

            // Crea el jugador
            player = new Player(username, session.getId());

            // Envía un mensaje de bienvenida con el ID del jugador
            session.sendMessage(new TextMessage("Username: " + username + " ID: " + player.getId()));

            // Añade el jugador a una partida
            if (partyCode.equals("")){
                gameID = gameService.joinGame(player);
            }
            else{
                gameID = gameService.joinGame(player,partyCode);
            }

            session.sendMessage(new TextMessage("Se ha unido a partida: " + gameID ));

        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        gameService.removePlayer(gameID, player);

        activeSessions.remove(session.getId());
    }
}
