package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Result_department extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_department);

        // Initialize buttons
        Button btnBBA = findViewById(R.id.btn_bba);
        Button btnComputerScience = findViewById(R.id.btn_computer_science);
        Button btnElectricalEngineering = findViewById(R.id.btn_electrical_engineering);
        Button btnMediaCommunications = findViewById(R.id.btn_media_communications);
        Button btnMathematics = findViewById(R.id.btn_mathematics);
        Button btnEducation = findViewById(R.id.btn_education);
        Button btnPhysicalEducation = findViewById(R.id.btn_physical_education);
        ImageView backButton = findViewById(R.id.backButton);

        // Set click listeners for each department button
        btnBBA.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_BBA.class);
            startActivity(intent);
        });

        btnComputerScience.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_CS.class);
            startActivity(intent);
        });

        btnElectricalEngineering.setOnClickListener(v -> {
            Intent intent = new Intent(this, Result_EE.class);
            startActivity(intent);

        });

        btnMediaCommunications.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_Media.class);
            startActivity(intent);
        });

        btnMathematics.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_Math.class);
            startActivity(intent);
        });

        btnEducation.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_Education.class);
            startActivity(intent);
        });

        btnPhysicalEducation.setOnClickListener(v -> {
            Intent intent = new Intent(Result_department.this, Result_Physical_Education.class);
            startActivity(intent);
        });

        // Back button
        backButton.setOnClickListener(v -> finish());
    }
}
