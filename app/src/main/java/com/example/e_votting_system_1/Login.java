package com.example.e_votting_system_1;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signInButton;
    private TextView forgotPasswordText, signUpText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

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
        if (currentUser != null && currentUser.isEmailVerified()) {
            navigateToHome();
        }
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
                    signInButton.setEnabled(true);

                    if (task.isSuccessful()) {
                        // Check if email is verified

                        navigateToHome(); // Allow login directly
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        if (user != null && user.isEmailVerified()) {
//                            navigateToHome();
//                        } else {
//                            Toast.makeText(Login.this,
//                                    "Please verify your email first",
//                                    Toast.LENGTH_SHORT).show();
//                            mAuth.signOut();
//                        }
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
        finish();
    }
}