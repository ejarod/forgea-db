package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CareerDetails extends AppCompatActivity {

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);
        btnHome.setVisibility(View.INVISIBLE);
        txtLabel.setText("Career Details");

        btnWorldI = findViewById(R.id.btnWorldI);
        btnInformationS = findViewById(R.id.btnInformationS);
        btnDecisionT = findViewById(R.id.btnDecisionT);
        btnStructureJ = findViewById(R.id.btnStructureJ);
        btnWorldE = findViewById(R.id.btnWorldE);
        btnInformationN = findViewById(R.id.btnInformationN);
        btnDecisionF = findViewById(R.id.btnDecisionF);
        btnStructureP = findViewById(R.id.btnStructureP);

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

        final boolean[] ISelected = {false};
        final boolean[] ESelected = {false};
        final boolean[] SSelected = {false};
        final boolean[] NSelected = {false};
        final boolean[] TSelected = {false};
        final boolean[] FSelected = {false};
        final boolean[] JSelected = {false};
        final boolean[] PSelected = {false};

        btnWorldI.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnWorldI.setBackgroundResource(R.drawable.personality_world);

        btnInformationS.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnInformationS.setBackgroundResource(R.drawable.personality_information);

        btnDecisionT.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnDecisionT.setBackgroundResource(R.drawable.personality_decision);

        btnStructureJ.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnStructureJ.setBackgroundResource(R.drawable.personality_structure);

        btnWorldE.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnWorldE.setBackgroundResource(R.drawable.personality_world);

        btnInformationN.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnInformationN.setBackgroundResource(R.drawable.personality_information);

        btnDecisionF.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnDecisionF.setBackgroundResource(R.drawable.personality_decision);

        btnStructureP.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
        btnStructureP.setBackgroundResource(R.drawable.personality_structure);
        
        btnWorldI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                if(!ISelected[0]) {
                    btnWorldI.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.world));
                    btnWorldI.setBackgroundResource(R.drawable.personality_world_selected);
                    ISelected[0] = true;
                    ESelected[0] = false;
                }else{
                    ISelected[0] = false;
                }
            }
        });

        btnWorldE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorldI.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnWorldI.setBackgroundResource(R.drawable.personality_world);
                btnWorldE.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnWorldE.setBackgroundResource(R.drawable.personality_world);
                if(!ESelected[0]) {
                    btnWorldE.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.world));
                    btnWorldE.setBackgroundResource(R.drawable.personality_world_selected);
                    ISelected[0] = false;
                    ESelected[0] = true;
                }else{
                    ESelected[0] = false;
                }
            }
        });

        btnInformationS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnInformationS.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                if(!SSelected[0]) {
                    btnInformationS.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.information));
                    btnInformationS.setBackgroundResource(R.drawable.personality_information_selected);
                    SSelected[0] = true;
                    NSelected[0] = false;
                }else{
                    SSelected[0] = false;
                }
            }
        });

        btnInformationN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnInformationS.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnInformationS.setBackgroundResource(R.drawable.personality_information);
                btnInformationN.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnInformationN.setBackgroundResource(R.drawable.personality_information);
                if(!NSelected[0]) {
                    btnInformationN.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.information));
                    btnInformationN.setBackgroundResource(R.drawable.personality_information_selected);
                    SSelected[0] = false;
                    NSelected[0] = true;
                }else{
                    NSelected[0] = false;
                }
            }
        });

        btnDecisionT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnDecisionT.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                if(!TSelected[0]) {
                btnDecisionT.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.decision));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision_selected);
                    TSelected[0] = true;
                    FSelected[0] = false;
                }else{
                    TSelected[0] = false;
                }
            }
        });

        btnDecisionF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnDecisionT.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnDecisionT.setBackgroundResource(R.drawable.personality_decision);
                btnDecisionF.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnDecisionF.setBackgroundResource(R.drawable.personality_decision);
                if(!FSelected[0]) {
                    btnDecisionF.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.decision));
                    btnDecisionF.setBackgroundResource(R.drawable.personality_decision_selected);
                    TSelected[0] = false;
                    FSelected[0] = true;
                }else{
                    FSelected[0] = false;
                }
            }
        });

        btnStructureJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnStructureJ.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                if(!JSelected[0]) {
                btnStructureJ.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.structure));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure_selected);
                    JSelected[0] = true;
                    PSelected[0] = false;
                }else{
                    PSelected[0] = false;
                }
            }
        });

        btnStructureP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnStructureJ.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnStructureJ.setBackgroundResource(R.drawable.personality_structure);
                btnStructureP.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.text));
                btnStructureP.setBackgroundResource(R.drawable.personality_structure);
                if(!PSelected[0]) {
                    btnStructureP.setTextColor(ContextCompat.getColor(CareerDetails.this, R.color.structure));
                    btnStructureP.setBackgroundResource(R.drawable.personality_structure_selected);
                    JSelected[0] = false;
                    PSelected[0] = true;
                }else{
                    PSelected[0] = false;
                }
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