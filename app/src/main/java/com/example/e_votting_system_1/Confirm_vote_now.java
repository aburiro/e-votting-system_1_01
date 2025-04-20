package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigInteger;

public class Confirm_vote_now extends AppCompatActivity {

    private BlockchainService blockchainService;
    private BigInteger selectedCandidateId;
    private static final String TAG = "ConfirmVoteNow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_now);

        CheckBox confirmCheckbox = findViewById(R.id.confirmCheckbox);
        Button submitButton = findViewById(R.id.submitButton);

        blockchainService = new BlockchainService(this);

        String candidateIdString = getIntent().getStringExtra("CANDIDATE_ID");
        if (candidateIdString != null) {
            selectedCandidateId = new BigInteger(candidateIdString);
        } else {
            Toast.makeText(this, "No candidate selected!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // ✅ Enable button only if checkbox is ticked
        confirmCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            submitButton.setEnabled(isChecked);
            if (isChecked) {
                submitButton.setTextColor(getResources().getColor(android.R.color.black));
            } else {
                submitButton.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        });

        submitButton.setOnClickListener(v -> {
            Toast.makeText(this, "Submit button clicked", Toast.LENGTH_SHORT).show();

            submitButton.setEnabled(false);
            blockchainService.castVote(selectedCandidateId, new BlockchainService.BlockchainCallback() {
                @Override
                public void onSuccess() {
                    runOnUiThread(() -> {
                        Toast.makeText(Confirm_vote_now.this, "✅ Vote recorded on blockchain!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Confirm_vote_now.this, Successful_vote_now.class);
                        intent.putExtra("CANDIDATE_ID", selectedCandidateId.toString());
                        startActivity(intent);
                        finish();
                    });
                }

                @Override
                public void onError(String error) {
                    runOnUiThread(() -> {
                        Toast.makeText(Confirm_vote_now.this, "❌ Vote failed: " + error, Toast.LENGTH_LONG).show();
                        submitButton.setEnabled(true);
                    });
                }
            });
        });
    }

}
