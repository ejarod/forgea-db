package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CareerPathsDescription extends AppCompatActivity {

    FirebaseFirestore dbF;
    String UserEmail, world, information, decision, structure = "";
    Long percentW, percentI, percentD, percentS;
    ImageButton btnHome, btnBack;
    TextView txtLabel;

    String personalityCode = "";

    public enum MBTIType {
        ISTJ("You're organized and reliable, with a strong sense of duty. You thrive in structured environments, value tradition, and approach tasks with a methodical mindset. Your commitment to responsibility makes you a cornerstone in any team."),
        ISTP("You're a practical problem-solver and a hands-on learner. Adaptable and resourceful, you excel in navigating challenges on the fly. Your love for exploration and curiosity makes you a master of troubleshooting and finding innovative solutions."),
        ISFJ("You're warm-hearted, considerate, and dedicated to helping others. With a keen attention to detail, you find fulfillment in providing support and maintaining harmony. Your nurturing nature and reliability make you a trusted and caring friend."),
        ISFP("You're a free spirit with a deep appreciation for aesthetics. Artistic and compassionate, you express yourself through creativity and enjoy connecting with the beauty in the world. Your laid-back nature and empathy make you a calming presence."),
        INTJ("You're creative and attentive to details. You prefer working alone. You enjoy solving problems and always try to make things better whether about systems, processes, or anything else you come across."),
        INTP("You're a logical thinker and problem-solver. Driven by curiosity, you enjoy exploring new ideas and theoretical concepts. Your independence and analytical mind make you adept at unraveling complex puzzles."),
        INFJ("You're insightful and compassionate, driven by a desire to make a positive impact. Guided by strong intuition, you excel in understanding people and envisioning a better future. Your passion for meaningful connections makes you a natural advocate for change."),
        INFP("You're a dreamer with a rich inner world. Idealistic and empathetic, you're drawn to creativity and authenticity. Your commitment to personal values and belief in the potential for good inspire those around you."),
        ESTJ("You're a reliable and decisive organizer. Practical and efficient, you excel in managing tasks and ensuring order. Your leadership style is marked by a focus on structure and a commitment to achieving goals."),
        ESTP("You're a dynamic and action-oriented individual. Adventurous and spontaneous, you enjoy tackling challenges head-on and living in the moment. Your charisma and love for excitement make you a natural risk-taker."),
        ESFJ("You're a caring and sociable team player. Dedicated to creating harmony, you excel in nurturing relationships and supporting those around you. Your warmth and generosity make you a valued friend and confidant."),
        ESFP("You're a lively and outgoing individual. Spontaneous and fun-loving, you thrive in social settings and enjoy bringing joy to others. Your enthusiasm and adaptability make you the life of the party."),
        ENTJ("You're a natural leader with a strategic mindset. Goal-oriented and assertive, you thrive in positions of authority and enjoy taking charge to achieve results. Your ability to see the big picture and make tough decisions sets you apart."),
        ENTP("You're an innovative and enthusiastic idea generator. Adaptable and quick-witted, you excel in brainstorming and finding unconventional solutions. Your energetic and charismatic nature makes you a compelling force in any discussion."),
        ENFJ("You're a charismatic and empathetic leader. Motivated by a desire to help others, you excel in building strong relationships and fostering a positive environment. Your natural charisma and inspirational qualities make you a magnetic presence."),
        ENFP("You're an imaginative and energetic individual. Enthusiastic and open-minded, you thrive on exploring new possibilities and connecting with others. Your creativity and passion for life make you a source of inspiration.");

        private final String description;

        MBTIType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    Button btnWorld, btnInformation, btnDecision, btnStructure, btnContinue;
    TextView trait, traitDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbF = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_career_paths_description);
        btnBack = findViewById(R.id.btnBackButton);
        btnContinue = findViewById(R.id.btnContinue2);
        btnWorld = findViewById(R.id.btnWorld2);
        btnInformation = findViewById(R.id.btnInformation2);
        btnDecision = findViewById(R.id.btnDecision2);
        btnStructure = findViewById(R.id.btnStructure2);
        trait = findViewById(R.id.txtCareerName);
        traitDetails = findViewById(R.id.txtTraitDetails);
        txtLabel = findViewById(R.id.lblPageName);
        txtLabel.setText("Career Paths");
        btnHome = findViewById(R.id.btnHome);
        btnHome.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        if (intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        /*DatabaseHelper db = new DatabaseHelper(this);
        world = "" + (db.getPersonality(UserEmail).charAt(0));
        information = "" + (db.getPersonality(UserEmail).charAt(1));
        decision = "" + (db.getPersonality(UserEmail).charAt(2));
        structure = "" + (db.getPersonality(UserEmail).charAt(3));

        btnWorld.setText(world);
        btnInformation.setText(information);
        btnDecision.setText(decision);
        btnStructure.setText(structure);*/


        DocumentReference userRef = dbF.collection("Users").document(UserEmail);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String personality = document.getString("personalitySummary");

                        // Check if personality is not null and has at least 4 characters
                        if (personality != null && personality.length() >= 4) {
                            world = "" + (personality.charAt(0));
                            information = "" + (personality.charAt(1));
                            decision = "" + (personality.charAt(2));
                            structure = "" + (personality.charAt(3));

                            personalityCode = world + information + decision + structure;

                        } else {
                            // Handle the case where personality data is missing or incomplete
                            world = "n";
                            information = "o";
                            decision = "n";
                            structure = "e";

                            personalityCode = "none";
                        }

                        percentW = document.getLong("percentW");
                        percentI = document.getLong("percentI");
                        percentD = document.getLong("percentD");
                        percentS= document.getLong("percentS");

                        btnWorld.setText(world);
                        btnInformation.setText(information);
                        btnDecision.setText(decision);
                        btnStructure.setText(structure);

                        MBTIType mbtiType = MBTIType.valueOf(personalityCode);

                        traitDetails.setText(mbtiType.getDescription());
                    }
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareerPathsDescription.this, CareerPathsRecommendations.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                intent.putExtra("mbtiType", personalityCode); // Pass the personality code to the next activity
                intent.putExtra("World", world);
                intent.putExtra("Information", information);
                intent.putExtra("Decision", decision);
                intent.putExtra("Structure", structure);

                intent.putExtra("percentW", percentW);
                intent.putExtra("percentI", percentI);
                intent.putExtra("percentD", percentD);
                intent.putExtra("percentS", percentS);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
