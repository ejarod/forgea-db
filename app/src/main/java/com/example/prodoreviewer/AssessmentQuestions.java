package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_questions);


    }
}