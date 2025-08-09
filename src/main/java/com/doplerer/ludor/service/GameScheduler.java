package com.doplerer.ludor.service;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.engine.GameEngine;
import com.doplerer.ludor.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.Map;

@Service
public class GameScheduler {
    private final GameDAO gameDAO;
    private final GameEngine gameEngine;
    private final long ACTIVATION_TIME = 1;
    private final int MIN_PLAYERS = 3;
    private final WebSocketService ws;


    @Autowired
    public GameScheduler(GameDAO gameDAO, GameEngine gameEngine, WebSocketService ws) {
        this.gameDAO = gameDAO;
        this.gameEngine = gameEngine;
        this.ws = ws;
    }

    // Checks non active games and activate them if possible
    @Scheduled(fixedRate = 5000)
    public void startGames() {
        Map<String, Game> games = gameDAO.getGames();
        for (Game game : games.values()) {
            if (!game.isActive() && game.getTimeCounter() >= ACTIVATION_TIME && game.getPlayers().size()>=MIN_PLAYERS){
                gameEngine.startGame(game);

                // Notifies players
                ws.broadcastMessage(game, new TextMessage("{\"type\": \"GAME_STARTED\", \"gameId\": \"" + game.getID() + "\"}"));
                ws.broadcastStatus(game);
            }

        }
    }
}
