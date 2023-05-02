package com.example.lutemonit;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;


public class FragmentStatics extends Fragment {

    TextView lblAllLut;
    private HashMap<Integer, Lutemon> lut = new HashMap<>();
    private String lblText;

    public FragmentStatics() {
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
        View v = inflater.inflate(R.layout.fragment_statics, container, false);
        lblAllLut = v.findViewById(R.id.lblAllLutemons);

        addText();

        // Inflate the layout for this fragment
        return v;
    }

    public void addText() {
        lut = Storage.getInstance().getLutemons();
        lblText = "Kaikki lutemonit: \n";
        lut.forEach((id, lutemon) -> {
            lblText += "Lutemon " + lutemon.getName() + "(" + lutemon.getColor() + ") on tällä hetkellä ";
            switch (lutemon.getStatement()) {
                case 0:
                    lblText += "kotona. \n";
                    break;
                case 1:
                    lblText += "treenissä. \n";
                    break;
                case 2:
                    lblText += "taistelussa. \n";
                    break;
                default:
                    lblText += "kotona. \n";
                    break;
            }
        });
        lblAllLut.setText(lblText);
    }

    @Override
    public void onResume() {
        super.onResume();
        addText();
    }

}