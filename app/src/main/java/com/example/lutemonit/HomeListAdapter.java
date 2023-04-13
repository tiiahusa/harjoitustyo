package com.example.lutemonit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import areas.BattleArea;
import areas.TrainingArea;
import lutemonfarm.Lutemon;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListHolder> {

    ArrayList<Lutemon> items;

    public HomeListAdapter(ArrayList<Lutemon> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public HomeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Create recyclerview list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lutemon_at_home, parent, false); // Choose card view
        return new HomeListHolder(view).linkAdapter(this); // link items to card
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListHolder holder, int position) { // Set card item's texts and image
        holder.name.setText(items.get(position).getName() + "  (" + items.get(position).getColor() + ")");
        holder.attack.setText("Taistelupisteet: " + items.get(position).getAttack());
        holder.defense.setText("Puolustuspisteet: " + items.get(position).getDefense());
        holder.experience.setText("Kokemuspisteet: " + items.get(position).getExperience());
        holder.health.setText("Elämä: " + items.get(position).getHealth() + "/" + items.get(position).getMaxHealth());
        System.out.println(items.get(position).getName() + items.get(position).getAttack() + items.get(position).getDefense());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class HomeListHolder extends RecyclerView.ViewHolder {

    TextView name, attack, defense, experience, health; // create items for textViews
    private HomeListAdapter adapter;
    Context context;

    public HomeListHolder(@NonNull View itemView) { // Holder link view and code together
        super(itemView);

        name = itemView.findViewById(R.id.tbLutemonsName); // link TextViews to code
        attack = itemView.findViewById(R.id.tbAttackPoints);
        defense = itemView.findViewById(R.id.tbDefensePoints);
        experience = itemView.findViewById(R.id.tbExperiencePoints);
        health = itemView.findViewById(R.id.tbHealthPoints);

        // Add click-listener to Go To Battle -button
        itemView.findViewById(R.id.btnBattle).setOnClickListener(view -> {
            // get right lutemon to list
            Lutemon lutemon = adapter.items.get(getAdapterPosition());
            // Try add it to the battle area
            boolean x = BattleArea.getInstance().AddLutemonToBattleArea(lutemon);
            if(x) { // if Success
                Toast.makeText(context,"Lutemon " + lutemon.getName() + " lisätty taisteluareenalle!",Toast.LENGTH_LONG).show();
            } else Toast.makeText(context, "Taisteluareena täynnä, lutemonia ei lisätty!", Toast.LENGTH_LONG).show(); //Not success
        });
        // Add click-listener to Go To Training -button
        itemView.findViewById(R.id.btnTraining).setOnClickListener(view -> {
            // get right lutemon to list
            Lutemon lutemon = adapter.items.get(getAdapterPosition());
            // Try add it to the battle area
            boolean x = TrainingArea.getInstance().AddLutemonToTrainingArea(lutemon);
            if(x) { // if Success
                Toast.makeText(context,"Lutemon " + lutemon.getName() + " lähti treeneihin!",Toast.LENGTH_LONG).show();
                adapter.notifyItemRemoved(getAdapterPosition());
            } else Toast.makeText(context, "Treenisali täynnä, lutemoni jäi kotia ihmettelemään!", Toast.LENGTH_LONG).show(); //Not success
        });
    }

    public HomeListHolder linkAdapter(HomeListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
