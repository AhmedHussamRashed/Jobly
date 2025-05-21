package com.example.jobly;

import java.util.List;

public class FaqsResponse {
    private List<Faq> faqs;

    public List<Faq> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<Faq> faqs) {
        this.faqs = faqs;
    }

    public static class Faq {
        private int id;
        private String question;
        private String answer;

        // Getters and Setters
    }
}
