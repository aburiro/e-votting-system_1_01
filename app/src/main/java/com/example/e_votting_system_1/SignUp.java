package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText usernameEditText, emailEditText, cmsIdEditText, departmentEditText,
            passwordEditText, confirmPasswordEditText;
    Button registerButton;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Bind views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cmsIdEditText = findViewById(R.id.cmsIdEditText);
        departmentEditText = findViewById(R.id.departmentEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backArrow = findViewById(R.id.backArrow);

        // Register Button Click
        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String cmsId = cmsIdEditText.getText().toString().trim();
            String department = departmentEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Simple validations
            if (username.isEmpty() || email.isEmpty() || cmsId.isEmpty() ||
                    department.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Email domain check
            if (!email.endsWith("@iba-suk.edu.pk")) {
                Toast.makeText(this, "Only iba-suk.edu.pk emails are allowed", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Add Firebase registration logic here

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUp.this, Login.class));
            finish();
        });

        // Back Arrow Click
        backArrow.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}
