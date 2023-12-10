package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileDetails extends AppCompatActivity {


    String UserEmail, world, information, decision, structure = "";
    int percentW, percentI, percentD, percentS;
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

    Button btnWorld,btnInformation,btnDecision,btnStructure;
    TextView trait,trait2,percent2,traitPercent,traitDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);
        btnHome.setVisibility(View.INVISIBLE);
        txtLabel.setText("Profile");

        btnWorld = findViewById(R.id.btnWorldProfile);
        btnInformation = findViewById(R.id.btnInformationProfile);
        btnDecision = findViewById(R.id.btnDecisionProfile);
        btnStructure = findViewById(R.id.btnStructureProfile);
        trait = findViewById(R.id.txtTraitProfile);
        trait2 = findViewById(R.id.txtTrait2);
        traitPercent = findViewById(R.id.txtTraitPercentageProfile);
        percent2 = findViewById(R.id.txtPercent2);
        traitDetails = findViewById(R.id.txtTraitDetailsProfile);

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        DatabaseHelper db = new DatabaseHelper(this);
        world = world + (db.getPersonality(UserEmail).charAt(0));
        information = world + (db.getPersonality(UserEmail).charAt(1));
        decision = world + (db.getPersonality(UserEmail).charAt(2));
        structure = world + (db.getPersonality(UserEmail).charAt(3));

        percentW = db.getPercentage(UserEmail,"world");
        percentI = db.getPercentage(UserEmail,"information");
        percentD = db.getPercentage(UserEmail,"decisions");
        percentS = db.getPercentage(UserEmail,"structure");

        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);

        traitPercent.setText(String.valueOf(percentW) + "%");
        percent2.setText(String.valueOf(100-percentW) + "%");
        traitPercent.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.world));
        btnWorld.setText(world);

        if(world.equals("E")){
            traitDetails.setText(worldDescriptions[0]);
            trait.setText("Extrovert");
            trait2.setText("Introvert");
            btnWorld.setText("E");
        } else {
            traitDetails.setText(worldDescriptions[1]);
            trait.setText("Introvert");
            trait2.setText("Extrovert");
            btnWorld.setText("I");
        }

        if(information.equals("S")){
            btnInformation.setText("S");
        } else {
            btnInformation.setText("N");
        }

        if(decision.equals("T")){
            btnDecision.setText("T");
        } else {
            btnDecision.setText("F");
        }

        if(structure.equals("J")){
            btnStructure.setText("J");
        } else {
            btnStructure.setText("P");
        }

        btnWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorld.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnWorld.setBackgroundResource(R.drawable.personality_world);

                btnInformation.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnInformation.setBackgroundResource(R.drawable.personality_information);

                btnDecision.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnDecision.setBackgroundResource(R.drawable.personality_decision);

                btnStructure.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnStructure.setBackgroundResource(R.drawable.personality_structure);

                btnWorld.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.world));
                btnWorld.setBackgroundResource(R.drawable.personality_world_selected);

                traitPercent.setText(String.valueOf(percentW) + "%");
                percent2.setText(String.valueOf(100-percentW) + "%");
                traitPercent.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.world));
                trait.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.world));

                if(world.equals("E")){
                    traitDetails.setText(worldDescriptions[0]);
                    trait.setText("Extrovert");
                    trait2.setText("Introvert");
                    btnWorld.setText("E");
                } else {
                    traitDetails.setText(worldDescriptions[1]);
                    trait.setText("Introvert");
                    trait2.setText("Extrovert");
                    btnWorld.setText("I");
                }
            }
        });

        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorld.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnWorld.setBackgroundResource(R.drawable.personality_world);

                btnInformation.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnInformation.setBackgroundResource(R.drawable.personality_information);

                btnDecision.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnDecision.setBackgroundResource(R.drawable.personality_decision);

                btnStructure.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnStructure.setBackgroundResource(R.drawable.personality_structure);

                //Set Selected
                btnInformation.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.information));
                btnInformation.setBackgroundResource(R.drawable.personality_information_selected);

                traitPercent.setText(String.valueOf(percentI) + "%");
                percent2.setText(String.valueOf(100-percentI) + "%");
                traitPercent.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.information));
                trait.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.information));
                btnInformation.setText(information);

                if(information.equals("S")){
                    traitDetails.setText(informationDescriptions[0]);
                    trait.setText("Sensing");
                    trait2.setText("Intuitive");
                    btnInformation.setText("S");
                } else {
                    traitDetails.setText(informationDescriptions[1]);
                    trait.setText("Intuitive");
                    trait2.setText("Sensing");
                    btnInformation.setText("N");
                }
            }
        });

        btnDecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorld.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnWorld.setBackgroundResource(R.drawable.personality_world);

                btnInformation.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnInformation.setBackgroundResource(R.drawable.personality_information);

                btnDecision.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnDecision.setBackgroundResource(R.drawable.personality_decision);

                btnStructure.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnStructure.setBackgroundResource(R.drawable.personality_structure);


                //Set Select
                btnDecision.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.decision));
                btnDecision.setBackgroundResource(R.drawable.personality_decision_selected);

                traitPercent.setText(String.valueOf(percentD) + "%");
                percent2.setText(String.valueOf(100-percentD) + "%");
                traitPercent.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.decision));
                trait.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.decision));
                btnDecision.setText(decision);

                if(decision.equals("T")){
                    traitDetails.setText(decisionDescriptions[0]);
                    trait.setText("Thinking");
                    trait2.setText("Feeling");
                    btnDecision.setText("T");
                } else {
                    traitDetails.setText(decisionDescriptions[1]);
                    trait.setText("Feeling");
                    trait2.setText("Thinking");
                    btnDecision.setText("F");
                }
            }
        });

        btnStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnWorld.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnWorld.setBackgroundResource(R.drawable.personality_world);

                btnInformation.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnInformation.setBackgroundResource(R.drawable.personality_information);

                btnDecision.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnDecision.setBackgroundResource(R.drawable.personality_decision);

                btnStructure.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.text));
                btnStructure.setBackgroundResource(R.drawable.personality_structure);

                //Set Select
                btnStructure.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.structure));
                btnStructure.setBackgroundResource(R.drawable.personality_structure_selected);

                traitPercent.setText(String.valueOf(percentS) + "%");
                percent2.setText(String.valueOf(100-percentS) + "%");
                traitPercent.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.structure));
                trait.setTextColor(ContextCompat.getColor(ProfileDetails.this, R.color.structure));
                btnStructure.setText(structure);

                if(structure.equals("J")){
                    traitDetails.setText(structureDescriptions[0]);
                    trait.setText("Judging");
                    trait2.setText("Perceiving");
                    btnStructure.setText("J");
                } else {
                    traitDetails.setText(structureDescriptions[1]);
                    trait.setText("Perceiving");
                    trait2.setText("Judging");
                    btnStructure.setText("P");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ProfileDetails.this, ForgeaMainMenu.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                finish();
            }
        });
    }
}