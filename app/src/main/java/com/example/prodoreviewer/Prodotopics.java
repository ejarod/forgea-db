package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Prodotopics extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> topic_id;
    ArrayList<String> topic_name;
    CustomAdapter customAdapter;
    ImageButton btnBackButton;
    TextView lblPageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotopics);

        btnBackButton = findViewById(R.id.btnBackButton);

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Topics");

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(Prodotopics.this);
        topic_id = new ArrayList<>();
        topic_name = new ArrayList<>();

        storeData();
        customAdapter = new CustomAdapter(Prodotopics.this,topic_id,topic_name);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Prodotopics.this));

    }

    void storeData() {
        Cursor cursor = myDB.readTopicData();
        if(cursor == null) {
            return;
        }
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"No Topics Created",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                topic_id.add(cursor.getString(0));
                topic_name.add(cursor.getString(1));
            }
        }
    }
}