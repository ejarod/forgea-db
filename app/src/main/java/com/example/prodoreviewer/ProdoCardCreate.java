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
import android.view.Window;
import android.view.WindowManager;
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
                    txtCardFront.setError("Front text too long");
                    txtCardFront.requestFocus();
                    return;
                }

                if(cardContent.length() > 128) {
                    txtCardBack.setError("Content too long");
                    txtCardBack.requestFocus();
                    return;
                }

                if(cardName.isEmpty() || cardName.trim().isEmpty()) {
                    txtCardFront.setError("Must not be empty");
                    txtCardFront.requestFocus();
                    return;
                }

                MyDatabaseHelperCard myDB = new MyDatabaseHelperCard(ProdoCardCreate.this);
                if(myDB.cardExists(cardName)){
                    txtCardFront.setError("Already exists");
                    txtCardFront.requestFocus();
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