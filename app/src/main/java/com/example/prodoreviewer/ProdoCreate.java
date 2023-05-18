package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ProdoCreate extends AppCompatActivity {

    Button btnAdd;
    EditText txtTopic;
    ImageButton btnBackButton,btnHome;
    TextView lblPageName;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_create);

        txtTopic = (EditText) findViewById(R.id.txtTopic);
        btnAdd = (Button) findViewById(R.id.btnAddNewCard);
        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Create Topic");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoCreate.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = txtTopic.getText().toString();

                MyDatabaseHelper myDB = new MyDatabaseHelper(ProdoCreate.this);
                myDB.addTopic(topicName);


                finish();
            }
        });
    }
}