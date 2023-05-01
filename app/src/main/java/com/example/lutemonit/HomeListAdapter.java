package com.example.lutemonit;

import android.app.Activity;
import android.content.Context;
import android.nfc.cardemulation.CardEmulation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

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
        // Get lutemon from items-list
        Lutemon lutemon = items.get(position);
        // Link code to layout things
        holder.pic.setImageResource(lutemon.getPic());
        holder.name.setText(lutemon.getName() + "  (" + lutemon.getColor() + ")");
        holder.attack.setText("Taistelupisteet: " + lutemon.getAttack());
        holder.defense.setText("Puolustuspisteet: " + lutemon.getDefense());
        holder.experience.setText("Kokemuspisteet: " + lutemon.getExperience());
        holder.health.setText("Elämä: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
        //System.out.println(lutemon.getName() + lutemon.getAttack() + lutemon.getDefense());

        // Add click-listener to Go To Battle -button
        holder.battle.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lut = items.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToBattle(lut);
            if(x) { // if Success
                holder.transfer.setVisibility(View.GONE);
                notifyItemRemoved(pos);
            } else {
                holder.transfer.setVisibility(View.VISIBLE);
            }
        });

        // Add click-listener to Go To Training -button
        holder.training.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lut = items.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToTraining(lut);
            if(x) { // if Success
                notifyItemRemoved(pos);
            } else {
                holder.transfer.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class HomeListHolder extends RecyclerView.ViewHolder {

    TextView name, attack, defense, experience, health, transfer; // create items for textViews
    ImageView training, battle, pic;
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
        pic = itemView.findViewById(R.id.imgLutemon);
        transfer = itemView.findViewById(R.id.lblTransferFalse);

    }

    public HomeListHolder linkAdapter(HomeListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
