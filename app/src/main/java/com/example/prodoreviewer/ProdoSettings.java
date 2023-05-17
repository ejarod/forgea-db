package com.example.prodoreviewer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProdoSettings extends AppCompatActivity {

    ImageButton btnBackButton;
    Button btnSave,btnClearTopics,btnClearCards;
    EditText txtTimer;
    TextView lblPageName;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_settings);
        btnBackButton = findViewById(R.id.btnBackButton);
        btnClearCards = findViewById(R.id.btnClearCards);
        btnClearTopics = findViewById(R.id.btnClearTopics);
        btnSave = findViewById(R.id.btnSaveSettings);
        txtTimer = findViewById(R.id.txtTimer);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText("Settings");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db = new MyDatabaseHelper(ProdoSettings.this);
                //db.saveSettings(Integer.parseInt(txtTimer.getText().toString()));

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                int timer = Integer.parseInt(txtTimer.getText().toString());
                editor.putInt("timer", timer);

                editor.apply();
            }
        });

        btnClearTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmTopicClear();
            }
        });

        btnClearCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmCardClear();
            }
        });
    }

    private void confirmTopicClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to clear cards?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new MyDatabaseHelper(ProdoSettings.this);
                        db.deleteAllTopics();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void confirmCardClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to clear topics?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new MyDatabaseHelper(ProdoSettings.this);
                        db.deleteAllCards();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}