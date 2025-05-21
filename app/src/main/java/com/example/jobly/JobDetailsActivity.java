package com.example.jobly;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView jobTitleTextView;
    private TextView jobCompanyTextView;
    private TextView jobLocationTextView;
    private TextView jobDescriptionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // ربط الواجهات مع الـ XML
        jobTitleTextView = findViewById(R.id.jobTitle);
        jobCompanyTextView = findViewById(R.id.jobCompany);
        jobLocationTextView = findViewById(R.id.jobLocation);
        jobDescriptionTextView = findViewById(R.id.jobDescription);

        // استلام البيانات المرسلة من BookmarkActivity
        String title = getIntent().getStringExtra("job_title");
        String company = getIntent().getStringExtra("job_company");
        String location = getIntent().getStringExtra("job_location");
        String description = getIntent().getStringExtra("job_description");

        // عرض البيانات على الشاشة
        jobTitleTextView.setText(title);
        jobCompanyTextView.setText(company);
        jobLocationTextView.setText(location);
        jobDescriptionTextView.setText(description);
    }
}
