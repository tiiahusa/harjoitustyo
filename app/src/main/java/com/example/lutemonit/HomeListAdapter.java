package com.example.lutemonit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListHolder> {

    ArrayList<Lutemon> items;
    Context context;

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

        // Add click-listener to Go To Battle -button
        holder.battle.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lutemon = items.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToBattle(lutemon);
            if(x) { // if Success
                //Toast.makeText(context,"Lutemon " + lutemon.getName() + " lisätty taisteluareenalle!",Toast.LENGTH_LONG).show();
                notifyItemRemoved(pos);
            } //else Toast.makeText(context, "Taisteluareena täynnä, lutemonia ei lisätty!", Toast.LENGTH_LONG).show(); //Not success
        });
        // Add click-listener to Go To Training -button
        holder.training.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lutemon = items.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToTraining(lutemon);
            if(x) { // if Success
                //Toast.makeText(context,"Lutemon " + lutemon.getName() + " lähti treeneihin!",Toast.LENGTH_LONG).show();
                notifyItemRemoved(pos);
            } //else Toast.makeText(context, "Treenisali täynnä, lutemoni " + lutemon.getName() + " jäi kotia ihmettelemään!", Toast.LENGTH_LONG).show(); //Not success
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class HomeListHolder extends RecyclerView.ViewHolder {

    TextView name, attack, defense, experience, health; // create items for textViews
    ImageView training, battle;
    private HomeListAdapter adapter;


    public HomeListHolder(@NonNull View itemView) { // Holder link view and code together
        super(itemView);

        name = itemView.findViewById(R.id.tbLutemonsName); // link TextViews to code
        attack = itemView.findViewById(R.id.tbAttackPoints);
        defense = itemView.findViewById(R.id.tbDefensePoints);
        experience = itemView.findViewById(R.id.tbExperiencePoints);
        health = itemView.findViewById(R.id.tbHealthPoints);
        training = itemView.findViewById(R.id.imgHomeToTraining);
        battle = itemView.findViewById(R.id.imgHomeToBattle);


    }

    public HomeListHolder linkAdapter(HomeListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
