package com.example.lutemonit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBottom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBottom extends Fragment {


    View v;
    TextView battle, training;
    ArrayList<Lutemon> lutBattle = new ArrayList<>();
    ArrayList<Lutemon> lutTrain = new ArrayList<>();

    public FragmentBottom() {
        // Required empty public constructor
    }
    public static FragmentBottom newInstance(String param1, String param2) {
        FragmentBottom fragment = new FragmentBottom();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bottom, container, false);

        //Link code and labels
        battle = v.findViewById(R.id.lblBattleLutemons);
        training = v.findViewById(R.id.lblTrainLutemons);

        //Get battle and training -lists
        lutBattle = Storage.getInstance().getLutemonsFromBattle();
        lutTrain = Storage.getInstance().getLutemonsFromTraining();

        // Get lutemons from battle list and add their names to string bat
        String bat = "";
        for (Lutemon lut: lutBattle) {
            if (bat.length() > 0) {
                bat += ", ";
            }
            bat += lut.getName();
        }
        // Get lutemons from training list and add their names to string tra
        String tra = "";
        for (Lutemon lut: lutTrain) {
            if (tra.length() > 0) { // Lisätään pilkku tuotteiden väliin
                tra += ", ";
            }
            tra += lut.getName();
        }
        // Add texts to layout labels
        if(bat.length() == 0) {
            battle.setText("Ei yhtään lutemonia taistelukentällä");
        } else battle.setText("Taisteluareenalla: " + bat);
        if(tra.length() == 0) {
            training.setText("Ei yhtään lutemonia treenissä");
        } else training.setText("Treenikentällä: " + tra);

        // Inflate the layout for this fragment
        return v;
    }
}