package com.example.jobly;

import java.util.List;

public class FavoriteJobsResponse {
    private List<Job> favoriteJobs;

    public List<Job> getFavoriteJobs() {
        return favoriteJobs;
    }

    public void setFavoriteJobs(List<Job> favoriteJobs) {
        this.favoriteJobs = favoriteJobs;
    }

    public static class Job {
        private int id;
        private String title;
        private String company;

        // Getters and Setters
    }
}
