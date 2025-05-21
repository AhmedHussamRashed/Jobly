package com.example.jobly;

import android.content.DialogInterface;
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

 class ApplicationsActivity extends AppCompatActivity {

    private LinearLayout applicationsContainer;
    private ArrayList<Application> applicationsList = new ArrayList<>();
    private Gson gson = new Gson();
    private SharedPreferences prefs;
    private static final String PREF_KEY = "my_applications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);

        applicationsContainer = findViewById(R.id.applicationsContainer);
        prefs = getSharedPreferences("applications_data", MODE_PRIVATE);

        if (!prefs.contains(PREF_KEY)) {
            generateSampleApplications();
        }

        loadApplicationsFromPreferences();
        renderApplications();
    }

    private void generateSampleApplications() {
        applicationsList.add(new Application("Android Developer", "Meta", "Accepted"));
        applicationsList.add(new Application("Backend Engineer", "Amazon", "Pending"));
        applicationsList.add(new Application("UI Designer", "Spotify", "Rejected"));
        applicationsList.add(new Application("Frontend Developer", "Google", "Pending"));
        applicationsList.add(new Application("DevOps Engineer", "Microsoft", "Accepted"));

        saveApplicationsToPreferences();
    }

    private void loadApplicationsFromPreferences() {
        String json = prefs.getString(PREF_KEY, null);
        Type type = new TypeToken<ArrayList<Application>>() {}.getType();

        if (json != null) {
            applicationsList = gson.fromJson(json, type);
        }

        if (applicationsList == null) applicationsList = new ArrayList<>();
    }

    private void saveApplicationsToPreferences() {
        String json = gson.toJson(applicationsList);
        prefs.edit().putString(PREF_KEY, json).apply();
    }

    private void renderApplications() {
        applicationsContainer.removeAllViews();
        for (int i = 0; i < applicationsList.size(); i++) {
            Application app = applicationsList.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_application, null);
            TextView title = view.findViewById(R.id.jobTitle);
            TextView company = view.findViewById(R.id.jobCompany);
            TextView status = view.findViewById(R.id.jobStatus);

            title.setText(app.getTitle());
            company.setText(app.getCompany());
            status.setText(app.getStatus());

            int color;
            switch (app.getStatus().toLowerCase()) {
                case "accepted": color = 0xFF4CAF50; break; // أخضر
                case "rejected": color = 0xFFF44336; break; // أحمر
                default: color = 0xFFFF9800; break; // برتقالي
            }
            status.setTextColor(color);

            final int index = i;
            view.setOnClickListener(v -> showOptionsDialog(index));

            applicationsContainer.addView(view);
        }
    }

    private void showOptionsDialog(int index) {
        CharSequence[] options = {"Edit Status", "Delete Request", "cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an action")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            showEditStatusDialog(index);
                            break;
                        case 1:
                            deleteApplication(index);
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
        builder.setPositiveButton("save", (dialog, which) -> {
            AlertDialog alert = (AlertDialog) dialog;
            int selectedPosition = alert.getListView().getCheckedItemPosition();

            if (selectedPosition != -1) {
                String newStatus = statuses[selectedPosition].toString();
                applicationsList.get(index).setStatus(newStatus);
                saveApplicationsToPreferences();
                renderApplications();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }

    private void deleteApplication(int index) {
        applicationsList.remove(index);
        saveApplicationsToPreferences();
        renderApplications();
    }
}
