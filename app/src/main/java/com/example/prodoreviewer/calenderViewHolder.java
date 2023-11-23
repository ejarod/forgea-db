package com.example.prodoreviewer;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class calenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayOfMonth;
    private final calenderAdapter.OnItemListener onItemListener;

    public calenderViewHolder(@NonNull View itemView, calenderAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(),(String) dayOfMonth.getText());
    }
}
