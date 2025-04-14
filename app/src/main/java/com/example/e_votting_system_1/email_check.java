package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class email_check extends AppCompatActivity {

    EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4, otpDigit5, otpDigit6;
    TextView otpErrorText, resendEmailText;
    Button resetPasswordButton;
    String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_check);

        // Initialize views
        otpDigit1 = findViewById(R.id.otpDigit1);
        otpDigit2 = findViewById(R.id.otpDigit2);
        otpDigit3 = findViewById(R.id.otpDigit3);
        otpDigit4 = findViewById(R.id.otpDigit4);
        otpDigit5 = findViewById(R.id.otpDigit5);
        otpDigit6 = findViewById(R.id.otpDigit6);
        otpErrorText = findViewById(R.id.otpErrorText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        resendEmailText = findViewById(R.id.resendEmailText);

        // Get the OTP from the Intent
        generatedOtp = getIntent().getStringExtra("generatedOtp");

        // Set listener for OTP verification button
        resetPasswordButton.setOnClickListener(v -> {
            String enteredOtp = getEnteredOtp();

            if (enteredOtp.equals(generatedOtp)) {
                otpErrorText.setVisibility(View.GONE);
                Toast.makeText(this, "OTP Verified Successfully!", Toast.LENGTH_SHORT).show();

                // Proceed to reset password or next step
                // After successful OTP verification
                String userEmail = getIntent().getStringExtra("email"); // Make sure you're passing the email
                Intent intent = new Intent(email_check.this, Reset_password.class);
                intent.putExtra("email", userEmail);
                startActivity(intent);
                finish();

            } else {
                otpErrorText.setVisibility(View.VISIBLE);
            }
        });

        // Set listener for "Resend email" link
        resendEmailText.setOnClickListener(v -> {
            // You can implement code to resend the OTP email here
            Toast.makeText(this, "OTP resent to your email", Toast.LENGTH_SHORT).show();
            // Optionally trigger re-sending the OTP using your existing logic from ForgotPasswordActivity
        });
    }

    // Method to get OTP entered by the user
    private String getEnteredOtp() {
        return otpDigit1.getText().toString() +
                otpDigit2.getText().toString() +
                otpDigit3.getText().toString() +
                otpDigit4.getText().toString() +
                otpDigit5.getText().toString() +
                otpDigit6.getText().toString();
    }
}
