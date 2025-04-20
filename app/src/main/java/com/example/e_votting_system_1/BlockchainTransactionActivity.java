package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;

public class BlockchainTransactionActivity extends AppCompatActivity {
    private TextView transactionStatus;
    private TextView transactionHash;
    private ProgressBar progressBar;
    private BlockchainService blockchainService;
    private String candidateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blockchain_transaction);

        transactionStatus = findViewById(R.id.transactionStatus);
        transactionHash = findViewById(R.id.transactionHash);
        progressBar = findViewById(R.id.progressBar);

        blockchainService = new BlockchainService(this);
        candidateId = getIntent().getStringExtra("CANDIDATE_ID");

        // Simulate blockchain confirmation process
        simulateBlockchainConfirmation();
    }

    private void simulateBlockchainConfirmation() {
        // Update UI to show transaction is being processed
        transactionStatus.setText("Processing your vote on the blockchain...");

        // In a real app, you would listen for transaction confirmation
        new Handler().postDelayed(() -> {
            transactionStatus.setText("Transaction mined - waiting for confirmations...");
            progressBar.setProgress(30);

            new Handler().postDelayed(() -> {
                transactionStatus.setText("Vote confirmed on blockchain!");
                progressBar.setProgress(100);

                // Proceed to success screen after delay
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(this, Successful_vote_now.class);
                    intent.putExtra("CANDIDATE_ID", candidateId);
                    startActivity(intent);
                    finish();
                }, 1500);
            }, 3000);
        }, 2000);
    }
}