package com.example.lutemonit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import lutemonfarm.Lutemon;

public class BattleAdapter extends RecyclerView.Adapter<BattleHolder> {

    ArrayList<ArrayList<String>> battle = new ArrayList<>();
    ArrayList<String> battlePart = new ArrayList<>();
    private int attacker;
    private int defenser;


    public BattleAdapter(ArrayList<ArrayList<String>> battle) {
        this.battle = battle;
    }

    @NonNull
    @Override
    public BattleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.battle_fight, parent, false); // Choose card view
        return new BattleHolder(view).linkAdapter(this); // link items to card
    }

    @Override
    public void onBindViewHolder(@NonNull BattleHolder holder, int position) {
        // Pick battle part from battle ArrayList
        battlePart = battle.get(position);
        try{
            attacker = Integer.parseInt(battlePart.get(1));
            defenser = Integer.parseInt(battlePart.get(2));
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        holder.imgAttackLut.setImageResource(attacker); // Set images
        holder.imgDefenseLut.setImageResource(defenser);
        holder.lblTitle.setText(battlePart.get(0));
        //holder.lblTitle.setText("Lutemon " + luts[0].getName() + " hyökkää lutemonin " + luts[1].getName() + " kimppuun!!");
        //holder.lblAttackLut.setText(luts[0].getDetails());
        //holder.lblDefenseLut.setText(luts[1].getDetails());

    }

    @Override
    public int getItemCount() {
        return battle.size();
    }

    public void updateData(ArrayList<ArrayList<String>> battle) {
        // Update recyclerview data
        this.battle = battle;
        notifyDataSetChanged();
    }
}


class BattleHolder extends RecyclerView.ViewHolder {

    TextView lblTitle, lblAttackLut, lblDefenseLut; // create items for textViews
    ImageView imgAttackLut, imgDefenseLut;
    private BattleAdapter adapter;


    public BattleHolder(@NonNull View itemView) { // Holder link view and code together
        super(itemView);

        lblTitle = itemView.findViewById(R.id.lblTitle); // link items to code

        imgAttackLut = itemView.findViewById(R.id.imgAttackLut);
        imgDefenseLut = itemView.findViewById(R.id.imgDefenseLut);

    }

    public BattleHolder linkAdapter(BattleAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}