package com.doplerer.ludor.model;

public class Card {

    private String id;
    private short value;
    private char family;


    public Card(String id, int value, String family) {
        this.id = id;
        this.value = (short) value;
        this.family = family.charAt(0);
    }

    // Getters

    public String getId() {
        return id;
    }

    public short getValue() {
        return value;
    }

    public char getFamily(){
        return family;
    }

}
