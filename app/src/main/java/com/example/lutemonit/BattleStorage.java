package com.example.lutemonit;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class BattleStorage {

    private static BattleStorage battleStorage = null; // Only one storage can be created
    private HashMap<String, Lutemon> battle = new HashMap<>();
    private ArrayList<String> returnList = new ArrayList<>();
    private Lutemon first, second;

    private BattleStorage () { // Singleton setting, thats why this is private method

    }

    public static BattleStorage getInstance () { //If storage is null, create new one. Return storage
        if(battleStorage == null) {
            battleStorage = new BattleStorage();
        }
        return battleStorage;
    }

    private void clearBattleStorage(){
        battle.clear();;
    }

    public void startNewBattle(Lutemon first, Lutemon second){
        battle.clear();
        this.first = first;
        this.second = second;
    }

    public void addFight(String fight, Lutemon lut) {
        battle.put(fight, lut);

    }

    public ArrayList<String> returnBattle () {
        for (String key: battle.keySet()) {
            returnList.add(key);
        } return returnList;
    }
}
