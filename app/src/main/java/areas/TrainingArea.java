package areas;

import java.util.ArrayList;
import lutemonfarm.Lutemon;

public class TrainingArea {

    private static TrainingArea area = null; // Only one storage can be created
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private TrainingArea () { // Singleton setting, thats why this is private method

    }

    public static TrainingArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new TrainingArea();
        }
        return area;
    }


}
