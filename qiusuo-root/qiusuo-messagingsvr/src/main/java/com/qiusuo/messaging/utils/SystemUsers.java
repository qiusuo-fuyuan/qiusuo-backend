package com.qiusuo.messaging.utils;

public enum SystemUsers {

    ADMIN("admin");

    SystemUsers(String username) {
        this.username = username;
    }

    private String username;

    public String getUsername() {
        return this.username;
    }
}
