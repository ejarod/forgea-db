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

    String UserEmail, personalityCode = "";
    ImageButton btnHome, btnBack;
    TextView txtLabel, txtProgramRecommendation;

    DatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_paths_recommendations);

        db = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);
        txtProgramRecommendation = findViewById(R.id.txtProgramRecommendation);

        txtLabel.setText("Career Paths - Recommendations");

        Intent intent = getIntent();
        if (intent != null) {
            UserEmail = intent.getStringExtra("email");
            personalityCode = intent.getStringExtra("mbtiType"); // Retrieve personality code from intent
        }

        // Fetch the list of career paths from the database
        List<CareerPath> careerPaths = db.getAllCareerPaths(personalityCode);

        // Initialize RecyclerView
        //TextView txtPersonalityTrait = findViewById(R.id.txtPersonalityTrait);

        // Retrieve the user's personality traits from the database
        String personalityTraits = db.getPersonality(UserEmail);

        // Update the text of txtPersonalityTrait based on the retrieved traits
        //txtPersonalityTrait.setText(personalityTraits);

        // Update button texts based on personality trait letters
        Button btnWorld = findViewById(R.id.btnWorld2);
        Button btnInformation = findViewById(R.id.btnInformation2);
        Button btnDecision = findViewById(R.id.btnDecision2);
        Button btnStructure = findViewById(R.id.btnStructure2);

        btnWorld.setText(String.valueOf(personalityTraits.charAt(0)));
        btnInformation.setText(String.valueOf(personalityTraits.charAt(1)));
        btnDecision.setText(String.valueOf(personalityTraits.charAt(2)));
        btnStructure.setText(String.valueOf(personalityTraits.charAt(3)));

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

        // Dummy var for GWA
        double gwa = 1.5; // Replace with actual GWA logic

        if (gwa <= 2.0) {
            txtProgramRecommendation.setText("Information Technology");
        } else {
            txtProgramRecommendation.setText("Computer Science");
        }
    }
}
