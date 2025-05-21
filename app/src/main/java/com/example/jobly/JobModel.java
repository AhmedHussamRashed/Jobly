package com.example.jobly;

public class JobModel {
    private String title;
    private String company;

    public JobModel(String title, String company) {
        this.title = title;
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }
}
