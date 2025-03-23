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
    private final Map<String, Player> sessionPlayers = new ConcurrentHashMap<>();
    private final Map<String, String> sessionGameIDs = new ConcurrentHashMap<>();

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
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue(payload, new TypeReference<>() {});

        String type = jsonMap.get("type");

        if ("JOIN".equals(type)) {
            String username = jsonMap.get("username");
            String partyCode = jsonMap.get("partyCode");

            // Crea el jugador y lo asocia a esta sesión
            Player player = new Player(username, session.getId());
            sessionPlayers.put(session.getId(), player);

            // Envía un mensaje de bienvenida con el ID del jugador
            session.sendMessage(new TextMessage("Username: " + username + " ID: " + player.getId()));

            // Añade el jugador a una partida
            String gameID;
            if (partyCode.equals("")){
                gameID = gameService.joinGame(player);
            }
            else{
                gameID = gameService.joinGame(player,partyCode);
            }

            // Asocia el GameID a esta sesión
            sessionGameIDs.put(session.getId(), gameID);

            session.sendMessage(new TextMessage("Se ha unido a partida: " + gameID ));

        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String sessionId = session.getId();

        // Recupera el jugador y el gameID asociados a esta sesión
        Player player = sessionPlayers.remove(sessionId);
        String gameID = sessionGameIDs.remove(sessionId);

        if (player != null && gameID != null) {
            gameService.removePlayer(gameID, player);
        }

        activeSessions.remove(session.getId());
    }
}
