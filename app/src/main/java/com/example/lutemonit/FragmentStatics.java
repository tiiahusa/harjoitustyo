package com.example.lutemonit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lutemonfarm.Storage;


public class FragmentStatics extends Fragment {

    Button btnLoadLutemons;
    Context context;

    public FragmentStatics() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = context.getApplicationContext();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statics, container, false);
        btnLoadLutemons = v.findViewById(R.id.btnLoadLutemons);

        btnLoadLutemons.setOnClickListener(view -> {
          //Storage.getInstance().loadLutemons(context);
        });

        // Inflate the layout for this fragment
        return v;
    }

}