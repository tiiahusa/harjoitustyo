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
        if(battle.size() < 2) {
            battle.add(lutemon);
            home.remove(lutemon);
            lutemon.setStatement(2);
            return true;
        } else return false;
    }

    public boolean setLutemonToTraining(Lutemon lutemon) {
        if(training.size() < 3) {
            training.add(lutemon);
            home.remove(lutemon);
            lutemon.setTrainingTime();
            lutemon.setStatement(1);
            return true;
        } else return false;
    }

    public void setLutemonToHome(Lutemon lutemon) {
        if(lutemon.getStatement() == 1) {
            training.remove(lutemon);
        } else if (lutemon.getStatement() == 2) {
            battle.remove(lutemon);
        }
        lutemon.setStatement(0);
        home.add(lutemon);
    }
}
