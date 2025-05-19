package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();

        deck.add(new Card("3O", 0, "3")); // 3 de Oros
        deck.add(new Card("3C", 1, "3")); // 3 de Copas
        deck.add(new Card("3E", 1, "3")); // 3 de Espadas
        deck.add(new Card("3B", 1, "3")); // 3 de Bastos

        deck.add(new Card("4O", 2, "4")); // 4 de Oros
        deck.add(new Card("4C", 2, "4")); // 4 de Copas
        deck.add(new Card("4E", 2, "4")); // 4 de Espadas
        deck.add(new Card("4B", 2, "4")); // 4 de Bastos

        deck.add(new Card("5O", 3, "5")); // 5 de Oros
        deck.add(new Card("5C", 3, "5")); // 5 de Copas
        deck.add(new Card("5E", 3, "5")); // 5 de Espadas
        deck.add(new Card("5B", 3, "5")); // 5 de Bastos

        deck.add(new Card("6O", 4, "6")); // 6 de Oros
        deck.add(new Card("6C", 4, "6")); // 6 de Copas
        deck.add(new Card("6E", 4, "6")); // 6 de Espadas
        deck.add(new Card("6B", 4, "6")); // 6 de Bastos

        deck.add(new Card("7O", 5, "7")); // 7 de Oros
        deck.add(new Card("7C", 5, "7")); // 7 de Copas
        deck.add(new Card("7E", 5, "7")); // 7 de Espadas
        deck.add(new Card("7B", 5, "7")); // 7 de Bastos

        deck.add(new Card("8O", 6, "8")); // 8 de Oros
        deck.add(new Card("8C", 6, "8")); // 8 de Copas
        deck.add(new Card("8E", 6, "8")); // 8 de Espadas
        deck.add(new Card("8B", 6, "8")); // 8 de Bastos

        deck.add(new Card("9O", 7, "9")); // 9 de Oros
        deck.add(new Card("9C", 7, "9")); // 9 de Copas
        deck.add(new Card("9E", 7, "9")); // 9 de Espadas
        deck.add(new Card("9B", 7, "9")); // 9 de Bastos

        deck.add(new Card("10O", 8, "10")); // 10 de Oros
        deck.add(new Card("10C", 8, "10")); // 10 de Copas
        deck.add(new Card("10E", 8, "10")); // 10 de Espadas
        deck.add(new Card("10B", 8, "10")); // 10 de Bastos

        deck.add(new Card("JO", 9, "J")); // J (Sota) de Oros
        deck.add(new Card("JC", 9, "J")); // J (Sota) de Copas
        deck.add(new Card("JE", 9, "J")); // J (Sota) de Espadas
        deck.add(new Card("JB", 9, "J")); // J (Sota) de Bastos

        deck.add(new Card("QO", 10, "Q")); // Q (Caballo) de Oros
        deck.add(new Card("QC", 10, "Q")); // Q (Caballo) de Copas
        deck.add(new Card("QE", 10, "Q")); // Q (Caballo) de Espadas
        deck.add(new Card("QB", 10, "Q")); // Q (Caballo) de Bastos

        deck.add(new Card("KO", 11, "K")); // K (Rey) de Oros
        deck.add(new Card("KC", 11, "K")); // K (Rey) de Copas
        deck.add(new Card("KE", 11, "K")); // K (Rey) de Espadas
        deck.add(new Card("KB", 11, "K")); // K (Rey) de Bastos

        deck.add(new Card("AO", 12, "A")); // A (As) de Oros
        deck.add(new Card("AC", 12, "A")); // A (As) de Copas
        deck.add(new Card("AE", 12, "A")); // A (As) de Espadas
        deck.add(new Card("AB", 12, "A")); // A (As) de Bastos

        deck.add(new Card("2C", 13, "2")); // 2 de Copas
        deck.add(new Card("2E", 13, "2")); // 2 de Espadas
        deck.add(new Card("2B", 13, "2")); // 2 de Bastos

        deck.add(new Card("X", 14, "X")); // Comodín
        deck.add(new Card("X", 14, "X")); // Comodín

        deck.add(new Card("AUTOPLIM", 15, "S")); // Auto Plim

        deck.add(new Card("2O", 16, "2")); // 2 de Oros


    }

    // Getters

    public List<Card> getDeck() {
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}
