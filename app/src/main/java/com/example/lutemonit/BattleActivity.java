package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class BattleActivity extends AppCompatActivity {

    RadioGroup rgFirst, rgSecond;
    TextView lblFirstLutemon, lblSecondLutemon, lblPrepare, lblChoose;
    ImageView imgFirstToHome, imgSecondToHome;
    Button btnStart;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private ArrayList<Lutemon[]> battleList = new ArrayList<>();
    Storage storage;
    Context context;
    private static Integer[] baseFactor = {0, 25, 50, 75, 100};
    private int percentA, percentB;
    Lutemon first, second;
    String mapText;
    private RecyclerView recyclerView;
    BattleAdapter adapter;
    BattleStorage battleStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        context = BattleActivity.this;

        //Get Lutemons from storage
        storage = Storage.getInstance();
        lutemons = storage.getLutemonsFromBattle();
        battleStorage = BattleStorage.getInstance();

        // Link view-things to code
        rgFirst = findViewById(R.id.rgFirstLutemon);
        rgSecond = findViewById(R.id.rgSecLutemon);
        lblFirstLutemon = findViewById(R.id.lblFirstBattleLutemon);
        lblSecondLutemon = findViewById(R.id.lblSecBattleLutemon);
        lblPrepare = findViewById(R.id.lblPrepare);
        lblChoose = findViewById(R.id.lblChoose);
        imgFirstToHome = findViewById(R.id.imgFirstLutemonToHome);
        imgSecondToHome = findViewById(R.id.imgSecLutemonToHome);
        btnStart = findViewById(R.id.btnStartBattle);

        // Link recyclerview to code and start it
        recyclerView = findViewById(R.id.rvBattleStart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BattleAdapter(battleStorage.getAttacks());
        recyclerView.setAdapter(adapter);

        // Set visibilities
        SetFirstThings(false); // First turn off items
        SetSecondThings(false);
        SetBattleThings(lutemons.size()==2); // Set BattleStart visibilities
        SetLutemons(); // Set names to textviews and visibilities
        SetBattleView(false);

        imgFirstToHome.setOnClickListener(view -> {
            SendToHome(0); // If home-button clicked
        });
        imgSecondToHome.setOnClickListener(view -> {
            SendToHome(1); // If home-button clicked
        });
        btnStart.setOnClickListener(view -> {
            StartBattle(); // When Start-button clicked
        });


    }

    private void SetFirstThings(boolean state) { // First lutemon items visibility
        if(state) {
            lblFirstLutemon.setVisibility(View.VISIBLE); // Set it visible
            imgFirstToHome.setVisibility(View.VISIBLE); // Show go to home -button
        } else {
            lblFirstLutemon.setVisibility(View.GONE); // Set invisible
            imgFirstToHome.setVisibility(View.GONE); // Hide go to home button
        }
    }

    private void SetSecondThings(boolean state) { //Second lutemon items visibility
        if(state) {
            lblSecondLutemon.setVisibility(View.VISIBLE); // Set it visible
            imgSecondToHome.setVisibility(View.VISIBLE); // Show go to home button
            lblPrepare.setVisibility(View.VISIBLE); // Show "VS" text
        } else {
            lblSecondLutemon.setVisibility(View.GONE); // Set it invisible
            imgSecondToHome.setVisibility(View.GONE); // Hide go to home button
            lblPrepare.setVisibility(View.GONE); // Hide "VS" text
        }
    }
    private void SetBattleThings(boolean state) { // Battle prepare items visibility
        if(state) { // If BattleArea is full show battlesettings
            lblChoose.setVisibility(View.VISIBLE);
            rgFirst.setVisibility(View.VISIBLE);
            rgSecond.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
        } else { // if battle area is not full, don't show battlesettings
            lblChoose.setVisibility(View.GONE);
            rgFirst.setVisibility(View.GONE);
            rgSecond.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
        }
    }

    private void SetLutemons() {
        // Set Lutemon's names and homeimages
        if(lutemons.size() > 0) { // If lutemons has one object
            lblFirstLutemon.setText(lutemons.get(0).getName()); // Set first name to label
            SetFirstThings(true); // Set first lutemon name + homebutton visible
            if(lutemons.size() > 1) { // If lutemons has two object
                lblSecondLutemon.setText(lutemons.get(1).getName()); // Set second name to label
                SetSecondThings(true);  // Set second lutemon name + homebutton visible
            } else {
                lblSecondLutemon.setText(""); // Clear second name label
                SetSecondThings(false); // Set second lutemon name + homebutton invisible
            }
        } else {
            lblFirstLutemon.setText(""); // Clear first name label
            SetFirstThings(false);// Set first lutemon name + homebutton invisible
        }
    }

    private void SendToHome(int i) {
        Lutemon lut = lutemons.get(i); // Get lutemon object
        storage.setLutemonToHome(lut); // Send it to home
        lutemons = storage.getLutemonsFromBattle(); // Refresh lutemons-list
        SetLutemons(); // Set battlelutemons for page top
        SetBattleThings(lutemons.size()==2); // Check Battle Settings
    }

    private void battleEnd(Lutemon winner, Lutemon loser) {
        // Add staticpoints and return lutemons to home
        loser.setLoss();
        winner.setWin();
        storage.setLutemonToHome(winner);
        storage.setLutemonToHome(loser);
        winner.setWin(); // Set one experiencepoint
        loser.setLoss(); // set experiencepoints to zero
    }

    private void SetBattleView(boolean state) {
        if(state) {
            recyclerView.setVisibility(View.VISIBLE);
        } else recyclerView.setVisibility(View.GONE);

    }

    private int RandomGenerator(int i) {
        Random random = new Random(); // Create random generator
        return random.nextInt(i); // return random number from factor list
    }

    public void StartBattle() {
        // Create few "jäsenmuuttuja"
        int firstrg;
        int secrg;

        // Check first radiogroup
        switch (rgFirst.getCheckedRadioButtonId()) {
            case R.id.rbOneF:
                firstrg = 0;
                break;
            case R.id.rbTwoF:
                firstrg = 1;
                break;
            case R.id.rbThreeF:
                firstrg = 2;
                break;
            case R.id.rbFourF:
                firstrg = 3;
                break;
            case R.id.rbFiveF:
                firstrg = 4;
                break;
            default:
                Toast.makeText(context, "Valitse taistelukerroin Lutemonille 1!", Toast.LENGTH_LONG).show();
                return; // If Radiogroup not checked, stop method and toast for user
        }

        // Check second radiogroup
        switch (rgSecond.getCheckedRadioButtonId()) {
            case R.id.rbOneS:
                secrg = 0;
                break;
            case R.id.rbTwoS:
                secrg = 1;
                break;
            case R.id.rbThreeS:
                secrg = 2;
                break;
            case R.id.rbFourS:
                secrg = 3;
                break;
            case R.id.rbFiveS:
                secrg = 4;
                break;
            default:
                Toast.makeText(context, "Valitse taistelukerroin Lutemonille 2!", Toast.LENGTH_LONG).show();
                return; // If Radiogroup not checked, stop method and toast for user
        }

        battleStorage.startNewBattle(first, second);
        adapter.updateData(battleStorage.getAttacks());

        // Set visibilities on / off
        SetBattleThings(false);
        SetFirstThings(false);
        SetSecondThings(false);
        SetBattleView(true);

        // Set random attack factor
        percentA = baseFactor[RandomGenerator(5)];
        percentB = baseFactor[RandomGenerator(5)];

        System.out.println("Ekan kerroin " + percentA);
        System.out.println("Tokan kerroin " + percentB);

        // Lutemons order is random, generator allot which one is first and witch second
        int lutemonSequence = RandomGenerator(2);
        first = lutemons.remove(lutemonSequence);
        second = lutemons.remove(0);

        itsBattleTime();

        System.out.println("Taistelu loppui!!");

        if(first.getHealth() <= 0 && second.getHealth() > 0) {
            System.out.println("Toka voitti");
            battleEnd(second, first);
        } else if(first.getHealth() > 0 && second.getHealth() <= 0) {
            System.out.println("Eka voitti");
            battleEnd(first, second);
        } else {
            System.out.println("Ekan elämät " + first.getHealth() + " ja tokan elämät " + second.getHealth());
        }
    }

    public void itsBattleTime() {
        int i = 1;

        // Start battle!! While goes until first or second lutemon health is zero
        while(first.getHealth() > 0 && second.getHealth() > 0) {

            first.attack(second, percentA); // first lutemon attack to second one
            mapText = i + ": " + first.getName() + " (att:" + first.getAttack() + ", def:" + first.getDefense() + ", health:" + first.getHealth() + ") hyökkää ja "
                    + second.getName() + " (att:" + second.getAttack() + ", def:" + second.getDefense() + ", health:" + second.getHealth() + ") puolustautuu!";
            battleStorage.addFight(first, second);
            battleStorage.addNewAttack(first, second, mapText);
            System.out.println(mapText);
            adapter.updateData(battleStorage.getAttacks());
            i++;

            second.attack(first, percentB);
            mapText = i + ": " + second.getName() + " (att:" + second.getAttack() + ", def:" + second.getDefense() + ", health:" + second.getHealth() + ") hyökkää ja "
                    + first.getName() + " (att:" + first.getAttack() + ", def:" + first.getDefense() + ", health:" + first.getHealth() + ") puolustautuu!";
            battleStorage.addFight(second, first);
            battleStorage.addNewAttack(second, first, mapText);
            System.out.println(mapText);
            adapter.updateData(battleStorage.getAttacks());
            i++;
            /*/try {
                Thread.sleep(3000);
            }  catch (InterruptedException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }/*/

        }
    }






}