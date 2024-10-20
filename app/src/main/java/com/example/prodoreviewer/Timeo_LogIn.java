package com.example.prodoreviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
// Import Firestore libraries
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.DocumentReference;

public class Timeo_LogIn extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    TextView signup;
    Animation scaleup, scaledown;

    FirebaseAuth mAuth;  // Firebase Auth instance
    FirebaseFirestore db; // Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeo_log_in);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupText);
        scaleup = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        setButtonTouchListener(loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (txtEmail.isEmpty() || txtPassword.isEmpty()) {
                    Toast.makeText(Timeo_LogIn.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Use Firebase Authentication to sign in the user
                    mAuth.signInWithEmailAndPassword(txtEmail, txtPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Login successful, retrieve user information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user != null) {
                                            String userEmail = user.getEmail();

                                            // Fetch the "hasAssessed" field from Firestore for this user
                                            assert userEmail != null;
                                            DocumentReference userRef = db.collection("Users").document(userEmail);
                                            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        if (document.exists()) {
                                                            // Retrieve the "assessed" value from Firestore
                                                            String personality = document.getString("personalitySummary");
                                                            Intent intent;
                                                            assert personality != null;
                                                            if (personality.equalsIgnoreCase("none")) {
                                                                intent = new Intent(Timeo_LogIn.this, AssessmentMain.class);
                                                            } else {
                                                                intent = new Intent(Timeo_LogIn.this, ForgeaMainMenu.class);
                                                            }

                                                            intent.putExtra("email", userEmail);
                                                            startActivity(intent);
                                                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                                                        } else {
                                                            // If document does not exist, handle it
                                                            Toast.makeText(Timeo_LogIn.this, "User data not found", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(Timeo_LogIn.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }

                                    } else {
                                        // Login failed, display error message
                                        Toast.makeText(Timeo_LogIn.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Timeo_LogIn.this, Timeo_Register.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setButtonTouchListener(final Button button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    button.startAnimation(scaleup);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    button.startAnimation(scaledown);
                }
                return false;
            }
        });
    }
}
