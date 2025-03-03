package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    private List<Player> players;
    private Player lastTurn;
    private List<Card> move;
    private Byte rotation;


    public Game(String id, List<Player> players) {
        this.id = id;
        this.players = players;
        this.move = new ArrayList<>();
    }


    // Getters & Setters

    public String getID(){
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayers(Player player){
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public void updateMove(Player player, List<Card> move){
        this.lastTurn = player;
        this.move = move;
    }





}
