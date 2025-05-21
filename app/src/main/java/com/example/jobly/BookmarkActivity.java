package com.example.jobly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private BookmarkAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        // إعداد التولبار
        MaterialToolbar toolbar = findViewById(R.id.toolbarBookmark);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // ربط المكونات
        recyclerView = findViewById(R.id.recyclerViewBookmark);
        progressBar = findViewById(R.id.progressBarBookmark);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // إظهار شريط التحميل
        progressBar.setVisibility(View.VISIBLE);

        // تحميل البيانات
        loadBookmarkedJobs();
    }

    private void loadBookmarkedJobs() {
        List<Job> bookmarkedJobs = MockData.getMockJobs();

        if (bookmarkedJobs != null && !bookmarkedJobs.isEmpty()) {
            adapter = new BookmarkAdapter(bookmarkedJobs, job -> {
                // فتح شاشة تفاصيل الوظيفة عند الضغط
                Intent intent = new Intent(BookmarkActivity.this, JobDetailsActivity.class);
                intent.putExtra("job_title", job.getTitle());
                intent.putExtra("job_company", job.getCompany());
                intent.putExtra("job_location", job.getLocation());
                intent.putExtra("job_description", job.getDescription());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        }

        // إخفاء شريط التحميل
        progressBar.setVisibility(View.GONE);
    }
}
