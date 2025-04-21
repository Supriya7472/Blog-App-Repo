package com.example.Blog_App.security.jwt;

public enum TokenType {
    ACCESS("at"),
    REFRESH("rt");

    private final String type;

    TokenType(String type){
        this.type=type;
    }

    public String type() {
        return this.type;
    }
}
