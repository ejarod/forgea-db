package com.example.prodoreviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    recycleAdapter adapter;

    myDatabasehelper myDB;
    ArrayList<String> task_id, task_name, task_icon, task_icon_color, task_date, task_description, task_list_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton menuButton = findViewById(R.id.menubtn);
        Button tasks4today = findViewById(R.id.btnToday);

        myDB = new myDatabasehelper(MainActivity.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();
        task_icon = new ArrayList<>();
        task_icon_color = new ArrayList<>();
        task_date = new ArrayList<>();
        task_description = new ArrayList<>();
        task_list_name = new ArrayList<>();

        recyclerView = findViewById(R.id.tasklisthome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTasksForToday();

        adapter = new recycleAdapter(MainActivity.this, task_id, task_name, task_icon,
                task_icon_color, task_date, task_description, task_list_name);
        adapter.setOnItemClickListener(new recycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String taskId, String taskName, String taskIcon,
                                    String taskIconColor, String taskDate, String taskDescription, String taskListName) {
                Intent intent = new Intent(MainActivity.this, updatetaskpage.class);
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

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("sort", "none");
                startActivity(intent);
            }
        });


        /*tasks4today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tasksToday.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
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