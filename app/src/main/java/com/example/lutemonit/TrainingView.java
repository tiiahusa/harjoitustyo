package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class TrainingView extends AppCompatActivity {

    private Storage storage;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //Create storage if null and get lutemons to storage
        storage = Storage.getInstance();
        lutemons = storage.getLutemons();

        // Link recyclerview to code and start it
        RecyclerView recyclerView = findViewById(R.id.rvLutemonsAtTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TrainingListAdapter adapter = new TrainingListAdapter(lutemons);
        recyclerView.setAdapter(adapter);
    }
}