package com.example.jobly;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddJobActivity extends AppCompatActivity {

    private EditText editTitle, editDescription, editCompany, editSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        // ربط العناصر
        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        editCompany = findViewById(R.id.editCompany);
        editSalary = findViewById(R.id.editSalary);

        // زر الحفظ
        findViewById(R.id.btnSave).setOnClickListener(v -> saveJob());
    }

    private void saveJob() {
        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String company = editCompany.getText().toString().trim();
        String salary = editSalary.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || company.isEmpty() || salary.isEmpty()) {
            Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("jobs_data", MODE_PRIVATE);
        String json = prefs.getString("jobs_list", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Job>>() {}.getType();
        ArrayList<Job> jobList = gson.fromJson(json, type);

        if (jobList == null) {
            jobList = new ArrayList<>();
        }

        Job job = new Job(title, company, "غير محدد", description, salary);
        jobList.add(job);

        String updatedJson = gson.toJson(jobList);
        prefs.edit().putString("jobs_list", updatedJson).apply();

        Toast.makeText(this, "تم حفظ الوظيفة", Toast.LENGTH_SHORT).show();
        finish();
    }
}
