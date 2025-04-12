package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Reset_password extends AppCompatActivity {

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password); // make sure your XML file name is correct

        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            // Navigate to Set screen
            Intent intent = new Intent(Reset_password.this, Set_New_Password.class);
            startActivity(intent);
            finish(); // optional, if you don't want to keep this activity in the back stack
        });
    }
}
