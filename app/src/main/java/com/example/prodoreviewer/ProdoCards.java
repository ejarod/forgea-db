package com.example.prodoreviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProdoCards extends AppCompatActivity {
    ImageButton btnBackButton;
    TextView lblPageName;
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDB;
    private ArrayList<String> card_name;
    private CardAdapter cardadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prodo_cards);



        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        postponeEnterTransition();

        View sharedView = findViewById(R.id.includeCards);
        String transitionName = getString(R.string.transition_image);
        ViewCompat.setTransitionName(sharedView, transitionName);

        btnBackButton = findViewById(R.id.btnBackButton);
        lblPageName = findViewById(R.id.lblPageName);
        recyclerView = findViewById(R.id.recyclerView2);

        lblPageName.setText("Flash Cards");

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("topic");

        myDB = new MyDatabaseHelper(ProdoCards.this);
        card_name = new ArrayList<>();

        storeData(topicName);
        cardadapter = new CardAdapter(ProdoCards.this,card_name);
        recyclerView.setAdapter(cardadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProdoCards.this));

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdoCards.this, Prodotopics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        startPostponedEnterTransition();
    }

    private void storeData(String topic) {
        Cursor cursor = myDB.readCardData(topic);
        if(cursor == null) {
            return;
        }
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"No Cards Created",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                card_name.add(cursor.getString(1));
            }
        }
    }
}