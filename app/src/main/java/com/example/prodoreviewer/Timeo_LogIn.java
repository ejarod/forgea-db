package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Timeo_LogIn extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    TextView signup;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeo_log_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupText);
        databaseHelper = new DatabaseHelper(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if(email.equals("")||password.equals("")){
                    Toast.makeText(Timeo_LogIn.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean verified = databaseHelper.checkEmailPassword(txtEmail,txtPassword);
                    if(verified) {
                        //Toast.makeText(Timeo_LogIn.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Timeo_LogIn.this,mainMenu.class);
                        intent.putExtra("email", txtEmail);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Timeo_LogIn.this, "Enter correct email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Timeo_LogIn.this,Timeo_Register.class);
                startActivity(intent);
            }
        });
    }
}