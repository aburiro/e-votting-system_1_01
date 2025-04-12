package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class email_check extends AppCompatActivity {

    EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4, otpDigit5;
    TextView otpErrorText, resendEmailText;
    Button resetPasswordButton;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email_check);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        otpDigit1 = findViewById(R.id.otpDigit1);
        otpDigit2 = findViewById(R.id.otpDigit2);
        otpDigit3 = findViewById(R.id.otpDigit3);
        otpDigit4 = findViewById(R.id.otpDigit4);
        otpDigit5 = findViewById(R.id.otpDigit5);

        otpErrorText = findViewById(R.id.otpErrorText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        resendEmailText = findViewById(R.id.resendEmailText);
        backArrow = findViewById(R.id.backArrow);

        // Handle reset button click
        resetPasswordButton.setOnClickListener(v -> {
            String otp = otpDigit1.getText().toString().trim() +
                    otpDigit2.getText().toString().trim() +
                    otpDigit3.getText().toString().trim() +
                    otpDigit4.getText().toString().trim() +
                    otpDigit5.getText().toString().trim();

            if (otp.length() < 5) {
                otpErrorText.setVisibility(View.VISIBLE);
                otpErrorText.setText("Please enter the 5-digit code.");
                return;
            }

            // TODO: Replace this check with actual Firebase OTP verification
            if (otp.equals("12345")) {  // Dummy check
                otpErrorText.setVisibility(View.GONE);
                Toast.makeText(this, "OTP Verified. Proceed to reset password.", Toast.LENGTH_SHORT).show();

                // Navigate to Reset_password activity
                Intent intent = new Intent(email_check.this, Reset_password.class);
                startActivity(intent);
                finish(); // Optional: finish current screen
            }
            else {
                otpErrorText.setVisibility(View.VISIBLE);
                otpErrorText.setText("Incorrect OTP. Please try again.");
            }
        });

        // Handle back arrow
        backArrow.setOnClickListener(v -> onBackPressed());

        // Handle resend OTP
        resendEmailText.setOnClickListener(v -> {
            Toast.makeText(this, "OTP resend feature will be integrated with Firebase.", Toast.LENGTH_SHORT).show();
            // TODO: Trigger Firebase resend logic
        });
    }
}
