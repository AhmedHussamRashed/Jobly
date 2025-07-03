package com.example.jobly;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private TextView loginLink;
    private MaterialButton googleSignup, facebookSignup, twitterSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);


        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.register_button);
        loginLink = findViewById(R.id.login_link);
        googleSignup = findViewById(R.id.google_signup);
        facebookSignup = findViewById(R.id.facebook_signup);
        twitterSignup = findViewById(R.id.twitter_signup);

        // الضغط على زر التسجيل
        registerButton.setOnClickListener(v -> {
            boolean valid = true;

            // تصفير الأخطاء السابقة
            nameEditText.setError(null);
            emailEditText.setError(null);
            passwordEditText.setError(null);
            confirmPasswordEditText.setError(null);

            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (TextUtils.isEmpty(name)) {
                nameEditText.setError("هذا الحقل مطلوب");
                nameEditText.requestFocus();
                valid = false;
            }

            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("هذا الحقل مطلوب");
                if (valid) emailEditText.requestFocus();
                valid = false;
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("هذا الحقل مطلوب");
                if (valid) passwordEditText.requestFocus();
                valid = false;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                confirmPasswordEditText.setError("هذا الحقل مطلوب");
                if (valid) confirmPasswordEditText.requestFocus();
                valid = false;
            }

            if (!TextUtils.isEmpty(password) && !password.equals(confirmPassword)) {
                confirmPasswordEditText.setError("كلمة المرور غير متطابقة");
                if (valid) confirmPasswordEditText.requestFocus();
                valid = false;
            }

            if (valid) {
                // يمكنك الآن إرسال البيانات أو إنشاء الحساب
                // مثلاً:
                // startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }
        });

        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });

        googleSignup.setOnClickListener(v -> {
            // تنفيذ تسجيل Google
        });

        facebookSignup.setOnClickListener(v -> {
            // تنفيذ تسجيل Facebook
        });

        twitterSignup.setOnClickListener(v -> {
            // تنفيذ تسجيل Twitter
        });
    }
}