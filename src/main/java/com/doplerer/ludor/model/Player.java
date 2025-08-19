package com.doplerer.ludor.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.UUID;

import java.util.List;

public class Player {
    private String id;
    private String username;
    @JsonIgnore
    private List<Card> cards;
    private int cardCount;
    private int role; // 2=Presidente 1=VicePresi 0=Neutro -1=ViceCulo -2=Culo

    public Player(String username, String id) {
        this.id = id;
        this.username = username;
        this.cards = new ArrayList<>();
        this.role = 0;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void updateCardCount() {
        this.cardCount = cards.size();
    }

    public int getCardCount() {
        return cardCount;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Boolean hasCards(List<Card> cards) {
        for (Card card : cards) {
            if (!this.cards.contains(card)) {
                return false;
            }
        }
        return true;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
