package com.example.jobly;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 ثوانٍ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash_logo);

        // إنشاء تأثير Fade In
        Animation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(1500); // 1.5 ثانية
        fadeIn.setFillAfter(true); // يبقى بعد الانتهاء

        logo.startAnimation(fadeIn);

        // الانتقال إلى MainActivity بعد انتهاء التأثير
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
