package com.doplerer.ludor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();

        deck.add(new Card(0, 0, "3")); // 3 de Oros
        deck.add(new Card(1, 1, "3")); // 3 de Copas
        deck.add(new Card(2, 1, "3")); // 3 de Espadas
        deck.add(new Card(3, 1, "3")); // 3 de Bastos

        deck.add(new Card(4, 2, "4")); // 4 de Oros
        deck.add(new Card(5, 2, "4")); // 4 de Copas
        deck.add(new Card(6, 2, "4")); // 4 de Espadas
        deck.add(new Card(7, 2, "4")); // 4 de Bastos

        deck.add(new Card(8, 3, "5")); // 5 de Oros
        deck.add(new Card(9, 3, "5")); // 5 de Copas
        deck.add(new Card(10, 3, "5")); // 5 de Espadas
        deck.add(new Card(11, 3, "5")); // 5 de Bastos

        deck.add(new Card(12, 4, "6")); // 6 de Oros
        deck.add(new Card(13, 4, "6")); // 6 de Copas
        deck.add(new Card(14, 4, "6")); // 6 de Espadas
        deck.add(new Card(15, 4, "6")); // 6 de Bastos

        deck.add(new Card(16, 5, "7")); // 7 de Oros
        deck.add(new Card(17, 5, "7")); // 7 de Copas
        deck.add(new Card(18, 5, "7")); // 7 de Espadas
        deck.add(new Card(19, 5, "7")); // 7 de Bastos

        deck.add(new Card(20, 6, "8")); // 8 de Oros
        deck.add(new Card(21, 6, "8")); // 8 de Copas
        deck.add(new Card(22, 6, "8")); // 8 de Espadas
        deck.add(new Card(23, 6, "8")); // 8 de Bastos

        deck.add(new Card(24, 7, "9")); // 9 de Oros
        deck.add(new Card(25, 7, "9")); // 9 de Copas
        deck.add(new Card(26, 7, "9")); // 9 de Espadas
        deck.add(new Card(27, 7, "9")); // 9 de Bastos

        deck.add(new Card(28, 8, "10")); // 10 de Oros
        deck.add(new Card(29, 8, "10")); // 10 de Copas
        deck.add(new Card(30, 8, "10")); // 10 de Espadas
        deck.add(new Card(31, 8, "10")); // 10 de Bastos

        deck.add(new Card(32, 9, "J")); // J (Sota) de Oros
        deck.add(new Card(33, 9, "J")); // J (Sota) de Copas
        deck.add(new Card(34, 9, "J")); // J (Sota) de Espadas
        deck.add(new Card(35, 9, "J")); // J (Sota) de Bastos

        deck.add(new Card(36, 10, "Q")); // Q (Caballo) de Oros
        deck.add(new Card(37, 10, "Q")); // Q (Caballo) de Copas
        deck.add(new Card(38, 10, "Q")); // Q (Caballo) de Espadas
        deck.add(new Card(39, 10, "Q")); // Q (Caballo) de Bastos

        deck.add(new Card(40, 11, "K")); // K (Rey) de Oros
        deck.add(new Card(41, 11, "K")); // K (Rey) de Copas
        deck.add(new Card(42, 11, "K")); // K (Rey) de Espadas
        deck.add(new Card(43, 11, "K")); // K (Rey) de Bastos

        deck.add(new Card(44, 12, "A")); // A (As) de Oros
        deck.add(new Card(45, 12, "A")); // A (As) de Copas
        deck.add(new Card(46, 12, "A")); // A (As) de Espadas
        deck.add(new Card(47, 12, "A")); // A (As) de Bastos

        deck.add(new Card(48, 13, "2")); // 2 de Copas
        deck.add(new Card(49, 13, "2")); // 2 de Espadas
        deck.add(new Card(50, 13, "2")); // 2 de Bastos

        deck.add(new Card(51, 14, "X")); // Comodín
        deck.add(new Card(52, 14, "X")); // Comodín

        deck.add(new Card(53, 15, "S")); // Auto Plim

        deck.add(new Card(54, 16, "2")); // 2 de Oros

    }

    // Getters

    public List<Card> getDeck() {
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}
