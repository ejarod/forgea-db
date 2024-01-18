package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class References extends AppCompatActivity {


    String UserEmail;
    ImageButton btnHome, btnBack;
    TextView txtLabel, txtMBTI, txt1, txt2, txt3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setVisibility(View.INVISIBLE);
        txtLabel = findViewById(R.id.lblPageName);
        txtMBTI = findViewById(R.id.txtOverview2);
        txt1 = findViewById(R.id.txResearchs1);
        txt2 = findViewById(R.id.txResearchs2);
        txt3 = findViewById(R.id.txResearchs3);

        txtLabel.setText("References");

        // Retrieve career name and description from Intent Extras
        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtMBTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String url = "https://www.myersbriggs.org/my-mbti-personality-type/myers-briggs-overview/";

                // Create an Intent to open a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if there's a browser available to handle the Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where no browser is available
                    Toast.makeText(getApplicationContext(), "No browser app found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String url = "https://academicjournals.org/journal/AJBM/article-full-text-pdf/D46694524797";

                // Create an Intent to open a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if there's a browser available to handle the Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where no browser is available
                    Toast.makeText(getApplicationContext(), "No browser app found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String url = "https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6671867/";

                // Create an Intent to open a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if there's a browser available to handle the Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where no browser is available
                    Toast.makeText(getApplicationContext(), "No browser app found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String url = "https://www.sciencedaily.com/releases/2021/12/211213181545.htm";

                // Create an Intent to open a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if there's a browser available to handle the Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where no browser is available
                    Toast.makeText(getApplicationContext(), "No browser app found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}