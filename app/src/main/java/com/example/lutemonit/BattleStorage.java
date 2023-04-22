package com.example.lutemonit;

import java.util.ArrayList;

public class BattleStorage {

    private static BattleStorage battleStorage = null; // Only one storage can be created
    private ArrayList<String> battle = new ArrayList<>();

    private BattleStorage () { // Singleton setting, thats why this is private method

    }

    public static BattleStorage getInstance () { //If storage is null, create new one. Return storage
        if(battleStorage == null) {
            battleStorage = new BattleStorage();
        }
        return battleStorage;
    }

    public void clearBattleStorage(){
        battle.clear();
    }

    public void addFight(String fight) {
        battle.add(fight);
    }

    public ArrayList<String> returnBattle () {
        return battle;
    }
}
