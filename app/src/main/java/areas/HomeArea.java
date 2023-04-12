package areas;

import java.util.ArrayList;

import lutemonfarm.Lutemon;

public class HomeArea {

    private static HomeArea area = null; // Only one storage can be created
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private HomeArea () { // Singleton setting, thats why this is private method

    }

    public static HomeArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new HomeArea();
        }
        return area;
    }
}
