package com.doplerer.ludor.service;

import com.doplerer.ludor.config.WebSocketHandler;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

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
}
