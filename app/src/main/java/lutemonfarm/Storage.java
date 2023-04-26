package lutemonfarm;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private static Storage storage = null; // Only one storage can be created
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private ArrayList<Lutemon> home = new ArrayList<>();
    private ArrayList<Lutemon> training = new ArrayList<>();
    private ArrayList<Lutemon> battle = new ArrayList<>();

    private Storage () { // Singleton setting, thats why this is private method

    }

    public static Storage getInstance () { //If storage is null, create new one. Return storage
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }
    public void AddLutemon(Lutemon lutemon) { //Add new lutemon to HashMap
        lutemons.put(lutemon.getId(), lutemon);
        home.add(lutemon);
    } // Add new lutemon to list
    
    public ArrayList<Lutemon> getLutemonsFromHome() {
        return home;
    }

    public ArrayList<Lutemon> getLutemonsFromTraining() {
        return training;
    }

    public ArrayList<Lutemon> getLutemonsFromBattle() {
        return battle;
    }

    public boolean setLutemonToBattle(Lutemon lutemon) {
        // If battlearea isn't full, add lutemon to battle and remove it in homearea, then return true
        if(battle.size() < 2) {
            battle.add(lutemon);
            home.remove(lutemon);
            lutemon.setStatement(2);
            return true;
        } else return false;// Only return false, don't do nothing else
    }

    public boolean setLutemonToTraining(Lutemon lutemon) {
        // If trainingarea isn't full, add lutemon to training area and remove it in homearea, then return true
        if(training.size() < 3) {
            training.add(lutemon);
            home.remove(lutemon);
            lutemon.setTrainingTime();
            lutemon.setStatement(1);
            return true;
        } else return false; // Only return false, don't do nothing else
    }

    public void setLutemonToHome(Lutemon lutemon) {
        // Check where lutemon is now and remove it there
        if(lutemon.getStatement() == 1) {
            if (lutemon.checkLutemonFromTrainingArea()) {
                lutemon.setTrainingDay();
            }
            training.remove(lutemon);
        } else if (lutemon.getStatement() == 2) {
            battle.remove(lutemon);
        }
        // Set lutemon to homestatement and add it to homelist
        lutemon.setStatement(0);
        home.add(lutemon);
    }


    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }
}
