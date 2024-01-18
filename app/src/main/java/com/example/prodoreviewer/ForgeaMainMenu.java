package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ForgeaMainMenu extends AppCompatActivity {

    String UserEmail, world, information, decision, structure = "";
    String UserPersonality;
    DatabaseHelper db;

    ImageButton btnAssessmentMenu, btnProfileMenu, btnCareerPathsMenu, btnPersonalityTraitsMenu, btnBack, btnHome;
    TextView txtLabel, txtAssessmentMenu, txtProfileMenu, txtCareerPathsMenu, txtPersonalityTraitsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgea_main_menu);

        btnAssessmentMenu = findViewById(R.id.btnAssessment);
        btnProfileMenu = findViewById(R.id.btnProfile);
        btnCareerPathsMenu = findViewById(R.id.btnCareerPaths);
        btnPersonalityTraitsMenu = findViewById(R.id.btnPersonalityTraits);
        btnBack = findViewById(R.id.btnBackButton);
        btnBack.setVisibility(View.INVISIBLE);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setImageResource(R.drawable.icon_info);
        btnHome.setColorFilter(getResources().getColor(android.R.color.white));

        txtLabel = findViewById(R.id.lblPageName);
        txtAssessmentMenu = findViewById(R.id.txtAssessment);
        txtProfileMenu = findViewById(R.id.txtProfile);
        txtCareerPathsMenu = findViewById(R.id.txtCareerPaths);
        txtPersonalityTraitsMenu = findViewById(R.id.txtPersonalityTraits);

        txtLabel.setText("Home");

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        db = new DatabaseHelper(this);

        world = "" + (db.getPersonality(UserEmail).charAt(0));
        information = "" + (db.getPersonality(UserEmail).charAt(1));
        decision = "" + (db.getPersonality(UserEmail).charAt(2));
        structure = "" + (db.getPersonality(UserEmail).charAt(3));

        String personalityCode = world + information + decision + structure;

        // Fetch the list of career paths from the database
        List<CareerPath> careerPaths = db.getAllCareerPaths(personalityCode);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.tasklistmenu2);

        // Create and set up the adapter with the list of career paths and the database helper
        CareerPathAdapter adapter = new CareerPathAdapter(careerPaths, db, UserEmail);
        recyclerView.setAdapter(adapter);

        // Set a layout manager (e.g., LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnCareerPathsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,CareerPathsDescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtCareerPathsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,CareerPathsDescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnPersonalityTraitsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,PersonalityDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtPersonalityTraitsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,PersonalityDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,References.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

//    @Override
//    protected void onDestroy() {
//        // Close the database helper when the activity is destroyed
//        if (db != null) {
//            db.close();
//        }
//        super.onDestroy();
//    }
}