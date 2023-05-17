package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProdoCards extends AppCompatActivity {
    ImageButton btnBackButton;
    TextView lblPageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_cards);
        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Flash Cards");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}