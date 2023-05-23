package com.example.prodoreviewer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class taskpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_taskpage);

        Button btnSave = findViewById(R.id.btnSave);
        ImageButton btnBack = findViewById(R.id.btnBackButton);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton menuBtn = findViewById(R.id.menubtn);
        EditText taskDate = findViewById(R.id.taskDue);
        EditText taskname, taskdescription, tasklist;
        TextInputEditText taskIcon = findViewById(R.id.taskIcon);
        TextView lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Task");

        Spinner colorSpinner = findViewById(R.id.taskColor);
        String selectedColor = colorSpinner.getSelectedItem().toString();


        taskname = findViewById(R.id.taskTitle);
        taskdescription = findViewById(R.id.taskDescription);
        tasklist = findViewById(R.id.taskList);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(taskpage.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(taskpage.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedColor = adapterView.getSelectedItem().toString();
                int color = Color.parseColor(selectedColor);
                findViewById(R.id.taskColor).setBackgroundColor(color); // Replace R.id.myLinearLayout with the ID of your layout
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Set a default background color when no item is selected
                int defaultColor = Color.WHITE; // Set your desired default color
                findViewById(R.id.taskColor).setBackgroundColor(defaultColor); // Replace R.id.myLinearLayout with the ID of your layout
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = taskname.getText().toString().trim();
                String description = taskdescription.getText().toString().trim();
                String list = tasklist.getText().toString().trim();
                String date = taskDate.getText().toString().trim();

                if (name.isEmpty()) {
                    taskname.setError("Task name cannot be empty");
                    taskname.requestFocus();
                    return;
                }

                if (list.isEmpty()) {
                    tasklist.setError("Task list cannot be empty");
                    tasklist.requestFocus();
                    return;
                }
                if (date.isEmpty()) {
                    taskDate.setError("Task date cannot be empty");
                    taskDate.requestFocus();
                    return;
                }

                // All fields are valid, proceed with saving the data
                myDatabasehelper myDB = new myDatabasehelper(taskpage.this);
                myDB.addList(name, taskIcon.getText().toString().trim(),
                        colorSpinner.getSelectedItem().toString().trim(), taskDate.getText().toString().trim(),
                        description, list);

                myDatabasehelper2 myDB2 = new myDatabasehelper2(taskpage.this);
                myDB2.addList(list, taskIcon.getText().toString().trim(),
                        colorSpinner.getSelectedItem().toString().trim());

                Intent intent = new Intent(taskpage.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        taskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        taskpage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayOfMonth) {
                                String selectedDate = dayOfMonth + "/" + (monthofYear+1) + "/" + year;
                                taskDate.setText(selectedDate);
                            }
                        },
                        year,month,day
                );
                datePickerDialog.show();
            }
        });

        taskIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayAdapter<String> iconAdapter = new ArrayAdapter<String>(
                        taskpage.this,
                        android.R.layout.simple_dropdown_item_1line,
                        new String[]{"Book", "Check", "Bell"}
                );

                new AlertDialog.Builder(taskpage.this)
                        .setTitle("Select an Icon")
                        .setAdapter(iconAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String selectedIcon = iconAdapter.getItem(which);
                                Drawable iconDrawable = getIconDrawable(selectedIcon);
                                taskIcon.setText(selectedIcon);
                                taskIcon.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,iconDrawable,null);

                            }
                        })
                        .show();
            }
        });
    }
    private Drawable getIconDrawable(String selectedIcon) {
        Drawable iconDrawable = null;

        switch (selectedIcon) {
            case "Book":
                iconDrawable = getResources().getDrawable(R.drawable.book); // Replace with your own icon resource
                break;
            case "Check":
                iconDrawable = getResources().getDrawable(R.drawable.baseline_check_24); // Replace with your own icon resource
                break;
            case "Bell":
                iconDrawable = getResources().getDrawable(R.drawable.belll); // Replace with your own icon resource
                break;
            // Add more cases for additional icons

            default:
                // If the selected icon doesn't match any case, you can handle it accordingly
                break;
        }

        return iconDrawable;
    }
}