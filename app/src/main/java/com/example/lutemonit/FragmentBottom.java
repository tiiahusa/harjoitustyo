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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View v;
    TextView battle, training;
    ArrayList<Lutemon> lutBattle = new ArrayList<>();
    ArrayList<Lutemon> lutTrain = new ArrayList<>();

    public FragmentBottom() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBottom.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBottom newInstance(String param1, String param2) {
        FragmentBottom fragment = new FragmentBottom();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

        battle = v.findViewById(R.id.lblBattleLutemons);
        training = v.findViewById(R.id.lblTrainLutemons);

        lutBattle = Storage.getInstance().getLutemonsFromBattle();
        lutTrain = Storage.getInstance().getLutemonsFromTraining();
        String bat = "";
        for (Lutemon lut: lutBattle) {
            if (bat.length() > 0) { // Lisätään pilkku tuotteiden väliin
                bat += ", ";
            }
            bat += lut.getName();
        }
        String tra = "";
        for (Lutemon lut: lutTrain) {
            if (tra.length() > 0) { // Lisätään pilkku tuotteiden väliin
                tra += ", ";
            }
            tra += lut.getName();
        }
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