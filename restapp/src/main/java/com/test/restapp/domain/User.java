package com.test.restapp.domain;

public class User {

    private String name;

    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
