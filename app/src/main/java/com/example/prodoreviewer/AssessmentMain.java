package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AssessmentMain extends AppCompatActivity {

    String UserEmail;
    Animation scaleup,scaledown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_main);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        Button btnAssess = findViewById(R.id.btnAssessStart);
        scaleup = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        setButtonTouchListener(btnAssess);

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