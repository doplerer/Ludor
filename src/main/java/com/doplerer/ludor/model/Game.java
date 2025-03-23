package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    private boolean active;
    private boolean locked;
    private long timeCounter;
    private List<Player> players;
    private Turn lastTurn;
    private int rotation; //

    // Constructor
    public Game(String id) {
        this.id = id;
        this.active = false;
        this.locked = false;
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

    public boolean isLocked(){
        return locked;
    }

    public void lockGame(){
        this.locked = true;
    }

    public long getTimeCounter(){
        return System.currentTimeMillis()/1000 - timeCounter;
    }

    public void activateGameByThreshold(long threshold){
        if (!active && getTimeCounter() >= threshold){
            this.active = true;
        }
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
    }

    public void updateTurn(Player player, List<Card> move) {
        this.lastTurn = new Turn(player, move);
    }

    public Turn getLastTurn() {
        return lastTurn;
    }

}
