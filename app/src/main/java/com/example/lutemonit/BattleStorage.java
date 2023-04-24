package com.example.lutemonit;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class BattleStorage {

    private static BattleStorage battleStorage = null; // Only one storage can be created
    private ArrayList<ArrayList<String>> battle = new ArrayList<>();
    private ArrayList<String> attack = new ArrayList<>();
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

    public void startNewBattle(Lutemon first, Lutemon second){
        battle.clear(); // Clear battle HashMap
        //this.first = first;
        //this.second = second;
    }

    public void addFight(Lutemon attackLut, Lutemon defenseLut) {
        //Lutemon[] fight = {attackLut, defenseLut};
        //order.add(fight);
    }

    public void addNewAttack(Lutemon attacker, Lutemon defenser, String attacks) {
        attack.clear();
        attack.add(attacks);
        attack.add(String.valueOf(attacker.getPic()));
        attack.add(String.valueOf(defenser.getPic()));
        battle.add(attack);
    }

    public ArrayList<ArrayList<String>> getAttacks() {
        return battle;
    }

    public ArrayList<Lutemon[]> getBattle () {
        ArrayList<Lutemon[]> returnlist = new ArrayList<>();
        for(int i = order.size()-1; i > 0; i--) {
            returnlist.add(order.get(i));
        }
        System.out.println(returnlist);
        return returnlist;
    }

}
