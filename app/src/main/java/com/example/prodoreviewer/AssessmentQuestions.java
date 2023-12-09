package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AssessmentQuestions extends AppCompatActivity {

    String[] worldQ = {
            "Do you enjoy large social gatherings?",
            "Do you prefer to work in a quiet environment?",
            "Are you comfortable being the center of attention?",
            "Do you prefer to work alone or in a team?",
            "Do you enjoy spending time alone?",
            "Are you comfortable speaking in front of a large group of people?",
            "Do you enjoy attending parties and social events?",
            "Do you prefer to spend time alone or in a group of friends?"
    };

    String[] informationQ = {
            "Do you prefer to work with facts and figures?",
            "Do you prefer to work with abstract concepts?",
            "Are you more comfortable with the present than the future?",
            "Do you prefer to work with the here-and-now rather than the big picture?",
            "Do you prefer to work with the 'what is' rather than the 'what could be'?",
            "Do you prefer to work with the standard and usual rather than the different and novel?",
            "Are you more focused on the 'here and now' rather than the future and global perspective?",
            "Do you prefer to work with facts and things rather than ideas and dreams?"
    };

    String[] decisionQ = {
            "Do you prefer to analyze and dissect situations?",
            "Do you prefer to empathize and understand others' feelings?",
            "Are you more focused on the logical and objective aspects of life?",
            "Do you prefer to consider the emotional and subjective aspects of life?",
            "Do you prefer to make decisions based on logical reasoning?",
            "Do you prefer to make decisions based on your emotions and feelings?",
            "Are you more comfortable with facts and data?",
            "Do you prefer to make decisions based on your intuition and feelings?"
    };

    String[] structureQ = {
            "Do you prefer to be organized and structured?",
            "Do you prefer to be flexible and adaptable?",
            "Do you prefer to plan and schedule?",
            "Do you prefer to be unplanned and spontaneous?",
            "Do you prefer to be regulated and structured?",
            "Do you prefer to be easygoing and live and let live?",
            "Do you prefer to prepare and plan ahead?",
            "Do you prefer to go with the flow and adapt as you go?"
    };

    Button btnA,btnB,btnC,btnD,btnE,btnF,btnG;
    Animation scaleup,scaledown;
    TextView question;

    int[] world = new int[worldQ.length];
    int[] information = new int[informationQ.length];
    int[] decision = new int[decisionQ.length];
    int[] structure = new int[structureQ.length];

    int qIndex = 0;

    int first = worldQ.length;
    int second = first + informationQ.length;
    int third = second + decisionQ.length;
    int fourth = third + structureQ.length;

    String UserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_questions);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);
        btnE = findViewById(R.id.btnAnswerE);
        btnF = findViewById(R.id.btnAnswerF);
        btnG = findViewById(R.id.btnAnswerG);
        question = findViewById(R.id.txtAssessmentQuestion);

        scaleup = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        setButtonTouchListener(btnA);
        setButtonTouchListener(btnB);
        setButtonTouchListener(btnC);
        setButtonTouchListener(btnD);
        setButtonTouchListener(btnE);
        setButtonTouchListener(btnF);
        setButtonTouchListener(btnG);

        proceedAssessment();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 7;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 7;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 7;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 7;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 6;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 6;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 6;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 6;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 5;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 5;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 5;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 5;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 4;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 4;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 4;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 4;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 3;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 3;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 3;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 3;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 2;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 2;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 2;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 2;
                }
                qIndex++;
                proceedAssessment();
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qIndex < first) {
                    world[qIndex] = 1;
                }
                if(qIndex >= first && qIndex < second) {
                    information[qIndex-first] = 1;
                }
                if(qIndex >= second && qIndex < third) {
                    decision[qIndex-second] = 1;
                }
                if(qIndex >= third && qIndex < fourth) {
                    structure[qIndex-third] = 1;
                }
                qIndex++;
                proceedAssessment();
            }
        });

    }

    private void proceedAssessment() {
        if(qIndex < first) {
            question.setText(worldQ[qIndex]);
        }
        if(qIndex >= first && qIndex < second) {
            question.setText(informationQ[qIndex-first]);
        }
        if(qIndex >= second && qIndex < third) {
            question.setText(decisionQ[qIndex-second]);
        }
        if(qIndex >= third && qIndex < fourth) {
            question.setText(structureQ[qIndex-third]);
        }

        if(qIndex >= fourth) {

            DatabaseHelper db = new DatabaseHelper(this);
            AssessmentControl control = new AssessmentControl(world,information,decision,structure);
            Boolean insert = db.insertPersonalityData(control.result,UserEmail,control.percentW,control.percentI,control.percentD,control.percentS);
            if(insert) {
                Intent intent = new Intent(AssessmentQuestions.this,AssessmentQuestions.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
            }
            /*System.out.print("World: ");
            System.out.print(world[0]);
            for(int i = 1; i < fourth; i++) {
                if(i < first) {
                    System.out.print(", ");
                    System.out.print(world[i]);
                }
            }
            System.out.println();*/
        }
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