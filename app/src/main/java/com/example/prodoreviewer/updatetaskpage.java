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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Calendar;

public class updatetaskpage extends AppCompatActivity {

    private EditText taskTitleNew;
    private EditText taskDescriptionNew;
    private EditText taskListNew;
    private EditText taskDueNew;
    private TextInputEditText taskIconNew;
    private Spinner colorSpinnerNew;
    private Button btnUpdate, btnDelete;

    private ImageButton btnBack, btnHome, btnMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_updatetaskpage);

        taskTitleNew = findViewById(R.id.taskTitleNew);
        taskDescriptionNew = findViewById(R.id.taskDescriptionNew);
        taskListNew = findViewById(R.id.taskListNew);
        taskDueNew = findViewById(R.id.taskDueNew);
        taskIconNew = findViewById(R.id.taskIconNew);
        colorSpinnerNew = findViewById(R.id.taskColorNew);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        TextView lblPageName = findViewById(R.id.lblPageName);
        ImageButton btnCards = findViewById(R.id.taskFlashcard);
        lblPageName.setText("Update");

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        btnMenu = findViewById(R.id.menubtn);

        btnCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updatetaskpage.this, ProdoReviewer.class);
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
                Intent intent = new Intent(updatetaskpage.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updatetaskpage.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if(intent != null){
            String taskId = intent.getStringExtra("taskId");
            String taskName = intent.getStringExtra("taskName");
            String taskIcon = intent.getStringExtra("taskIcon");
            String taskIconColor = intent.getStringExtra("taskIconColor");
            String taskDate = intent.getStringExtra("taskDate");
            String taskDescription = intent.getStringExtra("taskDescription");
            String taskListName = intent.getStringExtra("taskListName");

            // Populate the views with the task details
            taskTitleNew.setText(taskName);
            taskDescriptionNew.setText(taskDescription);
            taskListNew.setText(taskListName);
            taskDueNew.setText(taskDate);
            taskIconNew.setText(taskIcon);

            // Set up the colorSpinnerNew Spinner and select the appropriate item
            String[] colorOptions = {"Red", "Blue", "Green", "Purple"};
            ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorOptions);
            colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            colorSpinnerNew.setAdapter(colorAdapter);

            int colorIndex = Arrays.asList(colorOptions).indexOf(taskIconColor);
            colorSpinnerNew.setSelection(colorIndex);

        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the updated values from the input fields
                String updatedTaskTitle = taskTitleNew.getText().toString();
                String updatedTaskDescription = taskDescriptionNew.getText().toString();
                String updatedTaskList = taskListNew.getText().toString();
                String updatedTaskDue = taskDueNew.getText().toString();
                String updatedTaskIcon = taskIconNew.getText().toString();
                String updatedTaskIconColor = colorSpinnerNew.getSelectedItem().toString();
                String taskId = intent.getStringExtra("taskId");

                // Update the task in the database
                myDatabasehelper dbHelper = new myDatabasehelper(updatetaskpage.this);
                dbHelper.updateData(taskId, updatedTaskTitle, updatedTaskIcon, updatedTaskIconColor, updatedTaskDue, updatedTaskDescription, updatedTaskList);
                // Update the list in the database 2
                myDatabasehelper2 dbHelper2 = new myDatabasehelper2(updatetaskpage.this);
                dbHelper2.updateData(taskId,updatedTaskList,updatedTaskIcon,updatedTaskIconColor);
                // Show a toast message to indicate the update was successful
                Toast.makeText(updatetaskpage.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                // Finish the activity and return to the previous screen

                Intent intent = new Intent(updatetaskpage.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // Finish the activity and return to the previous screen


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the task ID from the intent
                String taskId = intent.getStringExtra("taskId");

                // Delete the task from the database
                myDatabasehelper dbHelper = new myDatabasehelper(updatetaskpage.this);
                dbHelper.deleteData(taskId);

                myDatabasehelper2 dbHelper2 = new myDatabasehelper2(updatetaskpage.this);
                dbHelper2.deleteData(taskId);
                // Show a toast message to indicate the deletion was successful
                Toast.makeText(updatetaskpage.this, "Task deleted successfully", Toast.LENGTH_SHORT).show();

                // Finish the activity and return to the previous screen
                    Intent intent = new Intent(updatetaskpage.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
            }
        });
        colorSpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedColor = adapterView.getSelectedItem().toString();
                int color = Color.parseColor(selectedColor);
                findViewById(R.id.taskColorNew).setBackgroundColor(color); // Replace R.id.myLinearLayout with the ID of your layout
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Set a default background color when no item is selected
                int defaultColor = Color.WHITE; // Set your desired default color
                findViewById(R.id.taskColorNew).setBackgroundColor(defaultColor); // Replace R.id.myLinearLayout with the ID of your layout
            }
        });
        taskDueNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        updatetaskpage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayOfMonth) {
                                String selectedDate = dayOfMonth + "/" + (monthofYear+1) + "/" + year;
                                taskDueNew.setText(selectedDate);
                            }
                        },
                        year,month,day
                );
                datePickerDialog.show();
            }
        });
        taskIconNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayAdapter<String> iconAdapter = new ArrayAdapter<String>(
                        updatetaskpage.this,
                        android.R.layout.simple_dropdown_item_1line,
                        new String[]{"Book", "Check", "Bell"}
                );

                new AlertDialog.Builder(updatetaskpage.this)
                        .setTitle("Select an Icon")
                        .setAdapter(iconAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String selectedIcon = iconAdapter.getItem(which);
                                Drawable iconDrawable = getIconDrawable(selectedIcon);
                                taskIconNew.setText(selectedIcon);
                                taskIconNew.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,iconDrawable,null);

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