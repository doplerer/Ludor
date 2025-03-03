package com.doplerer.ludor.model;

import java.util.List;

public class Player {
    private String username;
    private List<Card> cards;
    private Byte pos; // 2=Presidente 1=VicePresi 0=Neutro -1=ViceCulo -2=Culo

    public Player(String username, List<Card> cards, Byte pos) {
        this.username = username;
        this.cards = cards;
        this.pos = pos;
    }

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Byte getPos() {
        return pos;
    }

    public void setPos(Byte pos) {
        this.pos = pos;
    }
}
