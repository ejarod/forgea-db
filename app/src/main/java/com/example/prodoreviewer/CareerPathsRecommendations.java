package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CareerPathsRecommendations extends AppCompatActivity {


    String UserEmail, world, information, decision, structure = "";
    ImageButton btnHome, btnBack;
    TextView txtLabel;

    Button btnWorld,btnInformation,btnDecision,btnStructure,btnContinue;
    TextView trait,traitDetails;
    DatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_paths_recommendations);

        db = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBackButton);
        btnContinue = findViewById(R.id.btnContinue2);
        btnWorld = findViewById(R.id.btnWorld2);
        btnInformation = findViewById(R.id.btnInformation2);
        btnDecision = findViewById(R.id.btnDecision2);
        btnStructure = findViewById(R.id.btnStructure2);
        trait = findViewById(R.id.txtCareerName);
        traitDetails = findViewById(R.id.txtTraitDetails);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);

        txtLabel.setText("Career Paths - Recommendations");

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }
        world = "" + (db.getPersonality(UserEmail).charAt(0));
        information = "" + (db.getPersonality(UserEmail).charAt(1));
        decision = "" + (db.getPersonality(UserEmail).charAt(2));
        structure = "" + (db.getPersonality(UserEmail).charAt(3));

        btnWorld.setText(world);
        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareerPathsRecommendations.this, ForgeaMainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}