package com.example.jobly;

public class JobDetailsResponse {
    private int id;
    private String title;
    private String description;
    private String requirements;
    private String company;

    public JobDetailsResponse(int id, String title, String description, String requirements, String company) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.company = company;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
