package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonalityDetails extends AppCompatActivity {

    String UserEmail, worldI, informationS, decisionT, structureJ, worldE, informationN, decisionF, structureP = "";
    ImageButton btnHome, btnBack;
    TextView txtLabel;

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

    Button btnWorldI,btnInformationS,btnDecisionT,btnStructureJ,btnWorldE,btnInformationN,btnDecisionF,btnStructureP;
    TextView trait, traitDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_details);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);
        btnHome.setVisibility(View.INVISIBLE);
        txtLabel.setText("Personality Details");

        btnWorldI = findViewById(R.id.btnWorldI);
        btnInformationS = findViewById(R.id.btnInformationS);
        btnDecisionT = findViewById(R.id.btnDecisionT);
        btnStructureJ = findViewById(R.id.btnStructureJ);
        btnWorldE = findViewById(R.id.btnWorldE);
        btnInformationN = findViewById(R.id.btnInformationN);
        btnDecisionF = findViewById(R.id.btnDecisionF);
        btnStructureP = findViewById(R.id.btnStructureP);
        traitDetails = findViewById(R.id.txtTraitDetailsProfile);
        trait = findViewById(R.id.txtTraitProfile);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        DatabaseHelper db = new DatabaseHelper(this);
        worldI = "I";
        informationS = "S";
        decisionT = "T";
        structureJ = "J";
        worldE = "E";
        informationN = "N";
        decisionF = "F";
        structureP = "P";

        btnWorldI.setText(worldI);
        btnWorldE.setText(worldE);
        btnInformationS.setText(informationS);
        btnInformationN.setText(informationN);
        btnDecisionT.setText(decisionT);
        btnDecisionF.setText(decisionF);
        btnStructureJ.setText(structureJ);
        btnStructureP.setText(structureP);

        trait.setText("");
        traitDetails.setText("Please select a trait to view its description.\n\nThe Myers-Briggs Type Indicator (MBTI) is a popular personality assessment tool that categorizes individuals into 16 distinct personality types. This is achieved through four dichotomies: Extraversion (E) or Introversion (I), Sensing (S) or Intuition (N), Thinking (T) or Feeling (F), and Judging (J) or Perceiving (P). Whether you're outgoing or reserved, practical or imaginative, logical or empathetic, structured or flexible, the MBTI provides a concise framework to understand and appreciate your unique personality, fostering self-awareness and interpersonal insights.");

        btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnWorldI.setBackgroundResource(R.drawable.personality_world);

        btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnInformationS.setBackgroundResource(R.drawable.personality_information);

        btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnDecisionT.setBackgroundResource(R.drawable.personality_decision);

        btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnStructureJ.setBackgroundResource(R.drawable.personality_structure);

        btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnWorldE.setBackgroundResource(R.drawable.personality_world);

        btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnInformationN.setBackgroundResource(R.drawable.personality_information);

        btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnDecisionF.setBackgroundResource(R.drawable.personality_decision);

        btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
        btnStructureP.setBackgroundResource(R.drawable.personality_structure);
        
        btnWorldI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.world));
                btnWorldI.setBackgroundResource(R.drawable.personality_world_selected);

                trait.setText("Introvert");

                traitDetails.setText(worldDescriptions[0]);
            }
        });

        btnWorldE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.world));
                btnWorldE.setBackgroundResource(R.drawable.personality_world_selected);

                trait.setText("Extrovert");

                traitDetails.setText(worldDescriptions[1]);
            }
        });

        btnInformationS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.information));
                btnInformationS.setBackgroundResource(R.drawable.personality_information_selected);

                trait.setText("Sensing");

                traitDetails.setText(informationDescriptions[0]);
            }
        });

        btnInformationN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.information));
                btnInformationN.setBackgroundResource(R.drawable.personality_information_selected);

                trait.setText("Intuitive");

                traitDetails.setText(informationDescriptions[1]);
            }
        });

        btnDecisionT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.decision));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision_selected);

                trait.setText("Thinking");

                traitDetails.setText(decisionDescriptions[0]);
            }
        });

        btnDecisionF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.decision));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision_selected);

                trait.setText("Feeling");

                traitDetails.setText(decisionDescriptions[1]);
            }
        });

        btnStructureJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.structure));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure_selected);

                trait.setText("Judging");

                traitDetails.setText(structureDescriptions[0]);
            }
        });

        btnStructureP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                btnInformationS.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                btnDecisionT.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                btnStructureJ.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(PersonalityDetails.this, R.color.structure));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure_selected);

                trait.setText("Perceiving");

                traitDetails.setText(structureDescriptions[1]);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(PersonalityDetails.this, ForgeaMainMenu.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                finish();
            }
        });
    }
}