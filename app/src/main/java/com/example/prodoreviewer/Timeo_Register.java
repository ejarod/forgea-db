package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Objects;

public class Timeo_Register extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    FirebaseFirestore firestore;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtConfirm;
    Button signup;
    TextView redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeo_register);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        txtEmail = findViewById(R.id.signup_email);
        txtPassword = findViewById(R.id.signup_password);
        txtConfirm = findViewById(R.id.signup_confirm);
        signup = findViewById(R.id.signup_button);
        redirect = findViewById(R.id.loginRedirectText);
        databaseHelper = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirm = txtConfirm.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(Timeo_Register.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirm)) {
                        // Check if the email is already registered by trying to create an account
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Signup successful, store additional user info in Firestore
                                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                                            if (firebaseUser != null) {

                                                // Create a HashMap to store user data
                                                HashMap<String, Object> userData = new HashMap<>();
                                                userData.put("email", email);
                                                userData.put("type", 1);  // Default type or any other logic
                                                userData.put("personalitySummary", "none");
                                                userData.put("percentW", 0);
                                                userData.put("percentI", 0);
                                                userData.put("percentD", 0);
                                                userData.put("percentS", 0);

                                                // Save user data in Firestore
                                                firestore.collection("Users").document(email)
                                                        .set(userData)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    // Data saved successfully, navigate to login screen
                                                                    Toast.makeText(Timeo_Register.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(Timeo_Register.this, Timeo_LogIn.class);
                                                                    startActivity(intent);
                                                                } else {
                                                                    Toast.makeText(Timeo_Register.this, "Signup successful but failed to save data", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            }

                                        } else {
                                            // Handle the exception if the email already exists or other errors occur
                                            try {
                                                throw Objects.requireNonNull(task.getException());
                                            } catch (FirebaseAuthUserCollisionException e) {
                                                // Email already registered
                                                Toast.makeText(Timeo_Register.this, "User already exists, Login instead", Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {
                                                // General signup failure
                                                Toast.makeText(Timeo_Register.this, "Signup failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(Timeo_Register.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Timeo_Register.this,Timeo_LogIn.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}