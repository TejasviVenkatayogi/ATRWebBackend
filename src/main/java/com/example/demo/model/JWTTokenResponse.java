package com.example.demo.model;

public class JWTTokenResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JWTTokenResponse(String token) {
        this.token = token;
    }
}
