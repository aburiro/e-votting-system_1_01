package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Vote_now extends AppCompatActivity {

    private LinearLayout candidateListContainer;
    private Button nextButton;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private String selectedCandidateId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_now);

        candidateListContainer = findViewById(R.id.candidateListContainer);
        nextButton = findViewById(R.id.nextButton);
        progressBar = findViewById(R.id.progressBar);
        ImageView backArrow = findViewById(R.id.backArrow);

        radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        candidateListContainer.addView(radioGroup);

        // Go back
        backArrow.setOnClickListener(view -> finish());

        // Static dummy candidates
        addCandidateToUI("Ali Raza", "CMS001", "ali_raza_id");
        addCandidateToUI("Sana Khan", "CMS002", "sana_khan_id");
        addCandidateToUI("Bilal Ahmed", "CMS003", "bilal_ahmed_id");

        nextButton.setOnClickListener(v -> {

                Intent intent = new Intent(Vote_now.this, Confirm_vote_now.class);
                intent.putExtra("CANDIDATE_ID", "12345"); // value must NOT be null!
                startActivity(intent);

        });

        nextButton.setEnabled(false); // Disable button until a selection is made
    }

    private void addCandidateToUI(String name, String cmsId, String candidateId) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(name + " (" + cmsId + ")");
        radioButton.setTextSize(16f);
        radioButton.setPadding(20, 20, 20, 20);
        radioButton.setId(View.generateViewId());
        radioGroup.addView(radioButton);

        radioButton.setOnClickListener(view -> {
            selectedCandidateId = candidateId;
            nextButton.setEnabled(true);
        });
    }
}
