package com.example.prodoreviewer;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CareerPathAdapter extends RecyclerView.Adapter<CareerPathAdapter.CareerPathViewHolder>{
    private List<CareerPath> careerPaths;
    private DatabaseHelper db;

    String UserEmail;

    // Constructor to initialize the adapter with the list of career paths
    public CareerPathAdapter(List<CareerPath> careerPaths, DatabaseHelper db, String userEmail) {
        this.careerPaths = careerPaths;
        this.db = db;
        this.UserEmail = userEmail;
    }

    // ViewHolder class representing each item in the RecyclerView
    public static class CareerPathViewHolder extends RecyclerView.ViewHolder {
        TextView careerName;
        ImageView careerInfo;
        ConstraintLayout mainLayout;
        //TextView descriptionTextView;

        public CareerPathViewHolder(View itemView) {
            super(itemView);
            careerName = itemView.findViewById(R.id.txtCareer);
            careerInfo = itemView.findViewById(R.id.imgIcon);
            mainLayout = itemView.findViewById(R.id.design1);
            //descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }

    @NonNull
    @Override
    public CareerPathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_design_2, parent, false);
        return new CareerPathViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CareerPathViewHolder holder, int position) {
        // Bind the data to the views in each item
        CareerPath careerPath = careerPaths.get(position);
        holder.careerName.setText(careerPath.getName());
        //holder.descriptionTextView.setText(careerPath.getDescription());
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CareerPathsRecommendationsPage.class);
            intent.putExtra("careerName", careerPath.getName());
            intent.putExtra("email", UserEmail);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return careerPaths.size();
    }
}