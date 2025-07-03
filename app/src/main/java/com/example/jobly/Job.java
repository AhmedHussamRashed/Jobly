package com.example.jobly;

public class Job {
    private String title;
    private String company;
    private String location;
    private String description;
    private String salary;

    // Constructor
    public Job(String title, String company, String location, String description, String salary) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.salary = salary;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getSalary() {
        return salary;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
