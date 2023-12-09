package com.example.prodoreviewer;


import android.util.Log;

public class AssessmentControl {
    String world;          //E or I
    int percentW;
    String information;    //S or N
    int percentI;
    String decisions;      //T or F
    int percentD;
    String structure;      //J or P
    int percentS;
    String result = assessSkills();

    public String assessSkills() {
        String result;
        result = world + information + decisions + structure;
        return result;
    }

    public AssessmentControl(int[] worldA, int[] informationA, int[] decisionsA, int[]structureA) {
        char w = getTypeScore(worldA);
        char i = getTypeScore(informationA);
        char d = getTypeScore(decisionsA);
        char s = getTypeScore(structureA);

        this.percentW = getTypePercent(worldA);
        this.percentI = getTypePercent(informationA);
        this.percentD = getTypePercent(decisionsA);
        this.percentS = getTypePercent(structureA);

        if(w=='A'){
            this.world = "E";
        } else {
            this.world = "I";
        }

        if(i =='A'){
            this.information = "S";
        } else {
            this.information = "N";
        }

        if(d =='A'){
            this.decisions = "T";
        } else {
            this.decisions = "F";
        }

        if(s =='A'){
            this.structure = "J";
        } else {
            this.structure = "P";
        }
    }

    public char getTypeScore(int[] answers) {
        int scoreA = 0;
        int scoreB = 0;

        for (int i = 0; i < answers.length; i++) {
            //Log.d(String.valueOf(i) + " <- i+1", String.valueOf(i+1));
            if (i % 2 == 0) {
                scoreA += answers[i];
            } else {
                scoreB += answers[i];
            }
        }


        if (scoreA > scoreB) {
            return 'A';
        } else {
            return 'B';
        }
    }

    public int getTypePercent(int[] answers) {
        int scoreA = 0;
        int scoreB = 0;

        for (int i = 0; i < answers.length; i++) {
            if (i % 2 == 0) {
                scoreA += answers[i];
            } else {
                scoreB += answers[i];
            }
        }

        int totalScore = scoreA + scoreB;
        float percent;

        if (scoreA > scoreB) {
            percent = (float) scoreA / totalScore;
        } else if (scoreA < scoreB) {
            percent = (float) scoreB / totalScore;
        } else {
            scoreB += 3;
            percent = (float) scoreB / totalScore;
        }

        percent *= 100;
        return (int)percent;

    }
}
