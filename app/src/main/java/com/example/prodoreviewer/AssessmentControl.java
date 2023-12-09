package com.example.prodoreviewer;


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

    public AssessmentControl(int[] world, int[] information, int[] decisions, int[]structure) {
        char w = getTypeScore(world);
        char i = getTypeScore(information);
        char d = getTypeScore(decisions);
        char s = getTypeScore(structure);

        this.percentW = getTypePercent(world);
        this.percentI = getTypePercent(information);
        this.percentD = getTypePercent(decisions);
        this.percentS = getTypePercent(structure);

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

    public static char getTypeScore(int[] answers) {
        int scoreA = 0; //even questions == Extrovert
        int scoreB = 0; //odd questions == Introvert

        for (int i = 0; i < answers.length; i++) {
            if (i+1 % 2 == 0) {
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

    public static int getTypePercent(int[] answers) {
        int scoreA = 0;
        int scoreB = 0;
        float percent;

        for (int i = 0; i < answers.length; i++) {
            if (i+1 % 2 == 0) {
                scoreA += answers[i];
            } else {
                scoreB += answers[i];
            }
        }

        if (scoreA > scoreB) {
            percent = scoreA / scoreA+scoreB;
        } else if (scoreA < scoreB) {
            percent = scoreB / scoreA+scoreB;
        } else {
            scoreB += 3;
            percent = scoreB / scoreA+scoreB;
        }

        percent *= 100;

        return (int)percent;
    }
}
