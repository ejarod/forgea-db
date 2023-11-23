package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calendar extends AppCompatActivity implements calenderAdapter.OnItemListener{
    private TextView monthYearText, lblPageName;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        ImageButton backBtn = findViewById(R.id.btnBackButton);
        ImageButton btnHome = findViewById(R.id.btnHome);

        lblPageName = findViewById(R.id.lblPageName);
        lblPageName.setText("Calendar");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        initWidgets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = LocalDate.now();
        }
        setMonthView();
    }
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarview);
        monthYearText = findViewById(R.id.monthyearTV);

    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        calenderAdapter calenderAdapter = new calenderAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calenderAdapter);
    }


    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            yearMonth = YearMonth.from(date);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int daysInMonth = yearMonth.lengthOfMonth();
            LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
            for(int i = 1; i <= 42; i++){
                if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                    daysInMonthArray.add("");
                }else{
                    daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                }
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(formatter);
        }
        return null;
    }
    public void previousMonthAction(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusMonths(1);
        }
        setMonthView();
    }
    public void nextMonthAction(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.plusMonths(1);
        }
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}