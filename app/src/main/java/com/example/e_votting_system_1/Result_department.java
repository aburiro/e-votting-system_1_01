package com.example.e_votting_system_1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Result_department extends AppCompatActivity {

    private Button selectedTag;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_department); // Change this to your layout file name

        // Initialize views
        selectedTag = findViewById(R.id.selectedTag);
        backButton = findViewById(R.id.backButton);

        // Navigate to ResultsBBAActivity when BBA button is clicked
        selectedTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result_department.this, Results_BBA.class);
                startActivity(intent);
            }
        });

        // Optional: Go back to previous screen when back button clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // or use onBackPressed()
            }
        });
    }
}