package com.example.prodoreviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleAdapter2 extends RecyclerView.Adapter<recycleAdapter2.MyViewHolder> {
    Context context;
    ArrayList list_id, list_name, list_icon, list_color;
    private Animation translate_anim;

    myDatabasehelper2 dbHelper2;
    recycleAdapter2(Context context, ArrayList list_id, ArrayList list_name, ArrayList list_icon, ArrayList list_color){
        this.context = context;
        this.list_id = list_id;
        this.list_name = list_name;
        this.list_icon = list_icon;
        this.list_color = list_color;
        dbHelper2 = new myDatabasehelper2(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_design2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.list_nametxt.setText(String.valueOf(list_name.get(position)));

        String colorString = (String) list_color.get(position);
        int color;
        try {
            color = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            // Handle invalid color string
            color = Color.BLACK; // Set a default color
        }

        holder.icon_background.setCardBackgroundColor(color);

        //String iconName = (String) list_icon.get(position);
        //int iconResId = getTaskIcon(iconName);
        //holder.list_icon_img.setImageResource(iconResId);
        holder.list_icon_img.setVisibility(View.GONE);

        //Get the count of data entries with the same list name
        String listName = String.valueOf(list_name.get(position));
        int count = dbHelper2.countDataByListName(listName);
        holder.samelist_namecount.setText(String.valueOf(count));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("sort", String.valueOf(list_name.get(position)));
                context.startActivity(intent);
            }
        });
    }

    private int getTaskIcon(String iconName) {
        int iconResId = R.drawable.baseline_check_24; // Default icon resource

        if (iconName.equals("Book")) {
            iconResId = R.drawable.book;
        } else if (iconName.equals("Check")) {
            iconResId = R.drawable.baseline_check_24;
        } else if (iconName.equals("Bell")) {
            iconResId = R.drawable.belll;
        }
        // Add more conditions for other icon names

        return iconResId;
    }
    @Override
    public int getItemCount() {
        return list_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_nametxt;
        ImageView list_icon_img;
        ConstraintLayout mainLayout;
        CardView icon_background;

        TextView samelist_namecount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_nametxt = itemView.findViewById(R.id.txtCareer);
            list_icon_img = itemView.findViewById(R.id.imgIcon);
            icon_background = itemView.findViewById(R.id.icon_background);
            samelist_namecount = itemView.findViewById(R.id.listcount);
            mainLayout = itemView.findViewById(R.id.design2);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim2);
            mainLayout.setAnimation(translate_anim);
        }
    }
}