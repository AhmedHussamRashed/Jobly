package com.example.jobly;

import java.util.List;

public class JobResponse {

    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public static class Job {

        private int id;
        private String title;
        private String company;
        private String location;

        // Constructor
        public Job(int id, String title, String company, String location) {
            this.id = id;
            this.title = title;
            this.company = company;
            this.location = location;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCompany() {
            return company;
        }

        public String getLocation() {
            return location;
        }

        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
