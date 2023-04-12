package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;
import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class HomeView extends AppCompatActivity {

    Storage storage;
    ArrayList<Lutemon> lutemons;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Create storage if null and get lutemons to storage
        storage = Storage.getInstance();
        lutemons = storage.getLutemons();

        // Link recyclerview to code and start it
        RecyclerView recyclerView = findViewById(R.id.rvLutemonsAtHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeListAdapter adapter = new HomeListAdapter(lutemons);
        recyclerView.setAdapter(adapter);
    }
}