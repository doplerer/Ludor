package com.doplerer.ludor.service;

import com.doplerer.ludor.config.WebSocketHandler;
import com.doplerer.ludor.model.Card;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import com.doplerer.ludor.model.Turn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebSocketService {

    private final WebSocketHandler webSocketHandler;

    @Autowired
    public WebSocketService(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    // Sends message to all players on a game
    public void broadcastMessage(Game game, TextMessage message) {
        for (Player player : game.getPlayers()) {
            WebSocketSession session = webSocketHandler.getSession(player.getId());
            if (session != null && session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Sends status of game to each player
    public void broadcastStatus(Game game) {
        ObjectMapper mapper = new ObjectMapper();

        for (Player player : game.getPlayers()) {
            WebSocketSession session = webSocketHandler.getSession(player.getId());

            if (session != null && session.isOpen()) {
                try {
                    Map<String, Object> statusData = new HashMap<>();
                    statusData.put("type", "STATUS");

                    statusData.put("playerId", player.getId());
                    statusData.put("username", player.getUsername());
                    statusData.put("role", player.getRole());
                    statusData.put("cards", player.getCards());

                    statusData.put("players", game.getPlayers());
                    statusData.put("lastTurn", game.getLastTurn());
                    statusData.put("currentTurn", game.getCurrentTurn());

                    String jsonData = mapper.writeValueAsString(statusData);
                    TextMessage message = new TextMessage(jsonData);

                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
