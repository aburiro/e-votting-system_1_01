package com.example.e_votting_system_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    private ImageView backArrow;
    private TextView bottomTotalBar;
    private TextView title;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_department); // Make sure this matches your results layout file

        // Initialize views
        backArrow = findViewById(R.id.backArrow);
        bottomTotalBar = findViewById(R.id.bottomTotalBar);
        title = findViewById(R.id.title);

        // Get department from intent
        String department = getIntent().getStringExtra("DEPARTMENT");

        // Set department title
        if (department != null) {
            title.setText(department + " Results");
        }

        // Calculate and display total votes
        int totalVotes = 240 + 180 + 130;
        bottomTotalBar.setText(department + " total votes: " + totalVotes);

        // Set back button click listener
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to previous
            }
        });
    }
}