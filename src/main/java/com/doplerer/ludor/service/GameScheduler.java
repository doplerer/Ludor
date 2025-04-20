package com.doplerer.ludor.service;

import com.doplerer.ludor.config.WebSocketHandler;
import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.engine.GameEngine;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Service
public class GameScheduler {
    private final GameDAO gameDAO;
    private final GameEngine gameEngine;
    private final long ACTIVATION_TIME = 10;
    private final int MIN_PLAYERS = 2;

    @Autowired
    public GameScheduler(GameDAO gameDAO, GameEngine gameEngine) {
        this.gameDAO = gameDAO;
        this.gameEngine = gameEngine;
    }

    // Checks non active games and activate them if possible
    @Scheduled(fixedRate = 5000)
    public void startGames() {
        Map<String, Game> games = gameDAO.getGames();
        for (Game game : games.values()) {
            if (!game.isActive() && game.getTimeCounter() >= ACTIVATION_TIME && game.getPlayers().size()>=MIN_PLAYERS){
                gameEngine.startGame(game);
            }

        }
    }
}
