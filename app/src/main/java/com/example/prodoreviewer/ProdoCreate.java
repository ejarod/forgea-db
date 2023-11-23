package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;


public class ProdoCreate extends AppCompatActivity {

    Button btnAdd;
    EditText txtTopic;
    ImageButton btnBackButton,btnHome;
    TextView lblPageName;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prodo_create);



        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeCreate);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);

        txtTopic = (EditText) findViewById(R.id.txtTopic);
        btnAdd = (Button) findViewById(R.id.btnAddNewCard);
        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Create Topic");

        btnHome.setVisibility(View.INVISIBLE);

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoCreate.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoCreate.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = txtTopic.getText().toString();

                if(topicName.length() > 38) {
                    txtTopic.setError("Name too long");
                    txtTopic.requestFocus();
                    return;
                }

                if(topicName.isEmpty() || topicName.trim().isEmpty()) {
                    txtTopic.setError("Must not be empty");
                    txtTopic.requestFocus();
                    return;
                }

                MyDatabaseHelperCard myDB = new MyDatabaseHelperCard(ProdoCreate.this);
                if(myDB.topicExists(topicName)){
                    txtTopic.setError("Already exists0");
                    txtTopic.requestFocus();
                    return;
                }
                myDB.addTopic(topicName);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
    }
}