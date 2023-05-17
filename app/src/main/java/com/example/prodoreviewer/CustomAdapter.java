package com.example.prodoreviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> topic_id, topic_name;
    private MyDatabaseHelper myDB;


    public CustomAdapter(Context context, ArrayList<String> topic_id, ArrayList<String> topic_name) {
        this.context = context;
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder,final int position) {
        myDB = new MyDatabaseHelper(context);
        holder.txtTopicCards.setText(String.valueOf(myDB.getCards(topic_name.get(position)).size()));
        holder.txtTopic_name.setText(String.valueOf(topic_name.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProdoCardDisplay.class);
                intent.putExtra("topic",String.valueOf(topic_name.get(position)));
                context.startActivity(intent);

                if (view.getContext() instanceof Activity) {
                    Activity activity = (Activity) view.getContext();
                    activity.finish();
                }
            }
        });

        holder.btnAdd_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProdoCardCreate.class);
                intent.putExtra("topic",String.valueOf(topic_name.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topic_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTopicCards, txtTopic_name;
        ConstraintLayout mainLayout;
        Button btnAdd_Card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopicCards = itemView.findViewById(R.id.txtTopicCards);
            txtTopic_name = itemView.findViewById(R.id.txtTopic_name);
            btnAdd_Card = itemView.findViewById(R.id.btnAdd_Card);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
