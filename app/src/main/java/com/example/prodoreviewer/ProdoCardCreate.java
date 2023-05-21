package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeCardCreate);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);

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

                if(cardName.length() > 38) {
                    Toast.makeText(ProdoCardCreate.this, "Front text too long!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cardContent.length() > 128) {
                    Toast.makeText(ProdoCardCreate.this, "Back text too long!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cardName.isEmpty() || cardName.trim().isEmpty()) {
                    Toast.makeText(ProdoCardCreate.this, "Enter a card name", Toast.LENGTH_SHORT).show();
                    return;
                }

                MyDatabaseHelper myDB = new MyDatabaseHelper(ProdoCardCreate.this);
                if(myDB.cardExists(cardName)){
                    Toast.makeText(ProdoCardCreate.this, "Card already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                myDB.addCard(cardName,"normal",cardContent,topicName);

                Intent intent = new Intent(ProdoCardCreate.this, Prodotopics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoCardCreate.this, Prodotopics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
    }
}