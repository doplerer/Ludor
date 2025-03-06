package com.doplerer.ludor.model;

import java.util.List;

public class Turn {

    private Player player;
    private List<Card> move;

    public Turn(Player player, List<Card> move) {
        this.player = player;
        this.move = move;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Card> getMove() {
        return move;
    }

}
