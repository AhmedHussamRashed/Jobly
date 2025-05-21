package com.example.jobly;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private TextView homeTitle;
    private LinearLayout suggestedJobsContainer, featuredJobsContainer;
    private ArrayList<Job> suggestedJobs = new ArrayList<>();
    private ArrayList<Job> featuredJobs = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeTitle = findViewById(R.id.homeTitle);
        suggestedJobsContainer = findViewById(R.id.suggestedJobsContainer);
        featuredJobsContainer = findViewById(R.id.featuredJobsContainer);

        homeTitle.setText("Welcome Ahmed!");

        loadJobsFromPreferences();
        renderSuggestedJobs();
        renderFeaturedJobs();
    }

    private void loadJobsFromPreferences() {
        SharedPreferences prefs = getSharedPreferences("jobs_data", MODE_PRIVATE);
        String suggestedJson = prefs.getString("suggested_jobs", null);
        String featuredJson = prefs.getString("featured_jobs", null);

        Type type = new TypeToken<ArrayList<Job>>() {}.getType();
        if (suggestedJson != null) {
            suggestedJobs = gson.fromJson(suggestedJson, type);
        }

        if (featuredJson != null) {
            featuredJobs = gson.fromJson(featuredJson, type);
        }

        if (suggestedJobs == null) suggestedJobs = new ArrayList<>();
        if (featuredJobs == null) featuredJobs = new ArrayList<>();
    }

    private void saveJobsToPreferences() {
        SharedPreferences prefs = getSharedPreferences("jobs_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("suggested_jobs", gson.toJson(suggestedJobs));
        editor.putString("featured_jobs", gson.toJson(featuredJobs));
        editor.apply();
    }

    private void renderSuggestedJobs() {
        suggestedJobsContainer.removeAllViews();
        for (Job job : suggestedJobs) {
            View jobItem = createJobView(job, false);
            suggestedJobsContainer.addView(jobItem);
        }
    }

    private void renderFeaturedJobs() {
        featuredJobsContainer.removeAllViews();
        for (Job job : featuredJobs) {
            View jobItem = createJobView(job, true);
            featuredJobsContainer.addView(jobItem);
        }
    }

    private View createJobView(Job job, boolean isFeatured) {
        View jobItem = LayoutInflater.from(this).inflate(R.layout.item_featured_job, null);
        TextView title = jobItem.findViewById(R.id.jobTitle);
        TextView company = jobItem.findViewById(R.id.jobCompany);
        ImageView favIcon = jobItem.findViewById(R.id.jobFavorite);

        title.setText(job.getTitle());
        company.setText(job.getCompany());

        // الأيقونة: أسود للمميزة، رمادي للمقترحة
        favIcon.setImageResource(isFeatured ? R.drawable.ic_bookmark : R.drawable.ic_bookmark2);
        favIcon.setColorFilter(isFeatured ? 0xFF000000 : 0xFF888888); // أسود أو رمادي

        favIcon.setOnClickListener(v -> {
            if (isFeatured) {
                // إزالة من المميزة → إلى المقترحة (رمادية)
                featuredJobs.remove(job);
                suggestedJobs.add(job);
            } else {
                // إزالة من المقترحة → إلى المميزة (سوداء)
                suggestedJobs.remove(job);
                featuredJobs.add(job);
            }
            saveJobsToPreferences();
            renderSuggestedJobs();
            renderFeaturedJobs();
        });

        return jobItem;
    }
}
