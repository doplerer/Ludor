package com.doplerer.ludor.config;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.engine.GameEngine;
import com.doplerer.ludor.model.Game;
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
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {


    private final GameService gameService;
    private final GameEngine gameEngine;
    private final GameDAO gameDAO;
    private final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();
    private final Map<String, Player> sessionPlayers = new ConcurrentHashMap<>();
    private final Map<String, String> sessionGameIDs = new ConcurrentHashMap<>();

    @Autowired
    public WebSocketHandler(GameService gameService, GameEngine gameEngine, GameDAO gameDAO) {
        this.gameService = gameService;
        this.gameEngine = gameEngine;
        this.gameDAO = gameDAO;
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

            // Create player and links it to this session
            Player player = new Player(username, session.getId());
            sessionPlayers.put(session.getId(), player);

            // adds player to a game
            String gameID;
            if (partyCode.equals("")){
                gameID = gameService.joinGame(player);
            }
            else{
                gameID = gameService.joinGame(player,partyCode);
            }

            // Links gameID to this session
            sessionGameIDs.put(session.getId(), gameID);

            session.sendMessage(new TextMessage("Se ha unido a partida: " + gameID ));

        } else if ("MOVE".equals(type)) {
            String gameID = jsonMap.get("gameId");
            Game currentGame = gameDAO.getGame(gameID);
            Player currentPlayer = sessionPlayers.get(session.getId());

            // Checks if player and game exists
            if (currentPlayer == null || currentGame == null) {
                return;
            }

            // Checks if turn is valid and process move
            if (Objects.equals(currentGame.getRotation(), currentPlayer.getId())){
                gameEngine.processMove(currentGame, currentPlayer, move);
            }



        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String sessionId = session.getId();

        // gets player and the gameID linked to this session
        Player player = sessionPlayers.remove(sessionId);
        String gameID = sessionGameIDs.remove(sessionId);

        if (player != null && gameID != null) {
            gameService.removePlayer(gameID, player);
        }

        activeSessions.remove(session.getId());
    }

    // Getters

    public WebSocketSession getSession(String playerId) {
        return activeSessions.get(playerId);
    }
}
