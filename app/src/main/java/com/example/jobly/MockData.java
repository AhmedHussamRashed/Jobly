package com.example.jobly;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Job> getMockJobs() {
        List<Job> jobs = new ArrayList<>();

        jobs.add(new Job("Android Developer", "Tech Solutions", "Cairo, Egypt", "Develop and maintain Android applications.", "$1200/month"));
        jobs.add(new Job("UI/UX Designer", "Creative Studio", "Dubai, UAE", "Design user-friendly interfaces and user experiences.", "$1000/month"));
        jobs.add(new Job("Backend Developer", "Cloud Corp", "Riyadh, KSA", "Develop APIs and backend services using Java/Spring Boot.", "$1500/month"));
        jobs.add(new Job("Data Analyst", "Smart Data", "Amman, Jordan", "Analyze business data and provide insights.", "$1100/month"));

        return jobs;
    }
}
