package com.example.jobly;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NotificationAdapter adapter;
    private NotificationsViewModel notificationsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // إعداد شريط الأدوات
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("الإشعارات");
        }

        // المكونات
        recyclerView = findViewById(R.id.recyclerViewNotifications);
        progressBar = findViewById(R.id.progressBarNotifications);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // ViewModel
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        // مراقبة البيانات
        notificationsViewModel.getNotifications().observe(this, notifications -> {
            if (notifications != null && !notifications.isEmpty()) {
                adapter = new NotificationAdapter(notifications);
                recyclerView.setAdapter(adapter);
            }
            progressBar.setVisibility(View.GONE);
        });

        // تحميل البيانات
        progressBar.setVisibility(View.VISIBLE);
        notificationsViewModel.loadNotifications();
    }
}
