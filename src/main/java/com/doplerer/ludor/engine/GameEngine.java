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
        // Activate Game instance variable
        game.activateGame();

        // Deals cards
        dealCards(game);

        // Determines current turn
        // Checks if there are roles
        List<Player> players = game.getPlayers();
        int counter = 0;
        for (int i=0; i< players.size();i++){
            Player p = players.get(i);
            if (p.getRole() == 0) {
                counter++;
            }
        }
        // Sets rotation if there aren't roles
        // -- Person with 3 gold starts
        if (counter == players.size()) {
            Player startPlayer = null;
            outerLoop: for (Player player : players) {
                for (Card card : player.getCards()) {
                    if (card.getValue() == 0) {
                        startPlayer = player;
                        break outerLoop;
                    }
                }
            }

            game.setCurrentTurn(startPlayer);
        }
        // Set rotation if there are roles
        // -- Person with role -2 (Culo) starts
        else{
            for (int i=0; i< players.size();i++){
                Player p = players.get(i);
                if (p.getRole() == -2) {
                    game.setCurrentTurn(p);
                    break;
                }
            }
        }

        // Notifies players
        ws.broadcastMessage(game, new TextMessage("{\"type\": \"GAME_STARTED\", \"gameId\": \"" + game.getID() + "\"}"));
        ws.broadcastStatus(game);
    }

    public void processMove(Game game, Player player, List<Card> hand) {

        // Updates game turn history
        game.updateTurn(player, hand);

        // Updates new currentTurn
        // TODO

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
