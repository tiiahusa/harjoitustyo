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
    //ArrayList<String> battle;
    //ArrayList<String> firstDetails;
    //ArrayList<String> secDetails;
    ArrayList<Lutemon[]> order;
    ArrayList<String> battle = new ArrayList<>();
    HashMap<String, Lutemon[]> orders = new HashMap<>();
    private Lutemon[] luts;

    public BattleAdapter(HashMap<String, Lutemon[]> orders) {
        //this.order = order;
        this.orders = orders;
        battle.clear();
        for (String x: orders.keySet()) {
            this.battle.add(x);
        }
    }

    @NonNull
    @Override
    public BattleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.battle_fight, parent, false); // Choose card view
        return new BattleHolder(view).linkAdapter(this); // link items to card
    }

    @Override
    public void onBindViewHolder(@NonNull BattleHolder holder, int position) {
        luts = orders.get(battle.get(position));
        //luts = order.get(position); // Get [] to ArrayList
        holder.imgAttackLut.setImageResource(luts[0].getPic()); // Set images
        holder.imgDefenseLut.setImageResource(luts[1].getPic());
        holder.lblTitle.setText(battle.get(position));
        orders.
        //holder.lblTitle.setText("Lutemon " + luts[0].getName() + " hyökkää lutemonin " + luts[1].getName() + " kimppuun!!");
        //holder.lblAttackLut.setText(luts[0].getDetails());
        //holder.lblDefenseLut.setText(luts[1].getDetails());

    }

    @Override
    public int getItemCount() {
        return order.size();
    }

    public void updateData(HashMap<String, Lutemon[]> orders) {
        battle.clear();
        this.orders = orders;
        for (String x: orders.keySet()) {
            this.battle.add(x);
        }
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
        lblAttackLut = itemView.findViewById(R.id.lblAttackLut);
        lblDefenseLut = itemView.findViewById(R.id.lblDefenseLut);

        imgAttackLut = itemView.findViewById(R.id.imgAttackLut);
        imgDefenseLut = itemView.findViewById(R.id.imgDefenseLut);

    }

    public BattleHolder linkAdapter(BattleAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}