package lutemonfarm;

import android.os.Build;

import java.time.LocalDateTime;

public class Lutemon {

    protected String name, color;
    protected int attack, defense, experience, health, maxHealth, id, wins, losses, statement;
    protected LocalDateTime trainingTime;
    private static int idCounter = 1;

    public Lutemon(String name, String color, int attack, int defense, int experience, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.wins = 0;
        this.losses = 0;
        id = getNewId();
        this.statement = 0;
    }

    public void setWin() {
        this.wins++;
    }

    public void setLoss() {
        this.losses++;
    }

    public void setStatement(int statement) {
        this.statement = statement;
    }

    public int getStatement() {
        return statement;
    }

    public void setTrainingTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            trainingTime = LocalDateTime.now();
        }
    }

    public LocalDateTime getTrainingTime() {
        return trainingTime;
    }

    private int getNewId() { // Give lutemons id and grow for one every time
        return idCounter++;
    }

    public int getId() { // Get lutemons id
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }


}
