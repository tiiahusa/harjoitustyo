package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    TextView lblFirstLutemon, lblSecondLutemon, lblPrepare, lblChoose, lblWholeBattle;
    ImageView imgFirstToHome, imgSecondToHome, imgAttLut, imgDefLut, imgSword, imgWinner;
    Button btnStart;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private ArrayList<Lutemon[]> battleList = new ArrayList<>();
    Storage storage;
    Context context;
    private static Integer[] baseFactor = {0, 25, 50, 75, 100};
    private int percentA, percentB;
    Lutemon first, second;
    String mapText, fightText;
    //private RecyclerView recyclerView;
    BattleAdapter adapter;
    BattleStorage battleStorage;
    CountDownTimer timer;
    boolean timerrun;
    int round = 0;
    private long timeLeft = 600000;

    @SuppressLint("MissingInflatedId")
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
        lblWholeBattle = findViewById(R.id.lblWholeBattle);
        imgAttLut = findViewById(R.id.imgFirstLut);
        imgDefLut = findViewById(R.id.imgSecLut);
        imgSword = findViewById(R.id.imgSword);
        imgWinner = findViewById(R.id.imgWinner);

        // Link recyclerview to code and start it
        //recyclerView = findViewById(R.id.rvBattleStart);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new BattleAdapter(battleStorage.getAttacks());
        //recyclerView.setAdapter(adapter);

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
            startBattle(); // When Start-button clicked
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
    }

    private void SetBattleView(boolean state) {
        if(state) {
            lblWholeBattle.setVisibility(View.VISIBLE); // Set battle-textview visible
            imgAttLut.setVisibility(View.VISIBLE); // Show AttackLut image
            imgSword.setVisibility(View.VISIBLE); // Show Sword
            imgDefLut.setVisibility(View.VISIBLE); // Show DefenseLut image
        } else {
            lblWholeBattle.setVisibility(View.GONE); // Set battle-textview invisible
            imgAttLut.setVisibility(View.GONE); // Hide AttackLut image
            imgSword.setVisibility(View.GONE); // Hide Sword
            imgDefLut.setVisibility(View.GONE); // Hide DefenseLut image
            imgWinner.setVisibility(View.GONE); // Hide Winner picture
        }
    }

    private int randomGenerator(int i) {
        Random random = new Random(); // Create random generator
        return random.nextInt(i); // return random number from factor list
    }

    public void startBattle() {
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

        battleStorage.startNewBattle(); // Start Battle and clear recent battle from battlestorage

        // Set visibilities on / off
        SetBattleThings(false);
        SetFirstThings(false);
        SetSecondThings(false);
        SetBattleView(true);

        // Clear battle -textbox
        lblWholeBattle.setText("");
        // Clear figthText
        fightText = "";

        // Set random attack factor
        percentA = baseFactor[randomGenerator(5)];
        percentB = baseFactor[randomGenerator(5)];
        System.out.println("Ekan kerroin " + percentA);
        System.out.println("Tokan kerroin " + percentB);

        while(percentB+percentA == 0) {
            // New try if both percents are zero
            percentA = baseFactor[randomGenerator(5)];
            percentB = baseFactor[randomGenerator(5)];
        }

        System.out.println("Ekan kerroin " + percentA);
        System.out.println("Tokan kerroin " + percentB);

        // Lutemons order is random, generator allot which one is first and witch second
        int lutemonSequence = randomGenerator(2);
        first = lutemons.remove(lutemonSequence);
        second = lutemons.remove(0);

        System.out.println("Eka lutmeoni on " + first.getName());
        System.out.println("Toka lutemoni on " + second.getName());

        itsBattleTime();

    }

    public void battleFinal () {

        fightText += "\n\n Taistelu on päättynyt!! \n\n";
        lblWholeBattle.setText(fightText);
        imgSword.setVisibility(View.GONE);

        // If second one wins, add info to battletext, show second picture and winnerpicture, hide first picture and sworepicture
        // Add statisc
        if(first.getHealth() <= 0 && second.getHealth() > 0) {
            mapText = "\n" + second.getName() + " voitti tämän taistelun!";
            fightText += mapText;
            lblWholeBattle.setText(fightText);
            imgAttLut.setImageResource(second.getPic());
            imgDefLut.setVisibility(View.GONE);
            battleEnd(second, first);
            imgWinner.setVisibility(View.VISIBLE); // Show Winner picture
        } else if(first.getHealth() > 0 && second.getHealth() <= 0) {
            mapText = "\n" + first.getName() + " voitti tämän taistelun!";
            fightText += mapText;
            lblWholeBattle.setText(fightText);
            imgAttLut.setImageResource(first.getPic());
            imgDefLut.setVisibility(View.GONE);
            battleEnd(first, second);
            imgWinner.setVisibility(View.VISIBLE); // Show Winner picture
        } else {
            mapText = "\n Tämä oli tasapeli!!";
            fightText +=  mapText;
            lblWholeBattle.setText(fightText);
            imgAttLut.setImageResource(first.getPic());
            imgDefLut.setImageResource(second.getPic());
        }

    }

    public void itsBattleTime() {
        round = 0;

        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                if(round % 2 == 0) {
                    firstAttack();
                } else secondAttack();
            }
            @Override
            public void onFinish() {
            }
        }.start();
        timerrun = true;
    }

    public void stopTimer() {
        timer.cancel();
        timeLeft = 600000;
        timerrun = false;
        battleFinal();
    }
    public void firstAttack()
    {
        // End Battle when first or second is dead
        if (first.getHealth() <= 0 || second.getHealth() <= 0) {
            stopTimer();
            return;
        }
        round += 1;
        first.attack(second, percentA, percentB); // first lutemon attack to second one
        mapText = round + ") " + first.getName() + " (att:" + first.getAttack() + ", def:" + first.getDefense() + ", health:" + first.getHealth() + ") hyökkää ja "
                + second.getName() + " (att:" + second.getAttack() + ", def:" + second.getDefense() + ", health:" + second.getHealth() + ") puolustautuu!";
        fightText += mapText;
        fightText += "\n";
        lblWholeBattle.setText(fightText);
        imgAttLut.setImageResource(first.getPic());
        imgDefLut.setImageResource(second.getPic());
    }

    public void secondAttack()
    {
        // End Battle when first or second is dead
        if (first.getHealth() <= 0 || second.getHealth() <= 0) {
            stopTimer();
            return;
        }
        round += 1;
        second.attack(first, percentB, percentA); // first lutemon attack to second one
        mapText = round + ") " + second.getName() + " (att:" +second.getAttack() + ", def:" + second.getDefense() + ", health:" + second.getHealth() + ") hyökkää ja "
                + first.getName() + " (att:" + first.getAttack() + ", def:" + first.getDefense() + ", health:" + first.getHealth() + ") puolustautuu!";
        fightText += mapText;
        fightText += "\n";
        lblWholeBattle.setText(fightText);
        imgDefLut.setImageResource(first.getPic());
        imgAttLut.setImageResource(second.getPic());
    }







}