package areas;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class BattleArea {

    private static BattleArea area = null; // Only one storage can be created
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();

    private BattleArea () { // Singleton setting, thats why this is private method

    }

    public static BattleArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new BattleArea();
        }
        return area;
    }

    public boolean AddLutemonToBattleArea (Lutemon lutemon) {
        if(lutemons.size() < 2) { // If Battle area is available
            lutemons.put(lutemon.getId(), lutemon); // Add lutemon to Battlelist
            HomeArea.getInstance().GetLutemonFromHome(lutemon); // Get lutemon out of Home list
            return true;
        } else {
            return false;
        }
    }
}
