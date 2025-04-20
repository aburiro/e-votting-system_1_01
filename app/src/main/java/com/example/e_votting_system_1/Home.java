package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    CardView voteCard, resultsCard, settingsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        voteCard     = findViewById(R.id.voteCard);
        resultsCard  = findViewById(R.id.resultsCard);
        settingsCard = findViewById(R.id.settingsCard);

        voteCard.setOnClickListener(v ->
                startActivity(new Intent(Home.this, Vote_now.class))
        );

        resultsCard.setOnClickListener(v ->
                startActivity(new Intent(Home.this, Result_department.class))
        );

        settingsCard.setOnClickListener(v ->
                startActivity(new Intent(Home.this, Settings.class))
        );
    }
}
