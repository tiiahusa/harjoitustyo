package com.example.lutemonit;

public class Lutemon {

    protected String name, color;
    protected int attack, defense, experience, health, maxHealth, id;
    private static int idCounter = 1;

    public Lutemon(String name, String color) {
        this.name = name;
        this.color = color;
        id = getNewId();
    }


    private int getNewId() {
        return idCounter++;
    }


}
