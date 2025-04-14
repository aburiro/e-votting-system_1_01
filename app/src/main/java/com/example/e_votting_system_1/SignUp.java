package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, cmsIdEditText,
            departmentEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private ImageView backArrow;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Bind views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cmsIdEditText = findViewById(R.id.cmsIdEditText);
        departmentEditText = findViewById(R.id.departmentEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backArrow = findViewById(R.id.backArrow);

        registerButton.setOnClickListener(v -> registerUser());
        backArrow.setOnClickListener(v -> onBackPressed());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String cmsId = cmsIdEditText.getText().toString().trim();
        String department = departmentEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Input validation
        if (username.isEmpty() || email.isEmpty() || cmsId.isEmpty() ||
                department.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.endsWith("@iba-suk.edu.pk")) {
            Toast.makeText(this, "Only iba-suk.edu.pk emails are allowed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading indicator (you can add a ProgressBar in your layout)
        registerButton.setEnabled(false);

        // Create user with Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration success, save additional user data to Firestore
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveUserDataToFirestore(user.getUid(), username, email, cmsId, department);
                        }
                    } else {
                        // Registration failed
                        registerButton.setEnabled(true);
                        Toast.makeText(SignUp.this, "Registration failed: " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserDataToFirestore(String userId, String username, String email,
                                         String cmsId, String department) {
        // Create a new user document in Firestore
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("cmsId", cmsId);
        user.put("department", department);
        user.put("createdAt", System.currentTimeMillis());

        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(SignUp.this,
                            "Registration successful. You can now log in.",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this, Login.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    registerButton.setEnabled(true);
                    Toast.makeText(SignUp.this, "Failed to save user data: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }

    private void sendEmailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this,
                                    "Registration successful. Please verify your email.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, Login.class));
                            finish();
                        } else {
                            registerButton.setEnabled(true);
                            Toast.makeText(SignUp.this,
                                    "Failed to send verification email: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}