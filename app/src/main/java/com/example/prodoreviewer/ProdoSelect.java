package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProdoSelect extends AppCompatActivity {

    Button btnAddCard;
    Button btnStart;
    ImageButton btnBackButton;
    TextView lblPageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_select);

        btnAddCard = findViewById(R.id.btnAddCard);
        btnStart = findViewById(R.id.btnStart);
        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("topic");
        lblPageName.setText(topicName);

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoSelect.this,ProdoCardCreate.class);
                intent.putExtra("topic",topicName);
                startActivity(intent);

            }
        });

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}