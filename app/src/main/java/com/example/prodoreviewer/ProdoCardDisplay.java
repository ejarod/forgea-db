package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ProdoCardDisplay extends AppCompatActivity {

    // Delay in milliseconds (e.g., 5000 = 5 seconds)
    int DELAY;
    Timer timer;

    //Calendar calendar = Calendar.getInstance();
    //Date currentTime = calendar.getTime();

    //Calendar shuffleTime = Calendar.getInstance();
    private ArrayList<Card> deck = new ArrayList<>();
    //boolean dailyShuffle = false;

    private MyDatabaseHelper db;

    //private int hourSet = 9;
    private TextView txtFrontText;
    private TextView txtBackText;
    private Button btnFlip;
    private Button btnNext,btnHard,btnEasy;
    private ImageButton btnBackButton;
    TextView lblPageName;

    private int currentCardIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_card_display);
        Intent intent = getIntent();
        String topicName = intent.getStringExtra("topic");
        txtFrontText = findViewById(R.id.txtFrontText);
        txtBackText = findViewById(R.id.txtBackText);
        btnFlip = findViewById(R.id.btnFlip);
        btnNext = findViewById(R.id.btnNext);
        btnEasy = findViewById(R.id.btnEasy);
        btnHard = findViewById(R.id.btnHard);
        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);

        lblPageName.setText(topicName);

        db = new MyDatabaseHelper(ProdoCardDisplay.this);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        DELAY = preferences.getInt("timer", 99999);
        DELAY *= 1000;

        if(topicName.equalsIgnoreCase("quick")) {
            deck = db.getCards();
        } else {
            deck = db.getCards(topicName);
        }

        if(deck.size()==0) {
            Toast.makeText(this, "No Cards Created", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        shuffleDeck();
        currentCardIndex = 0;
        displayCard();

        /*shuffleTime.set(Calendar.HOUR_OF_DAY, hourSet); // Set the desired hour
        shuffleTime.set(Calendar.MINUTE, 0); // Set the desired minute
        if (currentTime.after(shuffleTime.getTime()) && dailyShuffle == false) {
            shuffleDeck();
            dailyShuffle = true;
            currentCardIndex = 0;
            displayCard();
        }*/

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextCard();
            }
        });

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyCard();
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hardCard();
            }
        });

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                finish();
            }
        });

    }

    private void displayCard() {
        if(currentCardIndex > deck.size()) {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }

        Card currentCard = deck.get(currentCardIndex);
        txtFrontText.setText(currentCard.getName());
        txtBackText.setText(currentCard.getContent());

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProdoCardDisplay.this, "Time is Up!", Toast.LENGTH_SHORT).show();
                        nextCard();
                    }
                });
            }
        }, DELAY);
    }

    private void flipCard() {
        boolean isFrontVisible = txtFrontText.getVisibility() == View.VISIBLE;
        boolean isBackVisible = txtBackText.getVisibility() == View.VISIBLE;

        if (isFrontVisible) {
            txtFrontText.setVisibility(View.INVISIBLE);
            txtBackText.setVisibility(View.VISIBLE);
        } else if (isBackVisible) {
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
        }
    }

    private void nextCard() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        currentCardIndex++;

        if (currentCardIndex < deck.size()) {
            displayCard();
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }
    }

    private void easyCard() {
        Card currentCard = deck.get(currentCardIndex);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        deck.remove(currentCard);

        if (currentCardIndex < deck.size()) {
            displayCard();
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }
    }

    private void hardCard() {
        Card currentCard = deck.get(currentCardIndex);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        deck.add(currentCard);

        currentCardIndex++;

        if (currentCardIndex < deck.size()) {
            displayCard();
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
}