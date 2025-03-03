package com.doplerer.ludor.model;

public class Card {

    private Byte id;
    private short value;
    private char family;


    public Card(int id, int value, String family) {
        this.id = (byte) id;
        this.value = (short) value;
        this.family = family.charAt(0);
    }

    // Getters

    public Byte getId() {
        return id;
    }

    public short getValue() {
        return value;
    }

    public char getFamily(){
        return family;
    }

}
