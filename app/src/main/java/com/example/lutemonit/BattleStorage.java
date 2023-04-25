package com.example.lutemonit;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class BattleStorage {

    private static BattleStorage battleStorage = null; // Only one storage can be created
    private ArrayList<ArrayList<String>> battle = new ArrayList<>();
    private ArrayList<String> attack = new ArrayList<>();
    private static int i = 0;
    //private HashMap<String, Lutemon[]> attacks = new HashMap();
    //private ArrayList<Lutemon[]> order = new ArrayList<>();
    //private Lutemon first, second;

    private BattleStorage () { // Singleton setting, thats why this is private method

    }

    public static BattleStorage getInstance () { //If storage is null, create new one. Return storage
        if(battleStorage == null) {
            battleStorage = new BattleStorage();
        }
        return battleStorage;
    }

    public void startNewBattle(){
        battle.clear(); // Clear battle ArrayList
    }


    public void addNewAttack(Lutemon attacker, Lutemon defenser, String attacks) {
        String mapText = i + ": " + attacker.getName() + " (att:" + attacker.getAttack() + ", def:" + attacker.getDefense() + ", health:" + attacker.getHealth() + ") hyökkää ja "
                + defenser.getName() + " (att:" + defenser.getAttack() + ", def:" + defenser.getDefense() + ", health:" + defenser.getHealth() + ") puolustautuu!";
        attack.clear();
        attack.add(mapText);
        attack.add(String.valueOf(attacker.getPic()));
        attack.add(String.valueOf(defenser.getPic()));
        battle.add(attack);
    }

    private int getId() {
        return i++;
    }

    public ArrayList<ArrayList<String>> getAttacks() {
        return battle;
    }

}
