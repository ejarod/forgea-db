package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssessmentResultDetails extends AppCompatActivity {

    Button btnWorld,btnInformation,btnDecision,btnStructure,btnContinue;
    String UserEmail, world, information, decision, structure;
    int percentW, percentI, percentD, percentS;
    TextView trait, traitPercent, traitDetails;

    int index = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_result_details);
        btnContinue = findViewById(R.id.btnContinue2);
        btnWorld = findViewById(R.id.btnWorld2);
        btnInformation = findViewById(R.id.btnInformation2);
        btnDecision = findViewById(R.id.btnDecision2);
        btnStructure = findViewById(R.id.btnStructure2);
        trait = findViewById(R.id.txtTrait);
        traitPercent = findViewById(R.id.txtTraitPercentage);
        traitDetails = findViewById(R.id.txtTraitDetails);

        String[] worldDescriptions = {
                "Extroverts are outgoing, sociable individuals who gain energy from interacting with others." + "\n\n" + "They often enjoy a variety of activities, seek stimulation, and are comfortable in group settings. Extroverts tend to think out loud and process information externally.",
                "Introverts are reserved, reflective individuals who recharge by spending time alone." + "\n\n" + "They prefer smaller, more intimate gatherings and may need quiet time for introspection. Introverts often think deeply before speaking and may process information internally before expressing their thoughts."
        };
        String[] informationDescriptions = {
                "Sensors focus on concrete facts, details, and the present moment." + "\n\n" + "They are practical and rely on direct experiences to understand the world around them. Sensors are often realistic and grounded, paying attention to the here and now.",
                "Intuitives are more interested in exploring possibilities, ideas, and future implications." + "\n\n" + "They enjoy thinking about the big picture and are comfortable with abstract concepts. Intuitives often seek patterns, connections, and innovative solutions beyond immediate, observable data."
        };
        String[] decisionDescriptions = {
                "Thinkers make decisions based on logic, objective analysis, and impersonal criteria." + "\n\n" + "They value fairness and consistency in decision-making, often prioritizing truth over tact in communication. Thinkers may approach problems with a focus on efficiency and effectiveness.",
                "Feelers make decisions based on values, personal beliefs, and the impact on people." + "\n\n" + "They are often empathetic, considerate of others' feelings, and seek harmony in their relationships. Feelers may prioritize the human aspect in decision-making, valuing compassion and understanding."
        };
        String[] structureDescriptions = {
                "Judgers prefer structure, planning, and organization." + "\n\n" + "They like to have clear goals, deadlines, and closure in their decisions. Judgers are often decisive, focused, and organized, valuing order and predictability.",
                "Perceivers are more flexible and adaptable." + "\n\n" + "They enjoy spontaneity, prefer to keep their options open, and may be more comfortable with uncertainty. Perceivers often embrace change, staying open to new information and possibilities, and enjoy the journey as much as the destination."
        };


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
        }

        btnWorld.setText(world);
        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);

        traitPercent.setText(String.valueOf(percentW) + "%");
        traitPercent.setTextColor(ContextCompat.getColor(this, R.color.world));
        if(world.equals("E")){
            traitDetails.setText(worldDescriptions[0]);
            trait.setText("Extrovert");
            btnWorld.setText("E");
        } else {
            traitDetails.setText(worldDescriptions[1]);
            trait.setText("Introvert");
            btnWorld.setText("I");
        }

        btnWorld.setTextColor(ContextCompat.getColor(this, R.color.world));
        btnWorld.setBackgroundResource(R.drawable.personality_world_selected);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;

                if(index==2){
                    btnWorld.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.text));
                    btnInformation.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.information));
                    btnWorld.setBackgroundResource(R.drawable.personality_world);
                    btnInformation.setBackgroundResource(R.drawable.personality_information_selected);
                    trait.setText(information);
                    trait.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.information));
                    traitPercent.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.information));
                    traitPercent.setText(String.valueOf(percentI) + "%");
                    if(information.equals("S")){
                        traitDetails.setText(informationDescriptions[0]);
                        trait.setText("Sensing");
                        btnInformation.setText("S");
                    } else {
                        traitDetails.setText(informationDescriptions[1]);
                        trait.setText("Intuitive");
                        btnInformation.setText("N");
                    }
                }

                if(index==3){
                    btnInformation.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.text));
                    btnDecision.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.decision));
                    btnInformation.setBackgroundResource(R.drawable.personality_information);
                    btnDecision.setBackgroundResource(R.drawable.personality_decision_selected);
                    trait.setText(decision);
                    trait.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.decision));
                    traitPercent.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.decision));
                    traitPercent.setText(String.valueOf(percentD) + "%");
                    if(decision.equals("T")){
                        traitDetails.setText(decisionDescriptions[0]);
                        trait.setText("Thinking");
                        btnDecision.setText("T");
                    } else {
                        traitDetails.setText(decisionDescriptions[1]);
                        trait.setText("Feeling");
                        btnDecision.setText("F");
                    }
                }

                if(index==4){
                    btnDecision.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.text));
                    btnStructure.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.structure));
                    btnDecision.setBackgroundResource(R.drawable.personality_decision);
                    btnStructure.setBackgroundResource(R.drawable.personality_structure_selected);
                    trait.setText(structure);
                    trait.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.structure));
                    traitPercent.setTextColor(ContextCompat.getColor(AssessmentResultDetails.this, R.color.structure));
                    traitPercent.setText(String.valueOf(percentS) + "%");
                    if(structure.equals("J")){
                        traitDetails.setText(structureDescriptions[0]);
                        trait.setText("Judging");
                        btnStructure.setText("J");
                    } else {
                        traitDetails.setText(structureDescriptions[1]);
                        trait.setText("Perceiving");
                        btnStructure.setText("P");
                    }
                }

                if(index>=5) {
                    DatabaseHelper db = new DatabaseHelper(AssessmentResultDetails.this);
                    Boolean insert = db.insertPersonalityData(UserEmail,world+information+decision+structure,percentW,percentI,percentD,percentS);
                    if(insert) {
                        Intent intent = new Intent(AssessmentResultDetails.this,ForgeaMainMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("email", UserEmail);
                        startActivity(intent);
                    }

                }
            }
        });

    }
}