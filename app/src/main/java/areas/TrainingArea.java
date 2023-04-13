package areas;

import android.os.Build;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class TrainingArea {

    private static TrainingArea area = null; // Only one storage can be created
    private HashMap<Lutemon, ZonedDateTime> lutemons = new HashMap<>();

    private TrainingArea () { // Singleton setting, thats why this is private method


    }

    public static TrainingArea getInstance () { //If storage is null, create new one. Return storage
        if(area == null) {
            area = new TrainingArea();
        }
        return area;
    }

    public boolean AddLutemonToTrainingArea (Lutemon lutemon) {
        if(lutemons.size() < 3) { // If Battle area is available
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                lutemons.put(lutemon, ZonedDateTime.now()); // Add lutemon to Battlelist
            }
            HomeArea.getInstance().GetLutemonFromHome(lutemon); // Get lutemon out of Home list
            return true;
            return false;
        }
    }

    public void GetLutemonFromTrainingArea (Lutemon lutemon) {
        lutemons.remove(lutemon);
    }

    public String GetLutemonsArriveTime (Lutemon lutemon) {
        return lutemons.get(lutemon).toString();
    }


}
