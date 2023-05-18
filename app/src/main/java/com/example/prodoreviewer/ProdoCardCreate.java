package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProdoCardCreate extends AppCompatActivity {

    Button btnAddNewCard;
    EditText txtCardFront, txtCardBack;
    ImageButton btnBackButton,btnHome;
    TextView lblPageName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_card_create);

        Intent intent = getIntent();

        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Card Create");

        txtCardFront = (EditText) findViewById(R.id.txtCardFront);
        txtCardBack = (EditText) findViewById(R.id.txtCardBack);
        btnAddNewCard = (Button) findViewById(R.id.btnAddNewCard);


        btnAddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardName = txtCardFront.getText().toString();
                String topicName = intent.getStringExtra("topic");
                String cardContent = txtCardBack.getText().toString();

                MyDatabaseHelper myDB = new MyDatabaseHelper(ProdoCardCreate.this);
                myDB.addCard(cardName,"normal",cardContent,topicName);

                Intent intent = new Intent(ProdoCardCreate.this, Prodotopics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoCardCreate.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}