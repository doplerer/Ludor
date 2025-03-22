package com.doplerer.ludor.service;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class  GameService {
    private final GameDAO gameDAO;

    @Autowired
    public GameService(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
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
            if (!game.isActive()) {
                game.addPlayer(player);
                return game.getID();
            }
        }

        String uuid = createGame();
        gameDAO.getGames().get(uuid).addPlayer(player);

        return uuid;
    }


}
