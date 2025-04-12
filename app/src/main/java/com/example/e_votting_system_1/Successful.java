package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Successful extends AppCompatActivity {

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful); // Ensure this XML name matches your layout file

        // Bind the Continue button
        continueButton = findViewById(R.id.continueButton);

        // Set click listener to navigate to the Login class
        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(Successful.this, Login.class);
            startActivity(intent);
            finish(); // Optional: close the Successful activity so that the user cannot return to it
        });
    }
}
