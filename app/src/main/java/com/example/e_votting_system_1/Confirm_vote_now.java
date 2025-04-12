package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class Confirm_vote_now extends AppCompatActivity {

    CheckBox confirmCheckbox;
    Button submitButton;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_now);

        confirmCheckbox = findViewById(R.id.confirmCheckbox);
        submitButton = findViewById(R.id.submitButton);
        titleText = findViewById(R.id.titleText);

        confirmCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                submitButton.setEnabled(true);
                submitButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue_3388FF)); // define this color
                submitButton.setTextColor(getResources().getColor(android.R.color.white));
                titleText.setText("Submit Your vote");
                Intent intent = new Intent(Confirm_vote_now.this, Successful_vote_now.class);
                startActivity(intent);
            } else {
                submitButton.setEnabled(false);
                submitButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
                submitButton.setTextColor(getResources().getColor(android.R.color.white));
                titleText.setText("Confirm your choice");
            }
        });
    }
}
