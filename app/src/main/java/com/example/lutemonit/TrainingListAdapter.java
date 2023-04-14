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
        holder.time.setText("Lutemoni saapui treeniin: " + items.get(position).getTrainingTime());
        System.out.println(items.get(position).getName() + items.get(position).getAttack() + items.get(position).getDefense());

        // Add click-listener to Go To Training -button
        holder.train.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            // get right lutemon to list
            Lutemon lutemon = items.get(pos);
            // Move lutemon to home
            Storage.getInstance().setLutemonToHome(lutemon);
            notifyItemRemoved(pos);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class TrainingListHolder extends RecyclerView.ViewHolder {

    TextView name, experience, time; // create items for textViews
    ImageView train;
    private TrainingListAdapter adapter;

    public TrainingListHolder(@NonNull View itemView) { // Holder link view and code together
        super(itemView);

        name = itemView.findViewById(R.id.tbLutemonsNameTraining); // link TextViews to code
        experience = itemView.findViewById(R.id.tbExperience);
        time = itemView.findViewById(R.id.tbArriveTime);
        train = itemView.findViewById(R.id.imgTrainingToHome);


    }

    public TrainingListHolder linkAdapter(TrainingListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
