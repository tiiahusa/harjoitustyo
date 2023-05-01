package com.example.lutemonit;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class FragmentFacts extends Fragment {

    TextView lblWins, lblLosses, lblStatic;
    Storage storage;
    ArrayList<Lutemon> mostWins = new ArrayList<>();
    ArrayList<Lutemon> mostLosses = new ArrayList<>();
    private String staticText, startText, winText, lossText;
    private int i, maxWin, maxLoss;
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();

    public FragmentFacts() {
        // Required empty public constructor
    }

    public static FragmentFacts newInstance(String param1, String param2) {
        FragmentFacts fragment = new FragmentFacts();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_facts, container, false);

        // Link code to layout labels
        lblWins = v.findViewById(R.id.lblWins);
        lblLosses = v.findViewById(R.id.lblLosses);
        lblStatic = v.findViewById(R.id.lblFacts);

        storage = Storage.getInstance();

        checkStatics(); // Check lutemons statics
        updateScreen(); // Update layout labels

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkStatics(); // Check lutemons statics
        updateScreen(); // Update layout labels
    }

    private void checkStatics() {

        // Get lutemons from storage and clear all "apumuuttujat"
        lutemons = storage.getLutemons();
        mostWins.clear();
        mostLosses.clear();
        staticText = "";
        maxLoss = 0;
        maxWin = 0;
        i = 0;

        // Went throw lutemons HashMap
        lutemons.forEach((key, lut) -> {
            // Update most wins -lutemon if needed and also most loss -lutemon
            if (maxWin == lut.getWins()) {
                mostWins.add(lut); }
            if(maxWin < lut.getWins()) {
                maxWin = lut.getWins();
                mostWins.clear();
                mostWins.add(lut);
            } if (maxLoss == lut.getLosses()) {
                    mostLosses.add(lut);
                }
            if(maxLoss < lut.getLosses()) {
                maxLoss = lut.getLosses();
                mostLosses.clear();
                mostLosses.add(lut);
            }
            // Add lutemon and his statics to statictext - string
            staticText += lut.getName() + " on voittanut " + lut.getWins() + " kertaa ja hävinnyt " + lut.getLosses() + " kertaa\n";
            i += lut.getWins();
            i += lut.getLosses();
        });

    }

    private void updateScreen() {
        startText = "Taisteluita on käyty yhteensä " + i/2 + " kappaletta:\n";

        if(mostWins.size() > 1) { // If deadheat, list all maxwins lutemons
            winText = "Eniten voittoja on lutemoneilla: ";
            mostWins.forEach((lut) -> winText += lut.getName()+", ");
            winText = winText.substring(0, winText.length()-2);
        }
        if (mostWins.size() == 1) {
            winText = "Eniten voittoja on lutemonilla " + mostWins.get(0).getName();
        }
        if (mostLosses.size() > 1) { // If deadheat, list all maxwloss lutemons
            lossText = "Eniten häviöitä on lutemoneilla: ";
            mostLosses.forEach((lut) -> lossText += lut.getName()+", ");
            lossText = lossText.substring(0, lossText.length()-2);
        }
        if (mostLosses.size() == 1) {
            lossText = "Eniten häviöitä on lutemonilla " + mostLosses.get(0).getName();
        }
        if(i == 0) {
            winText = "Taisteluita ei ole käyty, joten tilastoja ei ole saatavilla";
            lossText = "";
        }
        lblWins.setText(winText);
        lblLosses.setText(lossText);
        lblStatic.setText(startText + staticText);
    }
}