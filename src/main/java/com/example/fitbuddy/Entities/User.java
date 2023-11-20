package com.example.fitbuddy.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document("users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private LocalDate enrollDate;
    private String location;

    public User(String username, String password, String id, String name, LocalDate enrollDate, String location) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
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
