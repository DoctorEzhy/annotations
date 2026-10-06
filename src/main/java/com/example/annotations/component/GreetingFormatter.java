package com.example.annotations.component;

public class GreetingFormatter {

    private final String appName;

    public GreetingFormatter(String appName) {
        this.appName = appName;
    }

    public String format(String userName) {
        return "[" + appName + "] Привет, " + userName + "!";
    }
}
