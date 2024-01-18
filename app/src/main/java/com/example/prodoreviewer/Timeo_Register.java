package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Timeo_Register extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtConfirm;
    Button signup;
    TextView redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeo_register);

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

                if(email.equals("")||password.equals("")||confirm.equals("")){
                    Toast.makeText(Timeo_Register.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(confirm)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);
                            if(insert == true) {
                                Toast.makeText(Timeo_Register.this,"Signup successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Timeo_Register.this,Timeo_LogIn.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Timeo_Register.this,"Signup failed",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Timeo_Register.this,"User already exists, Login instead",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Timeo_Register.this,"Password mismatch",Toast.LENGTH_SHORT).show();
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