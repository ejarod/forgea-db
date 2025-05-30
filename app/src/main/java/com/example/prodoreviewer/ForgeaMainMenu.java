package com.example.prodoreviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ForgeaMainMenu extends AppCompatActivity {

    String UserEmail, world, information, decision, structure = "";
    String UserPersonality;
    DatabaseHelper db1;

    String personalityCode = "none";
    FirebaseFirestore db;
    Button btnWorld, btnInformation, btnDecision, btnStructure;
    ImageButton btnAssessmentMenu, btnProfileMenu, btnCareerPathsMenu, btnPersonalityTraitsMenu, btnBack, btnHome;
    TextView txtLabel, txtAssessmentMenu, txtProfileMenu, txtCareerPathsMenu, txtPersonalityTraitsMenu;
    RecyclerView recyclerView;

    LinearLayout userPersonality, userProfile, userAssessment, userCourseAdvice, userPersonalityTraits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgea_main_menu);

        db1 = new DatabaseHelper(this);
        db = FirebaseFirestore.getInstance();

        btnAssessmentMenu = findViewById(R.id.btnAssessment);
        btnProfileMenu = findViewById(R.id.btnProfile);
        btnCareerPathsMenu = findViewById(R.id.btnCareerPaths);
        btnPersonalityTraitsMenu = findViewById(R.id.btnPersonalityTraits);
        btnBack = findViewById(R.id.btnBackButton);
        btnBack.setVisibility(View.INVISIBLE);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setImageResource(R.drawable.icon_info);
        btnHome.setColorFilter(getResources().getColor(android.R.color.white));
        btnWorld = findViewById(R.id.btnWorld3);
        btnInformation = findViewById(R.id.btnInformation3);
        btnDecision = findViewById(R.id.btnDecision3);
        btnStructure = findViewById(R.id.btnStructure3);

        recyclerView = findViewById(R.id.tasklistmenu2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ForgeaMainMenu.this));

        txtLabel = findViewById(R.id.lblPageName);
        txtAssessmentMenu = findViewById(R.id.txtAssessment);
        txtProfileMenu = findViewById(R.id.txtProfile);
        txtCareerPathsMenu = findViewById(R.id.txtCourseRecommendation);
        txtPersonalityTraitsMenu = findViewById(R.id.txtPersonalityTraits);

        userPersonality = findViewById(R.id.UserPersonality);
        userProfile = findViewById(R.id.Profile);
        userAssessment = findViewById(R.id.Assessment);
        userCourseAdvice = findViewById(R.id.CourseAdvice);
        userPersonalityTraits = findViewById(R.id.PersonalityTraits);

        txtLabel.setText("Forgea");

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        if(intent != null) {
            UserEmail = intent.getStringExtra("email");
        }

        db1 = new DatabaseHelper(this);

        DocumentReference userRef = db.collection("Users").document(UserEmail);
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

                            personalityCode = personality;


                        } else {
                            // Handle the case where personality data is missing or incomplete
                            world = "n";
                            information = "o";
                            decision = "n";
                            structure = "e";

                        }

                        btnWorld.setText(world);
                        btnInformation.setText(information);
                        btnDecision.setText(decision);
                        btnStructure.setText(structure);
                    }
                }
            }
        });

        List<CareerPath> careerPaths = db1.getAllCareerPaths(personalityCode);

        CareerPathAdapter adapter = new CareerPathAdapter(careerPaths, db1, UserEmail);
        recyclerView.setAdapter(adapter);
        userAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        btnAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtAssessmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,ProfileDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        userCourseAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        btnCareerPathsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,CareerPathsDescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtCareerPathsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,CareerPathsDescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        userPersonalityTraits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,AssessmentMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnPersonalityTraitsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,PersonalityDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtPersonalityTraitsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,PersonalityDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgeaMainMenu.this,References.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

//    @Override
//    protected void onDestroy() {
//        // Close the database helper when the activity is destroyed
//        if (db != null) {
//            db.close();
//        }
//        super.onDestroy();
//    }
}