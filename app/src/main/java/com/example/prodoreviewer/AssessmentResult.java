package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssessmentResult extends AppCompatActivity {

    Button btnWorld,btnInformation,btnDecision,btnStructure,btnContinue;
    String UserEmail, world, information, decision, structure;
    int percentW, percentI, percentD, percentS;
    String personalityCode;
    TextView btnSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_result);

        btnWorld = findViewById(R.id.btnWorld);
        btnInformation = findViewById(R.id.btnInformation);
        btnDecision = findViewById(R.id.btnDecision);
        btnStructure = findViewById(R.id.btnStructure);
        btnContinue = findViewById(R.id.btnContinue);
        btnSkip = findViewById(R.id.textSkip);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
            world = intent.getStringExtra("world");
            information = intent.getStringExtra("information");
            decision = intent.getStringExtra("decision");
            structure = intent.getStringExtra("structure");

            percentW = intent.getIntExtra("percentW",0);
            percentI = intent.getIntExtra("percentI",0);
            percentD = intent.getIntExtra("percentD",0);
            percentS = intent.getIntExtra("percentS",0);

            personalityCode = world + information + decision + structure;
        }

        btnWorld.setText(world);
        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentResult.this,AssessmentResultDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                intent.putExtra("world", world);
                intent.putExtra("information", information);
                intent.putExtra("decision", decision);
                intent.putExtra("structure", structure);

                intent.putExtra("percentW", percentW);
                intent.putExtra("percentI", percentI);
                intent.putExtra("percentD", percentD);
                intent.putExtra("percentS", percentS);
                finish();
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentResult.this,CareerPathsRecommendations.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                intent.putExtra("mbtiType", personalityCode);

                intent.putExtra("percentW", percentW);
                intent.putExtra("percentI", percentI);
                intent.putExtra("percentD", percentD);
                intent.putExtra("percentS", percentS);

                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}