package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Set_New_Password extends AppCompatActivity {

    TextInputEditText passwordEditText, confirmPasswordEditText;
    Button updatePasswordButton;
    FirebaseAuth mAuth;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        mAuth = FirebaseAuth.getInstance();
        userEmail = getIntent().getStringExtra("email");

        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        updatePasswordButton = findViewById(R.id.updatePasswordButton);

        updatePasswordButton.setOnClickListener(v -> {
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            // First sign in the user (since OTP verified their email)
            mAuth.signInWithEmailAndPassword(userEmail, "temporary_password")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Now update password
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                user.updatePassword(password)
                                        .addOnCompleteListener(updateTask -> {
                                            if (updateTask.isSuccessful()) {
                                                Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(this, Login.class));
                                                finish();
                                            } else {
                                                Toast.makeText(this, "Error updating password: " +
                                                        updateTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        } else {
                            // If sign in fails, use password reset email as fallback
                            mAuth.sendPasswordResetEmail(userEmail)
                                    .addOnCompleteListener(resetTask -> {
                                        if (resetTask.isSuccessful()) {
                                            Toast.makeText(this, "Please check your email to reset password", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
        });
    }
}