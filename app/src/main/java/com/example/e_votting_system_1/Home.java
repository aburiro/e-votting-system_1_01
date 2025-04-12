package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    CardView voteCard, resultsCard, settingsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Replace with your layout filename

        // Bind the cards
        voteCard = findViewById(R.id.voteCard);
        resultsCard = findViewById(R.id.resultsCard);
       settingsCard = findViewById(R.id.settingsCard);

        // Vote Now click
       voteCard.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Vote_now.class);
            startActivity(intent);
        });

        // Results click
        resultsCard.setOnClickListener(v -> {
          Intent intent = new Intent(Home.this, Result_department.class);
           startActivity(intent);
       });

        // Settings click
        settingsCard.setOnClickListener(v -> {
//            Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
//            startActivity(intent);
       });
    }
}
