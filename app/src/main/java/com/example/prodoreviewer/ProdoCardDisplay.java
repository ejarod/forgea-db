package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
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
    int LOOP;

    //Calendar calendar = Calendar.getInstance();
    //Date currentTime = calendar.getTime();

    //Calendar shuffleTime = Calendar.getInstance();
    private ArrayList<Card> deck = new ArrayList<>();
    //boolean dailyShuffle = false;

    private MyDatabaseHelper db;

    //private int hourSet = 9;
    private TextView txtFrontText,lblFront,lblBack;
    private TextView txtBackText;
    private Button btnFlip;
    private Button btnNext,btnHard,btnEasy;
    private ImageButton btnBackButton,btnHome;
    TextView lblPageName;

    private int currentCardIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodo_card_display);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeDisplay);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);


        Intent intent = getIntent();
        String topicName = intent.getStringExtra("topic");
        txtFrontText = findViewById(R.id.txtFrontText);
        txtBackText = findViewById(R.id.txtBackText);
        btnFlip = findViewById(R.id.btnFlip);
        btnNext = findViewById(R.id.btnNext);
        btnEasy = findViewById(R.id.btnEasy);
        btnHard = findViewById(R.id.btnHard);
        btnHome = findViewById(R.id.btnHome);
        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);
        lblFront = findViewById(R.id.lblFront);
        lblBack = findViewById(R.id.lblBackside);



        lblPageName.setText(topicName);

        db = new MyDatabaseHelper(ProdoCardDisplay.this);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        DELAY = preferences.getInt("timer", 10);
        DELAY *= 1000;

        LOOP = preferences.getInt("loop", 2);

        ArrayList<Card> originaldeck = new ArrayList<>();

        if(topicName.equalsIgnoreCase(" ")) {
            originaldeck = db.getCards();
            lblPageName.setText("Quick");
        } else {
            originaldeck = db.getCards(topicName);
        }

        if(originaldeck.size()==0) {
            Toast.makeText(this, "No Cards Created", Toast.LENGTH_SHORT).show();
            finish();
        }

        for (Card c : originaldeck) {
            for (int i = 0; i < LOOP; i++) {
                deck.add(c);
            }
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
                Toast.makeText(ProdoCardDisplay.this, "Card flagged as easy", Toast.LENGTH_SHORT).show();
                easyCard();
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProdoCardDisplay.this, "Card flagged as hard", Toast.LENGTH_SHORT).show();
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



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

                Intent intent = new Intent(ProdoCardDisplay.this, ProdoReviewer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
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
        if(currentCard.getDifficulty()=="easy") {
            nextCard();
        }
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

            lblFront.setVisibility(View.INVISIBLE);
            lblBack.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnEasy.setVisibility(View.VISIBLE);
            btnHard.setVisibility(View.VISIBLE);
        } else if (isBackVisible) {
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);

            lblFront.setVisibility(View.VISIBLE);
            lblBack.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnEasy.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
        }
    }

    private void nextCard() {

        if (currentCardIndex >= deck.size()) {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        currentCardIndex++;

        if (currentCardIndex < deck.size()) {
            displayCard();
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
            lblFront.setVisibility(View.VISIBLE);
            lblBack.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnEasy.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
        } else {
            txtFrontText.setText("End of Topic");
            txtBackText.setVisibility(View.INVISIBLE);
            lblFront.setVisibility(View.INVISIBLE);
            lblBack.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnEasy.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }
    }

    private void easyCard() {

        if (currentCardIndex >= deck.size()) {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }

        Card currentCard = deck.get(currentCardIndex);
        deck.remove(currentCardIndex);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (currentCardIndex < deck.size()){
            for(int i = 0; i < deck.size(); i++) {
                if(deck.get(i)==currentCard) {
                    deck.remove(i);
                }
            }
        }

        if (currentCardIndex < deck.size()) {
            displayCard();
            txtFrontText.setVisibility(View.VISIBLE);
            txtBackText.setVisibility(View.INVISIBLE);
            lblFront.setVisibility(View.VISIBLE);
            lblBack.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnEasy.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
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

        if (currentCardIndex >= deck.size()) {
            Toast.makeText(this, "No Cards Left", Toast.LENGTH_SHORT).show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }

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
            lblFront.setVisibility(View.VISIBLE);
            lblBack.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnEasy.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
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