package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class TrainingView extends AppCompatActivity {

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    Storage storage;
    private TrainingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //Create storage if null
        storage = Storage.getInstance();
        // get lutemons to storage
        lutemons = storage.getLutemonsFromTraining();

        // Link recyclerview to code and start it
        RecyclerView recyclerView = findViewById(R.id.rvLutemonsAtTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrainingListAdapter(lutemons);
        recyclerView.setAdapter(adapter);
    }


}