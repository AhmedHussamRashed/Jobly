package com.example.jobly;

import java.util.List;

public class CompaniesResponse {
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public static class Company {
        private int id;
        private String name;

        // Getters and Setters
    }
}
