package com.doplerer.ludor.dao;

import com.doplerer.ludor.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GameDAO {

    private final Map<String, Game> games;

    @Autowired
    public GameDAO() {
        this.games = new HashMap<>();
    }

    // Checks if game exists based on ID
    public boolean gameExists(String id) {
        return games.containsKey(id);
    }

    // Get Games
    public Map<String, Game> getGames() {
        return games;
    }

    // Get Game by id
    public Game getGame(String id) {
        return games.get(id);
    }

    // Remove game by id
    public void removeGame(String id) {
        games.remove(id);
    }
}
