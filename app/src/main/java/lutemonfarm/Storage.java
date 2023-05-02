package lutemonfarm;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.example.lutemonit.MainActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    final private String FILENAME;
    private static Storage storage = null; // Only one storage can be created
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private ArrayList<Lutemon> home = new ArrayList<>();
    private ArrayList<Lutemon> training = new ArrayList<>();
    private ArrayList<Lutemon> battle = new ArrayList<>();
    Context context;

    private Storage () { // Singleton setting, thats why this is private method
        FILENAME = "lutemons.data";
        context = MainActivity.getAppContext();
    }

    public static Storage getInstance () { //If storage is null, create new one. Return storage
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }
    public void AddLutemon(Lutemon lutemon) { //Add new lutemon to HashMap
        lutemons.put(lutemon.getId(), lutemon);
        home.add(lutemon);
        //System.out.println(lutemons);
        saveLutemons(context);
    } // Add new lutemon to list
    
    public ArrayList<Lutemon> getLutemonsFromHome() {
        return home;
    }

    public ArrayList<Lutemon> getLutemonsFromTraining() {
        return training;
    }

    public ArrayList<Lutemon> getLutemonsFromBattle() {
        return battle;
    }

    public boolean setLutemonToBattle(Lutemon lutemon) {
        // If battlearea isn't full, add lutemon to battle and remove it in homearea, then return true
        if(battle.size() < 2) {
            battle.add(lutemon);
            home.remove(lutemon);
            lutemon.setStatement(2);
            return true;
        } else return false;// Only return false, don't do nothing else
    }

    public boolean setLutemonToTraining(Lutemon lutemon) {
        // If trainingarea isn't full, add lutemon to training area and remove it in homearea, then return true
        if(training.size() < 3) {
            training.add(lutemon);
            home.remove(lutemon);
            lutemon.setTrainingTime();
            lutemon.setStatement(1);
            return true;
        } else return false; // Only return false, don't do nothing else
    }

    public void setLutemonToHome(Lutemon lutemon) {
        // Check where lutemon is now and remove it there
        if(lutemon.getStatement() == 1) {
            if (lutemon.checkLutemonFromTrainingArea()) {
                lutemon.setTrainingDay();
            }
            training.remove(lutemon);
        } else if (lutemon.getStatement() == 2) {
            battle.remove(lutemon);
        }
        // Set lutemon to homestatement and add it to homelist
        lutemon.setStatement(0);
        home.add(lutemon);
    }

    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    public void saveLutemons(Context context) {
        // try write user to file
        try {
            // create object output stream name users.data
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            // Write users-list there
            userWriter.writeObject(lutemons);
            // close file
            userWriter.close();
            Toast.makeText(context, "Lutemoni tallennettu", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // If writing to file not success
            Toast.makeText(context, "Lutemonin tallentaminen ei onnistunut" + e, Toast.LENGTH_LONG).show();
        }

        //System.out.println(lutemons);
    }

    public void loadLutemons() {
        // try write user to file
        try {
            // create object input stream name users.data
            ObjectInputStream lutReader = new ObjectInputStream(context.openFileInput(FILENAME));
            // Read file to Arraylist name users
            lutemons = (HashMap<Integer, Lutemon>) lutReader.readObject();
            // close file
            lutReader.close();
            Toast.makeText(context, "Lutemonit ladattu", Toast.LENGTH_LONG).show();

            // Set lutemons to areas
            battle.clear();
            training.clear();
            home.clear();
            lutemons.forEach((id, lut) -> {
                switch (lut.getStatement()) {
                    case 0:
                        home.add(lut);
                        break;
                    case 1:
                        training.add(lut);
                        break;
                    case 2:
                        battle.add(lut);
                        break;
                    default:
                        home.add(lut);
                        lut.setStatement(0);
                        break;
                }
            });

        } catch (FileNotFoundException e) { // If file not found
            Toast.makeText(context, "Lutemonilistaa ei löytynyt!", Toast.LENGTH_LONG).show();
            //System.out.println("Lutemonien lataaminen ei onnistunut!");
            e.printStackTrace();
        } catch (IOException e) { // IO problem
            Toast.makeText(context, "Lutemonien lataaminen ei onnistunut!", Toast.LENGTH_LONG).show();
            //System.out.println("Lutemonien lataaminen ei onnistunut!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) { // Class problem
            Toast.makeText(context, "Lutemonien lataaminen ei onnistunut!", Toast.LENGTH_LONG).show();
            //System.out.println("Lutemonien lataaminen ei onnistunut!");
            e.printStackTrace();
        }

    }
}
