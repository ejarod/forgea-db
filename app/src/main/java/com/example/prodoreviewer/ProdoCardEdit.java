package com.example.prodoreviewer;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class ProdoCardEdit extends AppCompatActivity {

    Button btnEditCard;
    EditText txtEditFront, txtEditBack;
    ImageButton btnBackButton,btnHome;
    TextView lblPageName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prodo_card_edit);



        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeEdit);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);

        MyDatabaseHelper myDB = new MyDatabaseHelper(ProdoCardEdit.this);

        Intent intent = getIntent();

        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Card Edit");

        txtEditFront = (EditText) findViewById(R.id.txtEditFront);
        txtEditBack = (EditText) findViewById(R.id.txtEditBack);
        btnEditCard = (Button) findViewById(R.id.btnEditCard);

        Card card = myDB.getCard(intent.getStringExtra("front"));

        txtEditFront.setText(card.getName());
        txtEditBack.setText(card.getContent());


        btnEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardName = intent.getStringExtra("front");
                String newName = txtEditFront.getText().toString();
                String cardContent = txtEditBack.getText().toString();

                if(newName.isEmpty() || newName.trim().isEmpty()) {
                    txtEditFront.setError("Must not be empty");
                    txtEditFront.requestFocus();
                    return;
                }

                if(cardName.length() > 38) {
                    txtEditFront.setError("Front text too long");
                    txtEditFront.requestFocus();
                    return;
                }

                if(cardContent.length() > 128) {
                    txtEditBack.setError("Content change too long");
                    txtEditBack.requestFocus();
                    return;
                }

                if(myDB.cardExists(newName) && !cardName.equals(newName)){
                    txtEditFront.setError("Already exists");
                    txtEditFront.requestFocus();
                    return;
                }

                myDB.editCard(cardName,cardContent,newName);

                Intent intent = new Intent(ProdoCardEdit.this, Prodotopics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoCardEdit.this, ProdoCards.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("topic",card.getTopic());
                startActivity(intent);

                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoCardEdit.this, ProdoReviewer.class);
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