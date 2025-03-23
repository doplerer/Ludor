package com.doplerer.ludor.service;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameScheduler {
    private final GameDAO gameDAO;
    private final long ACTIVATION_TIME = 10;
    private final int MIN_PLAYERS = 2;

    @Autowired
    public GameScheduler(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    @Scheduled(fixedRate = 5000)
    public void checkGames() {
        Map<String, Game> games = gameDAO.getGames();
        for (Game game : games.values()) {
            if (!game.isActive() && game.getTimeCounter() >= ACTIVATION_TIME && game.getPlayers().size()>=MIN_PLAYERS){
                game.activateGame();
            }

        }
    }
}
