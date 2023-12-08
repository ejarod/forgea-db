package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AssessmentMain extends AppCompatActivity {

    String UserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_main);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        Button btnAssess = findViewById(R.id.btnAssessStart);

        btnAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentMain.this,AssessmentQuestions.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
        });
    }
}