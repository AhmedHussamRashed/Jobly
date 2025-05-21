package com.example.jobly;

public class Application {
    private String title;
    private String company;
    private String status;

    public Application(String title, String company, String status) {
        this.title = title;
        this.company = company;
        this.status = status;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
