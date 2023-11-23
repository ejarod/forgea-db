package com.example.prodoreviewer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class ProdoReviewer extends AppCompatActivity {

    Button btnStudy;
    Button btnTopic;
    Button btnQuick;
    Button btnSettings;
    ImageButton btnBackButton,btnHome;
    TextView lblPageName;
    private MyDatabaseHelperCard db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prodoreviewer);

        btnStudy = findViewById(R.id.btnStudy);
        btnTopic = findViewById(R.id.btnTopic);
        btnQuick = findViewById(R.id.btnQuick);
        btnSettings = findViewById(R.id.btnSettings);
        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        lblPageName = findViewById(R.id.lblPageName);
        db = new MyDatabaseHelperCard(ProdoReviewer.this);

        lblPageName.setText("Resource Management");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoReviewer.this,ProdoCreate.class);
                //startActivity(intent);

                View sharedView = findViewById(R.id.include); // The shared element view
                String transitionName = getString(R.string.transition_image); // The transition name
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ProdoReviewer.this, sharedView, transitionName);
                startActivity(intent, options.toBundle());
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
                //startActivity(intent);

                View sharedView = findViewById(R.id.include); // The shared element view
                String transitionName = getString(R.string.transition_image); // The transition name
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ProdoReviewer.this, sharedView, transitionName);
                startActivity(intent, options.toBundle());
            }
        });

        btnQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.cardNo()==0) {
                    Toast.makeText(ProdoReviewer.this, "Cards must be created first", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ProdoReviewer.this, ProdoCardDisplay.class);
                intent.putExtra("topic"," ");
                //startActivity(intent);

                View sharedView = findViewById(R.id.include); // The shared element view
                String transitionName = getString(R.string.transition_image); // The transition name
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ProdoReviewer.this, sharedView, transitionName);
                startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
    }
}