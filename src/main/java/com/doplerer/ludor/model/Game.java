package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    private boolean active;
    private boolean locked;
    private long timeCounter;
    private List<Player> players;
    private List<Turn> turnRecord;
    private Player currentTurn;
    private int rotation; //

    // Constructor
    public Game(String id) {
        this.id = id;
        this.active = false;
        this.locked = false;
        this.players = new ArrayList<>();
        this.turnRecord = null;
        this.currentTurn = null;
        int rotation = 0;
    }

    // Getters & Setters

    public String getID(){
        return id;
    }

    public boolean isActive(){
        return active;
    }

    public boolean isLocked(){
        return locked;
    }

    public void lockGame(){
        this.locked = true;
    }

    public long getTimeCounter(){
        return System.currentTimeMillis()/1000 - timeCounter;
    }

    public void activateGame(){
        this.active = true;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
        this.timeCounter = System.currentTimeMillis()/1000;
    }

    public void removePlayer(Player player){
        this.players.remove(player);
        this.timeCounter = System.currentTimeMillis()/1000;
        this.active = false;
    }

    public void updateTurn(Player player, List<Card> move) {
        this.turnRecord.add(new Turn(player, move));
    }

    public Turn getLastTurn() {
        return turnRecord.get(turnRecord.size() - 1);
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }
}
