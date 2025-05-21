package com.example.jobly;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

 class MyJobActivity extends AppCompatActivity {

    private LinearLayout myJobContainer;
    private TextView emptyMessage;
    private ArrayList<Application> myJobsList = new ArrayList<>();
    private Gson gson = new Gson();
    private SharedPreferences prefs;
    private static final String PREF_KEY = "my_applications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);

        myJobContainer = findViewById(R.id.myJobContainer);
        emptyMessage = findViewById(R.id.emptyMessage);
        prefs = getSharedPreferences("applications_data", MODE_PRIVATE);

        loadMyJobsFromPreferences();
        renderMyJobs();
    }

    private void loadMyJobsFromPreferences() {
        String json = prefs.getString(PREF_KEY, null);
        Type type = new TypeToken<ArrayList<Application>>() {}.getType();

        if (json != null) {
            myJobsList = gson.fromJson(json, type);
        }

        if (myJobsList == null) myJobsList = new ArrayList<>();
    }

    private void saveMyJobsToPreferences() {
        String json = gson.toJson(myJobsList);
        prefs.edit().putString(PREF_KEY, json).apply();
    }

    private void renderMyJobs() {
        myJobContainer.removeAllViews();

        if (myJobsList.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            return;
        } else {
            emptyMessage.setVisibility(View.GONE);
        }

        for (int i = 0; i < myJobsList.size(); i++) {
            Application app = myJobsList.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_my_job, null);
            TextView title = view.findViewById(R.id.jobTitle);
            TextView company = view.findViewById(R.id.jobCompany);
            TextView status = view.findViewById(R.id.jobStatus);

            title.setText(app.getTitle());
            company.setText(app.getCompany());
            status.setText(app.getStatus());

            int color;
            switch (app.getStatus().toLowerCase()) {
                case "accepted":
                    color = 0xFF4CAF50;
                    break;
                case "rejected":
                    color = 0xFFF44336;
                    break;
                default:
                    color = 0xFFFF9800;
                    break;
            }
            status.setTextColor(color);

            final int index = i;
            view.setOnClickListener(v -> showOptionsDialog(index));

            myJobContainer.addView(view);
        }
    }

    private void showOptionsDialog(int index) {
        CharSequence[] options = {"Edit Status", "Delete Job", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an action")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            showEditStatusDialog(index);
                            break;
                        case 1:
                            deleteJob(index);
                            break;
                        case 2:
                            dialog.dismiss();
                            break;
                    }
                })
                .show();
    }

    private void showEditStatusDialog(int index) {
        CharSequence[] statuses = {"Accepted", "Pending", "Rejected"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a new status");
        builder.setSingleChoiceItems(statuses, -1, null);
        builder.setPositiveButton("Save", (dialog, which) -> {
            AlertDialog alert = (AlertDialog) dialog;
            int selectedPosition = alert.getListView().getCheckedItemPosition();

            if (selectedPosition != -1) {
                String newStatus = statuses[selectedPosition].toString();
                myJobsList.get(index).setStatus(newStatus);
                saveMyJobsToPreferences();
                renderMyJobs();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteJob(int index) {
        myJobsList.remove(index);
        saveMyJobsToPreferences();
        renderMyJobs();
    }
}
