package com.example.e_votting_system_1;

import android.content.Intent;
// REMOVE: import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, cmsIdEditText,
            passwordEditText, confirmPasswordEditText, phoneNumberEditText;
    private Spinner departmentSpinner;
    private Button registerButton;
    private ImageView backArrow;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String[] departments = {"Select Department", "BBA", "Computer Science", "Electrical Engineering", "Media Communications",  "Mathematics" , "Eduction" ,"Physical Education"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cmsIdEditText = findViewById(R.id.cmsIdEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        registerButton = findViewById(R.id.registerButton);
        backArrow = findViewById(R.id.backArrow);

        // Set spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(adapter);

        registerButton.setOnClickListener(v -> registerUser());
        backArrow.setOnClickListener(v -> onBackPressed());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String cmsId = cmsIdEditText.getText().toString().trim();
        String phone = phoneNumberEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String department = departmentSpinner.getSelectedItem().toString();

        if (username.isEmpty() || email.isEmpty() || cmsId.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
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

        if (department.equals("Select Department")) {
            // Decide how to handle this - perhaps make selection mandatory
            Toast.makeText(this, "Please select your department", Toast.LENGTH_SHORT).show();
            return;
            // department = "Not specified"; // Or set a default/error state
        }

        registerButton.setEnabled(false);

        String finalDepartment = department;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Save user data to Firestore
                            saveUserDataToFirestore(user.getUid(), username, email, cmsId, finalDepartment, phone);
                        } else {
                            registerButton.setEnabled(true);
                            Toast.makeText(SignUp.this, "Registration failed: User object is null.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        registerButton.setEnabled(true);
                        Toast.makeText(SignUp.this, "Registration failed: " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserDataToFirestore(String userId, String username, String email,
                                         String cmsId, String department, String phone) {

        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("cmsId", cmsId);
        user.put("department", department); // Save department here, Home will read it
        user.put("phone", phone);
        user.put("createdAt", System.currentTimeMillis());

        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    // Data saved to Firestore, now send verification email
                    sendEmailVerification();
                })
                .addOnFailureListener(e -> {
                    registerButton.setEnabled(true);
                    Toast.makeText(SignUp.this, "Failed to save user data: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    // Consider deleting the auth user if data save fails badly
                    if (mAuth.getCurrentUser() != null) {
                        mAuth.getCurrentUser().delete();
                    }
                });
    }

    private void sendEmailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        registerButton.setEnabled(true); // Re-enable button after async tasks

                        if (task.isSuccessful()) {
                            // REMOVE: SharedPreferences saving logic

                            Toast.makeText(SignUp.this,
                                    "Verification email sent. Please verify your email.",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this, Login.class));
                            finish();
                        } else {
                            Toast.makeText(SignUp.this,
                                    "Failed to send verification email: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            // Decide what to do if verification email fails. Maybe allow login but rely on Home's check? Or delete user?
                        }
                    });
        } else {
            registerButton.setEnabled(true);
            Toast.makeText(SignUp.this, "Verification failed: User is null.", Toast.LENGTH_SHORT).show();
        }
    }

}