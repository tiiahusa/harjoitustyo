package areas;

import java.util.ArrayList;

import lutemonfarm.Lutemon;

public class BattleArea {

    private static BattleArea area = null; // Only one storage can be created
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private BattleArea () { // Singleton setting, thats why this is private method

    }

    public static BattleArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new BattleArea();
        }
        return area;
    }
}
