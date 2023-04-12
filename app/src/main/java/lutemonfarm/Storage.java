package lutemonfarm;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private static Storage storage = null; // Only one storage can be created
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private ArrayList<Lutemon> returnList = new ArrayList<>();

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
    } // Add new lutemon to list
    
    public ArrayList<Lutemon> getLutemons() {
        lutemons.forEach((key, value) -> { //Go through lutemons hashmap and add lutemons to returnlist
            returnList.add(value);
        });
        return returnList;
    }
}
