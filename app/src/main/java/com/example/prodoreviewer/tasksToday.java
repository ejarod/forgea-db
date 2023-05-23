package com.example.prodoreviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tasksToday extends AppCompatActivity {
    RecyclerView recyclerView;
    recycleAdapter adapter;

    myDatabasehelper myDB;
    ArrayList<String> task_id, task_name, task_icon, task_icon_color, task_date, task_description, task_list_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tasks_today);

        ImageButton menuBtn = findViewById(R.id.menubtn);
        ImageButton back = findViewById(R.id.btnBackButton);
        ImageButton home = findViewById(R.id.btnHome);
        ImageButton menu = findViewById(R.id.menubtn);
        TextView lblPageName = findViewById(R.id.lblPageName);
        lblPageName.setText("Today");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tasksToday.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tasksToday.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        myDB = new myDatabasehelper(tasksToday.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();
        task_icon = new ArrayList<>();
        task_icon_color = new ArrayList<>();
        task_date = new ArrayList<>();
        task_description = new ArrayList<>();
        task_list_name = new ArrayList<>();

        recyclerView = findViewById(R.id.tasklistToday);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTasksForToday();

        adapter = new recycleAdapter(tasksToday.this, task_id, task_name, task_icon,
                task_icon_color, task_date, task_description, task_list_name);
        adapter.setOnItemClickListener(new recycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String taskId, String taskName, String taskIcon,
                                    String taskIconColor, String taskDate, String taskDescription, String taskListName) {
                Intent intent = new Intent(tasksToday.this, updatetaskpage.class);
                intent.putExtra("taskId", taskId);
                intent.putExtra("taskName", taskName);
                intent.putExtra("taskIcon", taskIcon);
                intent.putExtra("taskIconColor", taskIconColor);
                intent.putExtra("taskDate", taskDate);
                intent.putExtra("taskDescription", taskDescription);
                intent.putExtra("taskListName", taskListName);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tasksToday.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
    void getTasksForToday() {
        Cursor cursor = myDB.getTasksForToday();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks for today.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                task_id.add(cursor.getString(0));
                task_name.add(cursor.getString(1));
                task_icon.add(cursor.getString(2));
                task_icon_color.add(cursor.getString(3));
                task_date.add(cursor.getString(4));
                task_description.add(cursor.getString(5));
                task_list_name.add(cursor.getString(6));
            }
        }
    }
}