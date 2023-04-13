package com.example.lutemonit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import areas.BattleArea;
import areas.TrainingArea;
import lutemonfarm.Lutemon;

public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListHolder> {

    ArrayList<Lutemon> items;

    public TrainingListAdapter(ArrayList<Lutemon> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public TrainingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Create recyclerview list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lutemon_at_training, parent, false); // Choose card view
        return new TrainingListHolder(view).linkAdapter(this); // link items to card
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingListHolder holder, int position) { // Set card item's texts and image
        holder.name.setText(items.get(position).getName() + "  (" + items.get(position).getColor() + ")");
        holder.experience.setText("Kokemuspisteet: " + items.get(position).getExperience());
        holder.time.setText("Lutemoni saapui treeniin: " + TrainingArea.getInstance().GetLutemonsArriveTime(items.get(position)));
        System.out.println(items.get(position).getName() + items.get(position).getAttack() + items.get(position).getDefense());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class TrainingListHolder extends RecyclerView.ViewHolder {

    TextView name, experience, time; // create items for textViews
    private TrainingListAdapter adapter;
    Context context;

    public TrainingListHolder(@NonNull View itemView) { // Holder link view and code together
        super(itemView);

        name = itemView.findViewById(R.id.tbLutemonsName); // link TextViews to code
        experience = itemView.findViewById(R.id.tbExperiencePoints);
        time = itemView.findViewById(R.id.tbArriveTime);

        // Add click-listener to Go ToHome -button
        itemView.findViewById(R.id.btnHome).setOnClickListener(view -> {
            // get right lutemon to list
            Lutemon lutemon = adapter.items.get(getAdapterPosition());
            // Get Lutemon to Training Area
            TrainingArea.getInstance().GetLutemonFromTrainingArea(lutemon);
            // Information to user that lutemon is deleted
            Toast.makeText(context,"Lutemon " + lutemon.getName() + " keskeyttää treenin ja palaa kotiin!",Toast.LENGTH_LONG).show();

        });

    }

    public TrainingListHolder linkAdapter(TrainingListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
