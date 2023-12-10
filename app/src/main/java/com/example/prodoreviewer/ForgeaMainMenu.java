package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ForgeaMainMenu extends AppCompatActivity {

    String UserEmail;
    String UserPersonality;
    DatabaseHelper db;

    ImageButton btnAssessmentMenu, btnProfileMenu, btnCareerPathsMenu, btnPersonalityTraitsMenu;
    TextView txtAssessmentMenu, txtProfileMenu, txtCareerPathsMenu, txtPersonalityTraitsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgea_main_menu);

        db = new DatabaseHelper(this);

        btnAssessmentMenu = findViewById(R.id.btnAssessment);
        btnProfileMenu = findViewById(R.id.btnProfile);
        btnCareerPathsMenu = findViewById(R.id.btnCareerPaths);
        btnPersonalityTraitsMenu = findViewById(R.id.btnPersonalityTraits);

        txtAssessmentMenu = findViewById(R.id.txtAssessment);
        txtProfileMenu = findViewById(R.id.txtProfile);
        txtCareerPathsMenu = findViewById(R.id.txtCareerPaths);
        txtPersonalityTraitsMenu = findViewById(R.id.txtPersonalityTraits);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        btnAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

        txtAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

        btnProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

        txtProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });

    }
}