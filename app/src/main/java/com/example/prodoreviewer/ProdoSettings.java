package com.example.prodoreviewer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ProdoSettings extends AppCompatActivity {

    ImageButton btnBackButton,btnHome;
    Button btnSave,btnClearTopics,btnClearCards;
    EditText txtTimer,txtLoop;
    TextView lblPageName;
    private MyDatabaseHelperCard db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prodo_settings);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeSettings);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);

        btnBackButton = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        btnClearCards = findViewById(R.id.btnClearCards);
        btnClearTopics = findViewById(R.id.btnClearTopics);
        btnSave = findViewById(R.id.btnSaveSettings);
        txtTimer = findViewById(R.id.txtTimer);
        lblPageName = findViewById(R.id.lblPageName);
        txtLoop = findViewById(R.id.txtLoop);

        lblPageName.setText("Settings");

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProdoSettings.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                try{
                    String input = txtTimer.getText().toString();
                    if(!input.isEmpty()) {
                        int timer = Integer.parseInt(input);
                        editor.putInt("timer", timer);
                    }

                } catch(Exception e) {
                    txtTimer.setError("Invalid Input");
                    txtTimer.requestFocus();
                    return;
                }

                try{
                    String input2 = txtLoop.getText().toString();
                    if(!input2.isEmpty()) {
                        int loop = Integer.parseInt(input2);
                        editor.putInt("loop", loop);
                    }

                    if(input2.equals(0)) {
                        txtLoop.setError("Must not be zero");
                        txtLoop.requestFocus();
                        return;
                    }

                } catch(Exception e) {
                    txtLoop.setError("Invalid Input");
                    txtLoop.requestFocus();
                    return;
                }

                editor.apply();
                Toast.makeText(ProdoSettings.this, "Settings saved", Toast.LENGTH_SHORT).show();
                finish();
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

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
    }

    private void confirmTopicClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder
                .setMessage("Are you sure you want to clear all topics?")
                .setPositiveButton("Clear topics", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new MyDatabaseHelperCard(ProdoSettings.this);
                        db.deleteAllTopics();
                        db.deleteAllCards();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void confirmCardClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder
                .setMessage("Are you sure you want to clear all cards?")
                .setPositiveButton("Clear cards", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new MyDatabaseHelperCard(ProdoSettings.this);
                        db.deleteAllCards();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}