package com.example.prodoreviewer;

import android.annotation.SuppressLint;
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

public class mainMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    recycleAdapter2 adapter;
    recycleAdapter adapter2;
    String UserEmail = "1";

    myDatabasehelper2 myDB2;
    myDatabasehelper myDB;
    ArrayList<String> list_id, list_title, list_icon, list_color;
    ArrayList<String> task_id, task_name, task_icon, task_icon_color, task_date, task_description, task_list_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(0, 0);

        setContentView(R.layout.activity_main_menu);

        ImageButton taskadd = findViewById(R.id.addTask);
        ImageButton back = findViewById(R.id.btnBackButton);
        ImageButton home =findViewById(R.id.btnHome);
        ImageButton btnUpcoming = findViewById(R.id.btnAssessment);
        ImageButton btnToday = findViewById(R.id.btnCareerPaths);
        ImageButton btnFlashcard = findViewById(R.id.btnPersonalityTraits);
        ImageButton btnListView = findViewById(R.id.btnProfile);
        TextView txtAssessment = findViewById(R.id.txtAssessment);
        TextView today = findViewById(R.id.txtCareerPaths);
        TextView addnewList = findViewById(R.id.addnewlist);
        TextView listview = findViewById(R.id.txtProfile);
        TextView lblPageName = findViewById(R.id.lblPageName);
        TextView list = findViewById(R.id.textView10);
        lblPageName.setText("Menu");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView flashcard = findViewById(R.id.txtPersonalityTraits);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        myDB = new myDatabasehelper(mainMenu.this);
        myDB2 = new myDatabasehelper2(mainMenu.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();
        task_icon = new ArrayList<>();
        task_icon_color = new ArrayList<>();
        task_date = new ArrayList<>();
        task_description = new ArrayList<>();
        task_list_name = new ArrayList<>();

        list_id = new ArrayList<>();
        list_title = new ArrayList<>();
        list_icon = new ArrayList<>();
        list_color = new ArrayList<>();

        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);

                /*recyclerView = findViewById(R.id.tasklistmenu);
                recyclerView.setLayoutManager(new LinearLayoutManager(mainMenu.this));
                displayData();

                adapter = new recycleAdapter2(mainMenu.this,list_id,list_title,list_icon,
                        list_color);
                list.setText("Lists");
                recyclerView.setAdapter(adapter);*/
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);

                /*recyclerView = findViewById(R.id.tasklistmenu);
                recyclerView.setLayoutManager(new LinearLayoutManager(mainMenu.this));
                displayData();

                adapter = new recycleAdapter2(mainMenu.this,list_id,list_title,list_icon,
                        list_color);
                list.setText("Lists");
                recyclerView.setAdapter(adapter);*/
            }
        });

        txtAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);

                /*recyclerView = findViewById(R.id.tasklistmenu);
                recyclerView.setLayoutManager(new LinearLayoutManager(mainMenu.this));
                getUpcomingTasks();

                adapter2 = new recycleAdapter(mainMenu.this, task_id, task_name, task_icon,
                        task_icon_color, task_date, task_description, task_list_name);
                adapter2.setOnItemClickListener(new recycleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, String taskId, String taskName, String taskIcon,
                                            String taskIconColor, String taskDate, String taskDescription, String taskListName) {
                        Intent intent = new Intent(mainMenu.this, updatetaskpage.class);
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
                list.setText("Upcoming");
                recyclerView.setAdapter(adapter2);*/
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);

                /*Intent intent = new Intent(mainMenu.this,tasksToday.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/

                /*recyclerView = findViewById(R.id.tasklistmenu);
                recyclerView.setLayoutManager(new LinearLayoutManager(mainMenu.this));

                getTasksForToday();
                adapter2 = new recycleAdapter(mainMenu.this, task_id, task_name, task_icon,
                        task_icon_color, task_date, task_description, task_list_name);
                adapter2.setOnItemClickListener(new recycleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, String taskId, String taskName, String taskIcon,
                                            String taskIconColor, String taskDate, String taskDescription, String taskListName) {
                        Intent intent = new Intent(mainMenu.this, updatetaskpage.class);
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
                list.setText("Today");
                recyclerView.setAdapter(adapter2);*/
            }
        });

        flashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

        btnFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

        addnewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, taskpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        taskadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, taskpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this,tasksToday.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this,Upcomingtasks.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.tasklistmenu2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String sort;

        sort = intent.getStringExtra("sort");

        if(sort==null || sort.equals("none")) {
            displayData();

            adapter = new recycleAdapter2(mainMenu.this,list_id,list_title,list_icon,
                    list_color);
            recyclerView.setAdapter(adapter);
        } else {
            getTasks(sort);

            adapter2 = new recycleAdapter(mainMenu.this, task_id, task_name, task_icon,
                    task_icon_color, task_date, task_description, task_list_name);
            adapter2.setOnItemClickListener(new recycleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, String taskId, String taskName, String taskIcon,
                                        String taskIconColor, String taskDate, String taskDescription, String taskListName) {
                    Intent intent = new Intent(mainMenu.this, updatetaskpage.class);
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
            list.setText(sort);
            recyclerView.setAdapter(adapter2);
        }

    }
    void displayData() {
        Cursor cursor = myDB2.readAllData();
        list_id.clear();
        list_title.clear();
        list_icon.clear();
        list_color.clear();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<String> uniqueTitles = new ArrayList<>();

            while (cursor.moveToNext()) {
                String title = cursor.getString(1);
                if (!uniqueTitles.contains(title)) {
                    uniqueTitles.add(title);
                    list_id.add(cursor.getString(0));
                    list_title.add(title);
                    list_icon.add(cursor.getString(2));
                    list_color.add(cursor.getString(3));
                }
            }
        }
    }

    void getUpcomingTasks() {
        Cursor cursor = myDB.getTasksNotForToday();
        task_id.clear();
        task_name.clear();
        task_icon.clear();
        task_icon_color.clear();
        task_date.clear();
        task_description.clear();
        task_list_name.clear();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Upcoming Tasks.", Toast.LENGTH_SHORT).show();
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

    void getTasks(String list) {
        Cursor cursor = myDB.getTasks(list);
        task_id.clear();
        task_name.clear();
        task_icon.clear();
        task_icon_color.clear();
        task_date.clear();
        task_description.clear();
        task_list_name.clear();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks for '"+list+"'", Toast.LENGTH_SHORT).show();
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

    void getTasksForToday() {
        Cursor cursor = myDB.getTasksForToday();
        task_id.clear();
        task_name.clear();
        task_icon.clear();
        task_icon_color.clear();
        task_date.clear();
        task_description.clear();
        task_list_name.clear();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks for today", Toast.LENGTH_SHORT).show();
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