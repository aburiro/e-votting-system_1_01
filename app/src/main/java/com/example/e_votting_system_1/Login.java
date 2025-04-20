package com.example.e_votting_system_1;

import android.content.Intent;
// REMOVE: import android.content.SharedPreferences;
import android.os.Bundle;
// REMOVE: import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
// REMOVE: import com.google.firebase.firestore.DocumentSnapshot;
// REMOVE: import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signInButton;
    private TextView forgotPasswordText, signUpText;

    private FirebaseAuth mAuth;
    // REMOVE: private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // REMOVE: Initialize FirebaseFirestore

        // Bind Views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        signUpText = findViewById(R.id.signUpText);

        // Sign In Button Click
        signInButton.setOnClickListener(v -> loginUser());

        // Forgot Password Click
        forgotPasswordText.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Forgot_Password.class));
        });

        // Sign Up Click
        signUpText.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, SignUp.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is already signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Keeping the email verified check here or in Home is a design choice.
        // The original Home code checks it, so it's safer to keep it there if you want to enforce it.
        // For a simple flow, you could remove it here and only check in Home.
        if (currentUser != null) { // Check if user is logged in
            navigateToHome(); // Navigate directly to Home
        }
        // REMOVE: populateSharedPreferencesAndNavigate(currentUser);
    }

    private void loginUser() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading indicator
        signInButton.setEnabled(false);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    signInButton.setEnabled(true); // Re-enable button after auth task completes

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // ✅ User authenticated successfully, just navigate.
                            // Home activity will now fetch department from Firestore itself.
                            navigateToHome();
                        } else {
                            Toast.makeText(Login.this,
                                    "Authentication successful but user object is null?",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this,
                                "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToHome() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Login.this, Home.class));
        finish(); // Close Login activity
    }

    // REMOVE: populateSharedPreferencesAndNavigate method

}