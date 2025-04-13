package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    EditText emailEditText;
    Button resetPasswordButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize Firebase
        try {


            mAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            Toast.makeText(this, "Firebase initialization failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        emailEditText = findViewById(R.id.emailEditText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);

        resetPasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!email.toLowerCase().endsWith("@iba-suk.edu.pk")) {
                Toast.makeText(this, "Only iba-suk.edu.pk emails are allowed", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgot_Password.this, "Reset link sent to: " + email, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Forgot_Password.this, email_check.class);
                            startActivity(intent);
                        } else {
                            String error = task.getException() != null ?
                                    task.getException().getMessage() : "Unknown error";
                            Toast.makeText(Forgot_Password.this,
                                    "Failed to send reset email: " + error,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}