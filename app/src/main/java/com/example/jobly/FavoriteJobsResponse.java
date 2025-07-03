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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}
