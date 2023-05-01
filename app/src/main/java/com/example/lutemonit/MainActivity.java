package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import lutemonfarm.Storage;

public class MainActivity extends AppCompatActivity {

    Button btnHome, btnTraining, btnBattle;
    Storage storage;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        // Link Buttons to code
        btnBattle = findViewById(R.id.btnBattle);
        btnHome = findViewById(R.id.btnHome);
        btnTraining = findViewById(R.id.btnTraining);

        // Tässä välissä lataa Lutemonit tiedostosta


        // Refresh button's text
        storage = Storage.getInstance();
        btnBattle.setText("Taistelutanner (" + storage.getLutemonsFromBattle().size() + ")");
        btnHome.setText("Lutemonikoti (" + storage.getLutemonsFromHome().size() + ")");
        btnTraining.setText("Treenikämppä (" + storage.getLutemonsFromTraining().size() + ")");


    }

    @Override // Refresh buttons text
    protected void onPostResume() {
        super.onPostResume();
        btnBattle.setText("Taistelutanner (" + storage.getLutemonsFromBattle().size() + ")");
        btnHome.setText("Lutemonikoti (" + storage.getLutemonsFromHome().size() + ")");
        btnTraining.setText("Treenikämppä (" + storage.getLutemonsFromTraining().size() + ")");
    }

    public void AddLutemon(View view) {
        // Go to AddLutemon -page
        Intent intent = new Intent(this, AddNewLutemon.class);
        startActivity(intent);

    }

    public void GoToTraining(View view) {
        // GO to Training page
        Intent intent = new Intent(this, TrainingView.class);
        startActivity(intent);
    }


    public void GoToHome(View view) {
        // Go to home-page
        Intent intent = new Intent(this, HomeViewer.class);
        startActivity(intent);
    }

    public void GoToBattle(View view) {
        // Go to Battle -page
        Intent intent = new Intent(this, BattleActivity.class);
        startActivity(intent);
    }


}