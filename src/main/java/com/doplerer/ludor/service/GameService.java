package com.doplerer.ludor.service;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.engine.GameEngine;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class  GameService {
    private final GameDAO gameDAO;
    private final GameEngine gameEngine;

    @Autowired
    public GameService(GameDAO gameDAO, GameEngine gameEngine) {
        this.gameDAO = gameDAO;
        this.gameEngine = gameEngine;
    }

    // Create public Game
    public String createGame(){
        String id = UUID.randomUUID().toString();
        Game game = new Game(id);
        gameDAO.getGames().put(id, game);

        return id;
    }

    // Creates private Game
    public String createGame(String id){
        Game game = new Game(id);
        game.lockGame();
        gameDAO.getGames().put(id, game);
        return id;
    }

    // Adds player to a private game
    public String joinGame(Player player, String gameID){
        if (gameDAO.gameExists(gameID)){
            Game game = gameDAO.getGame(gameID);
            game.addPlayer(player);
        }else{
            gameID = createGame(gameID);
            Game game = gameDAO.getGame(gameID);
            game.addPlayer(player);
        }

        return gameID;
    }

    // Adds player to a public game
    public String joinGame(Player player){
        for (Game game : gameDAO.getGames().values()){
            if (!game.isActive() && !game.isLocked()) {
                game.addPlayer(player);
                return game.getID();
            }
        }

        String uuid = createGame();
        gameDAO.getGames().get(uuid).addPlayer(player);

        return uuid;
    }

    // Removes Player from game by gameID & removes game if empty
    public void removePlayer(String gameID, Player player){
        if (gameDAO.gameExists(gameID)){
            Game game = gameDAO.getGame(gameID);
            game.removePlayer(player);
            if(game.getPlayers().size()==0){
                gameDAO.removeGame(gameID);
            }
        }
    }

}
