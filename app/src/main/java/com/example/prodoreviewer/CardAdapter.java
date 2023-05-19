package com.example.prodoreviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> card_name;
    private MyDatabaseHelper myDB;
    private Animation translate_anim;


    public CardAdapter(Context context, ArrayList<String> card_name) {
        this.context = context;
        this.card_name = card_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, final int position) {
        myDB = new MyDatabaseHelper(context);
        holder.txtCard_name.setText(String.valueOf(card_name.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Card card = myDB.getCard(String.valueOf(card_name.get(position)));
                Intent intent = new Intent(context,ProdoCardEdit.class);
                intent.putExtra("front",String.valueOf(card_name.get(position)));
                context.startActivity(intent);

                if (view.getContext() instanceof Activity) {
                    Activity activity = (Activity) view.getContext();
                    activity.finish();
                }
            }
        });

        holder.btnDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = myDB.getCard(String.valueOf(card_name.get(position)));

                myDB.deleteCard(String.valueOf(card_name.get(position)));

                Intent intent = new Intent(context, ProdoCards.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("topic",card.getTopic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return card_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCard_name;
        ConstraintLayout mainLayout;
        ImageButton btnDeleteCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCard_name = itemView.findViewById(R.id.txtCard_name);
            mainLayout = itemView.findViewById(R.id.mainCardLayout);
            btnDeleteCard = itemView.findViewById(R.id.btnDeleteCard);

            translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
