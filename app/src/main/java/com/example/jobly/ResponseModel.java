package com.example.jobly;

public class ResponseModel {
    private boolean success;
    private String message;
    private String token; // اختياري

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}

