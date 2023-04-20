package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void AddLutemon(View view) {

        Intent intent = new Intent(this, AddNewLutemon.class);
        startActivity(intent);

    }

    public void GoToTraining(View view) {
        Intent intent = new Intent(this, TrainingView.class);
        startActivity(intent);
    }

    public void GoToHome(View view) {
        Intent intent = new Intent(this, HomeViewer.class);
        startActivity(intent);
    }

    public void GoToBattle(View view) {

    }


}