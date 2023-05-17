package com.example.prodoreviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProdoReviewer extends AppCompatActivity {

    Button btnStudy;
    Button btnTopic;
    Button btnQuick;
    Button btnSettings;
    ImageButton btnBackButton;
    TextView lblPageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodoreviewer);

        btnStudy = (Button) findViewById(R.id.btnStudy);
        btnTopic = (Button) findViewById(R.id.btnTopic);
        btnQuick = (Button) findViewById(R.id.btnQuick);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Prodo Reviewer");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this,ProdoCreate.class);
                startActivity(intent);
            }
        });

        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this, Prodotopics.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this, ProdoSettings.class);
                startActivity(intent);
            }
        });

        btnQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this, ProdoCardDisplay.class);
                intent.putExtra("topic","Quick");
                startActivity(intent);
            }
        });
    }
}