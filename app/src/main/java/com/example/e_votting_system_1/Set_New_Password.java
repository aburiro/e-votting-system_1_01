package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Set_New_Password extends AppCompatActivity {

    TextInputEditText passwordEditText, confirmPasswordEditText;
    Button updatePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password); // Make sure this XML name matches

        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        updatePasswordButton = findViewById(R.id.updatePasswordButton);

        updatePasswordButton.setOnClickListener(v -> {
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Success - Navigate to the next screen
            Toast.makeText(this, "Password successfully updated!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Set_New_Password.this, Successful.class);
            startActivity(intent);
            finish(); // Optional
        });
    }
}
