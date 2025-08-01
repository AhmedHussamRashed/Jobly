package com.example.jobly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.appbar.MaterialToolbar;

public class AboutMe extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private TextView description;
    private ShapeableImageView companyImage;
    private Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        // ربط المكونات
        topAppBar = findViewById(R.id.topAppBar);
        description = findViewById(R.id.description);
        companyImage = findViewById(R.id.companyImage);
        contactButton = findViewById(R.id.contactButton);

        // إعداد شريط الأدوات مع زر الرجوع
        setSupportActionBar(topAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        topAppBar.setNavigationOnClickListener(v -> onBackPressed());


        description.setText("نحن شركة مبتكرة تعمل على تقديم الحلول الرقمية المبدعة التي تساهم في تطوير الأعمال ورفع الكفاءة التشغيلية.");

        // إعداد زر الاتصال
        contactButton.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "استفسار عن الشركة");
            intent.putExtra(Intent.EXTRA_TEXT, "مرحباً, لدي استفسار حول الشركة.");
            startActivity(Intent.createChooser(intent, "اختيار التطبيق"));
        });
    }
}
