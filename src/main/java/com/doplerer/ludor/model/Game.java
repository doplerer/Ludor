package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    private boolean active;
    private List<Player> players;
    private Turn lastTurn;
    private int rotation;


    public Game(String id) {
        this.id = id;
        this.active = false;
        this.players = new ArrayList<>();
        this.lastTurn = null;
        int rotation = 0;
    }


    // Getters & Setters

    public String getID(){
        return id;
    }

    public boolean isActive(){
        return active;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public void updateTurn(Player player, List<Card> move) {
        this.lastTurn = new Turn(player, move);
    }

    public Turn getLastTurn() {
        return lastTurn;
    }

}
