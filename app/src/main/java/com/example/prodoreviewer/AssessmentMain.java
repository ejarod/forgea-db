package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Ref;

public class AssessmentMain extends AppCompatActivity {

    String UserEmail;
    Animation scaleup,scaledown;
    ImageButton btnBack, btnHome, btnRef;
    Button btnAssess;
    DatabaseHelper db;
    TextView txtLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_main);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        db = new DatabaseHelper(this);

        btnAssess = findViewById(R.id.btnAssessStart);
        btnHome = findViewById(R.id.btnHome);
        btnBack = findViewById(R.id.btnBackButton);
        scaleup = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(this,R.anim.scale_down);
        txtLabel = findViewById(R.id.lblPageName);
        btnRef = findViewById(R.id.btnRef);

        setButtonTouchListener(btnAssess);

        Boolean check = db.hasAssessed(UserEmail);
        if(!check){
            btnHome.setActivated(false);
            btnHome.setVisibility(View.GONE);
            txtLabel.setVisibility(View.GONE);

            btnBack.setActivated(false);
            btnBack.setVisibility(View.GONE);
        } else {
            btnHome.setActivated(false);
            btnHome.setVisibility(View.GONE);
            txtLabel.setVisibility(View.VISIBLE);
            txtLabel.setText("Assessment");

            btnBack.setActivated(true);
            btnBack.setVisibility(View.VISIBLE);
        }
        btnAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentMain.this,AssessmentQuestions.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                finish();
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentMain.this, References.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void setButtonTouchListener(final Button button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    button.startAnimation(scaleup);
                } else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    button.startAnimation(scaledown);
                }
                return false;
            }
        });
    }
}