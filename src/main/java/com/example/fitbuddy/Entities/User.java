package com.example.fitbuddy.Entities;

import java.time.LocalDate;
import java.util.Date;

public class User {
    String username;
    String password;
    String id;
    String name;
    LocalDate enrollDate;
    String location;

    public User(String username, String password, String id, String name, LocalDate enrollDate, String location) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.enrollDate = enrollDate;
        this.location = location;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
