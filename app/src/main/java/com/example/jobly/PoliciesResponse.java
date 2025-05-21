package com.example.jobly;

import java.util.List;

public class PoliciesResponse {
    private List<Policy> policies;

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public static class Policy {
        private int id;
        private String title;
        private String content;

        // Getters and Setters
    }
}
