package com.example.e_votting_system_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Forgot_Password extends AppCompatActivity {

    EditText emailEditText;
    Button resetPasswordButton;
    ProgressBar progressBar;

    private String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.emailEditText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        progressBar = findViewById(R.id.progressBar);

        resetPasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            generatedOtp = generateOTP();
            progressBar.setVisibility(View.VISIBLE);
            sendOtpEmail(email, generatedOtp);
        });
    }

    private String generateOTP() {
        int random = new Random().nextInt(900000) + 100000;
        return String.valueOf(random);
    }

    private void sendOtpEmail(String recipientEmail, String otp) {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.mailersend.com/v1/email");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer mlsn.d82c68cfcbaeecbdb1aab885f8ee6928fac742d9fe726ee0b95849a5eed0138a");
                conn.setDoOutput(true);

                JSONObject payload = new JSONObject();
                JSONObject from = new JSONObject();
                from.put("email", "MS_Cxsu3P@test-51ndgwv703xlzqx8.mlsender.net");
                from.put("name", "OTP Sender");

                JSONObject to = new JSONObject();
                to.put("email", recipientEmail);
                to.put("name", "User");

                payload.put("from", from);
                payload.put("to", new org.json.JSONArray().put(to));
                payload.put("subject", "Your OTP Code");
                payload.put("text", "Your OTP code is: " + otp);
                payload.put("custom_id", "otp_email");

                OutputStream os = conn.getOutputStream();
                os.write(payload.toString().getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();

                InputStream is = (responseCode >= 200 && responseCode < 300) ? conn.getInputStream() : conn.getErrorStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String responseBody = response.toString();

                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    if (responseCode == 202) {
                        Toast.makeText(this, "OTP sent to your email", Toast.LENGTH_LONG).show();
                        Log.d("OTP", "OTP sent: " + otp);

                        String userEmail = getIntent().getStringExtra("email");
                        Intent intent = new Intent(Forgot_Password.this, email_check.class);
                        intent.putExtra("email", userEmail); // Make sure to include the email
                        intent.putExtra("generatedOtp", otp);


                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to send OTP: " + responseBody, Toast.LENGTH_LONG).show();
                        Log.e("MailerSend Error", "Code: " + responseCode + ", Response: " + responseBody);
                    }
                });

            } catch (Exception e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
                e.printStackTrace();
            }
        }).start();
    }

}
