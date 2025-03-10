package com.doplerer.ludor.dao;

import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
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

    // Checks if game exists based on UUID
    public boolean gameExists(String uuid) {
        return games.containsKey(uuid);
    }

    // Create public Game
    public String createGame(){
        String id = UUID.randomUUID().toString();
        Game game = new Game(id);
        games.put(id, game);

        return id;
    }

    // Creates private Game
    public String createGame(String id){
        Game game = new Game(id);
        games.put(id, game);
        return id;
    }

    // Adds player to a private game
    public void joinGame(Player player, String gameID){
        if (gameExists(gameID)){
            Game game = games.get(gameID);
            game.addPlayer(player);
        }else{
            gameID = createGame();
            Game game = games.get(gameID);
            game.addPlayer(player);
        }
    }

    // Adds player to a public game
    public void joinGame(Player player){

    }

}
