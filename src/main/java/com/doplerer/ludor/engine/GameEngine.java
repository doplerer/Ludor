package com.doplerer.ludor.engine;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.model.Card;
import com.doplerer.ludor.model.Deck;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.model.Player;
import com.doplerer.ludor.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameEngine {

    private final GameDAO gameDAO;
    private final WebSocketService ws;

    @Autowired
    public GameEngine(GameDAO gameDAO, WebSocketService ws) {
        this.gameDAO = gameDAO;
        this.ws = ws;
    }

    // Starts game
    public void startGame(Game game){
        // Activate Game instance
        game.activateGame();

        // Deals cards
        dealCards(game);

        // Instancia turno
        // TODO

        // Notifies players
        ws.broadcastMessage(game, new TextMessage("{\"type\": \"GAME_STARTED\", \"gameId\": \"" + game.getID() + "\"}"));
        ws.broadcastStatus(game);
    }

    private void dealCards(Game game){
        // Generates a Shuffled deck
        Deck deck = new Deck();
        deck.shuffle();


        for (int i = 0; i < deck.getDeck().size(); i++) {
            Player p = game.getPlayers().get(i % game.getPlayers().size());
            p.addCard(deck.getDeck().get(i));
            p.updateCardCount();
        }

    }
}
