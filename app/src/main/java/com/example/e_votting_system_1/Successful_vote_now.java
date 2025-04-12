package com.example.e_votting_system_1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Successful_vote_now extends AppCompatActivity {

    Button goHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_vote_now);

        goHomeButton = findViewById(R.id.goHomeButton);

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Home Activity (replace HomeActivity.class with your target activity)
                Intent intent = new Intent(Successful_vote_now.this, Home.class);
                startActivity(intent);
                finish(); // optional: finish this screen so user can't go back to success screen
            }
        });
    }
}
