package com.example.e_votting_system_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Result_CS extends Activity {

    private ImageView backArrow;
    private TextView bottomTotalBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_bba);

        // Initialize views
        backArrow = findViewById(R.id.backArrow);
        bottomTotalBar = findViewById(R.id.bottomTotalBar);

        // Handle back navigation
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can use finish() to go back to the previous screen
                finish();
            }
        });

        // Optional: Show total votes dynamically (you can calculate this dynamically too)
        int totalVotes = 240 + 180 + 130;
        bottomTotalBar.setText("CS total votes " + totalVotes);
    }
}
