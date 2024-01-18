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

import java.util.ArrayList;
import java.util.List;

public class CareerPathsRecommendationsPage extends AppCompatActivity {


    String UserEmail, world, information, decision, structure = "";
    String careerName, careerDescription;
    ImageButton btnHome, btnBack;
    DatabaseHelper db;
    Button btnWorld,btnInformation,btnDecision,btnStructure;
    TextView txtLabel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_paths_recommendations_page);

        db = new DatabaseHelper(this);

        btnWorld = findViewById(R.id.btnWorld2);
        btnInformation = findViewById(R.id.btnInformation2);
        btnDecision = findViewById(R.id.btnDecision2);
        btnStructure = findViewById(R.id.btnStructure2);
        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);

        txtLabel.setText("Career Paths - Recommendations");

        // Retrieve career name and description from Intent Extras
        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
            careerName = intent.getStringExtra("careerName");
        }

        world = "" + (db.getPersonality(UserEmail).charAt(0));
        information = "" + (db.getPersonality(UserEmail).charAt(1));
        decision = "" + (db.getPersonality(UserEmail).charAt(2));
        structure = "" + (db.getPersonality(UserEmail).charAt(3));

        btnWorld.setText(world);
        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);

        // Use the retrieved data to set content in your layout
        TextView nameTextView = findViewById(R.id.txtCareerName);
        TextView descriptionTextView = findViewById(R.id.txtCareerDesc);

        nameTextView.setText(careerName);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        careerDescription = dbHelper.getCareerDetails(careerName);
        descriptionTextView.setText(careerDescription);

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
                Intent intent = new Intent(CareerPathsRecommendationsPage.this, ForgeaMainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                finish();
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}