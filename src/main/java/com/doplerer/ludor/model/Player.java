package com.doplerer.ludor.model;
import java.util.ArrayList;
import java.util.UUID;

import java.util.List;

public class Player {
    private UUID uuid;
    private String username;
    private List<Card> cards;
    private int role; // 2=Presidente 1=VicePresi 0=Neutro -1=ViceCulo -2=Culo

    public Player(String username) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.cards = new ArrayList<>();
        this.role = 0;
    }

    // Getters & Setters

    public UUID getUuid() {
        return uuid;
    }

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
