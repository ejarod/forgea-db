package com.example.prodoreviewer;


public class AssessmentControl {
    char world;          //E or I
    char information;    //S or N
    char decisions;      //T or F
    char structure;      //J or P
    String result = assessSkills();

    public String assessSkills() {
        String result;
        if(world=='A'){
            result = "E";
        } else {
            result = "I";
        }

        if(information =='A'){
            result += "S";
        } else {
            result += "N";
        }

        if(decisions =='A'){
            result += "T";
        } else {
            result += "F";
        }

        if(structure =='A'){
            result += "J";
        } else {
            result += "P";
        }

        return result;
    }

    public AssessmentControl(int[] world, int[] information, int[] decisions, int[]structure) {
        this.world = getTypeScore(world);
        this.information = getTypeScore(information);
        this.decisions = getTypeScore(decisions);
        this.structure = getTypeScore(structure);
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
        } else if (scoreA < scoreB) {
            return 'B';
        } else {
            return 'C';
        }
    }

    public static int getTypePercent(int[] answers) {
        int scoreA = 0;
        int scoreB = 0;

        for (int i = 0; i < answers.length; i++) {
            if (i+1 % 2 == 0) {
                scoreA += answers[i];
            } else {
                scoreB += answers[i];
            }
        }

        if (scoreA > scoreB) {
            return scoreA / answers.length;
        } else if (scoreA < scoreB) {
            return scoreB / answers.length;
        } else {
            return 50;
        }
    }
}
