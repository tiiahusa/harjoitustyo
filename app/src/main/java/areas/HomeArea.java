package areas;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class HomeArea {

    private static HomeArea area = null; // Only one storage can be created
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();

    private HomeArea () { // Singleton setting, thats why this is private method

    }

    public static HomeArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new HomeArea();
        }
        return area;
    }

    public void AddLutemonToHome(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
    }

    public void GetLutemonFromHome (Lutemon lutemon) {
        lutemons.remove(lutemon.getId(), lutemon);
    }


}
