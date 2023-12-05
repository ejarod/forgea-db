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

        if(information =='A'){
            result += "T";
        } else {
            result += "F";
        }

        if(information =='A'){
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
        int scoreA = 0;
        int scoreB = 0;

        for (int answer : answers) {
            if (answer < 4) {
                scoreA += 4-answer;
            } else if(answer > 4){
                scoreB += answer-4;
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

        for (int answer : answers) {
            if (answer < 4) {
                scoreA += answer;
            } else if(answer > 4){
                scoreB += answer;
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
