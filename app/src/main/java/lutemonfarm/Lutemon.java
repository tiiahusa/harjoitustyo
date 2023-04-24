package lutemonfarm;

import android.os.Build;

import com.example.lutemonit.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Lutemon {

    protected String name, color;
    protected int attack, defense, experience, health, maxHealth, id, wins, losses, statement, pic, trainingdays;
    protected long trainingTime;
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
        this.trainingdays = 0;
        this.trainingTime = 0;
    }

    /*/private void setPicture() {
        switch (color) { // get color by string
            case "Musta": // if it is black, create black lutemon picture
                this.pic = R.drawable.black;
                break;
            case "Vihreä": // if it is green, create green lutemon picture
                this.pic = R.drawable.green;
                break;
            case "Oranssi": // if it is orange, create orange lutemon picture
                this.pic = R.drawable.orange;
                break;
            case "Pinkki": // if it is pink, create pink lutemon picture
                this.pic = R.drawable.pink;
                break;
            case "Valkoinen": // if it is white, create white lutemon picture
                this.pic = R.drawable.white;
                break;

            default:
                this.pic = R.drawable.black;
                break;

        }
    }/*/

    public void setWin() {
        this.experience++;
        this.wins++;
        this.health = this.getMaxHealth();
    }

    public void setLoss() {
        this.experience = 0;
        this.losses++;
        this.health = this.getMaxHealth();
    }

    public void setTrainingDay() {
        this.experience++;
        this.trainingdays++;
        this.health = this.getMaxHealth();
    }

    protected void setStatement(int statement) {
        this.statement = statement;
    }

    protected int getStatement() {
        return statement;
    }

    protected void setTrainingTime() {
        trainingTime = System.currentTimeMillis();
    }


    public long getTrainingTime() {
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
    public int getPic() {
        return pic;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void attack(Lutemon lut, int factor) {
        int a;
        // this lutemons health minus battlefriend lutemons defense
        a = lut.defense((attack+experience) * factor / 100);
        if (a <= health) {
            health -= a;
        } else health = 0;
    }
    private int defense(int value) {
        // This lutemons health minus value, return this lutemons defense
        if (value <= health) {
            health -= value;
        } else health = 0;
        return defense;
    }

    public String getDetails() {
        return name + " (" + color + "): att: " + attack + ", def: " + defense + ", exp: " + experience + ", healt: " + health;
    }

    public boolean checkLutemonFromTrainingArea () {
        long timeNow = System.currentTimeMillis();
            if(timeNow - trainingTime > 60000) {
                return true;
            }
        return false;
    }

}
