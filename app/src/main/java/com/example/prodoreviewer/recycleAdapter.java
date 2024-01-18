package com.example.prodoreviewer;

import android.content.Context;
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

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{
    Context context;
    ArrayList task_id, task_name,task_icon, task_icon_color, task_date,task_description, task_list_name;
    OnItemClickListener listener;
    Animation translate_anim;

    recycleAdapter(Context context,ArrayList task_id,ArrayList task_name, ArrayList task_icon,
                   ArrayList task_icon_color, ArrayList task_date, ArrayList task_description, ArrayList task_list_name){
        this.context = context;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_icon = task_icon;
        this.task_icon_color = task_icon_color;
        this.task_date = task_date;
        this.task_description = task_description;
        this.task_list_name = task_list_name;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.task_nametxt.setText(String.valueOf(task_name.get(position)));
        holder.task_datetxt.setText(String.valueOf(task_date.get(position)));

        String colorString = (String) task_icon_color.get(position);
        int color;
        try {
            color = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            // Handle invalid color string
            color = Color.WHITE; // Set a default color
        }

        holder.icon_background.setCardBackgroundColor(color);

        String iconName = (String) task_icon.get(position);
        int iconResId = getTaskIcon(iconName);
        holder.task_icon_img.setImageResource(iconResId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(position, (String) task_id.get(position), (String) task_name.get(position), (String) task_icon.get(position),
                            (String) task_icon_color.get(position), (String) task_date.get(position), (String) task_description.get(position),
                            (String) task_list_name.get(position));
                }
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
        return task_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_nametxt;
        ImageView task_icon_img;

        CardView icon_background;
        ConstraintLayout mainLayout;

        TextView task_datetxt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_nametxt = itemView.findViewById(R.id.txtCareer);
            task_icon_img = itemView.findViewById(R.id.imgIcon);
            icon_background = itemView.findViewById(R.id.icon_background);

            mainLayout = itemView.findViewById(R.id.design1);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim2);
            mainLayout.setAnimation(translate_anim);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position, String taskId, String taskName,
                         String taskIcon, String taskIconColor, String taskDate,
                         String taskDescription, String taskListName);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}