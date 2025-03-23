package com.doplerer.ludor.engine;

import com.doplerer.ludor.dao.GameDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class GameEngine {

    private final GameDAO gameDAO;

    @Autowired
    public GameEngine(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }
}
