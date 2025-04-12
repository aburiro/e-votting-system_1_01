package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button signInButton;
    TextView forgotPasswordText, signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind Views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        signUpText = findViewById(R.id.signUpText);

        // Sign In Button Click
        signInButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                // Go to Home Activity (You must create Home.java)
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        // Forgot Password Click
        forgotPasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Forgot_Password.class); // You must create Email_Check.java
            startActivity(intent);
        });

        // Sign Up Click
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class); // You must create SignUp.java
            startActivity(intent);
        });
    }
}
