package com.example.lutemonit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private ArrayList<Lutemon> list;
    private Context context;

    public ListAdapter(Context context, ArrayList<Lutemon> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_at_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lutemon lutemon = list.get(position);
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
            Lutemon lut = list.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToBattle(lut);
            if(x) { // if Success
                notifyItemRemoved(pos);
            }
        });
        // Add click-listener to Go To Training -button
        holder.training.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lut = list.get(pos);
            // Try add it to the battle area
            boolean x = Storage.getInstance().setLutemonToTraining(lut);
            if(x) { // if Success
                notifyItemRemoved(pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, attack, defense, experience, health; // create items for textViews
        ImageView training, battle, pic;
        private ListAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tbLutemonsName); // link TextViews to code
            attack = itemView.findViewById(R.id.tbAttackPoints);
            defense = itemView.findViewById(R.id.tbDefensePoints);
            experience = itemView.findViewById(R.id.tbExperiencePoints);
            health = itemView.findViewById(R.id.tbHealthPoints);
            training = itemView.findViewById(R.id.imgHomeToTraining);
            battle = itemView.findViewById(R.id.imgHomeToBattle);
            pic = itemView.findViewById(R.id.imgLutemon);
        }

        public MyViewHolder linkAdapter(ListAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}

