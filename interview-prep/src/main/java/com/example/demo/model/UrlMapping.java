package com.example.demo.model;

public class UrlMapping {

    private final String shortCode;
    private final String originalUrl;

    public UrlMapping(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
