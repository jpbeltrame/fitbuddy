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
    private Date birthDate;
    private String location;
    private String subscriptionType;

    public User(String id, String username, String password, String name, LocalDate enrollDate, Date birthDate, String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.enrollDate = enrollDate;
        this.birthDate = birthDate;
        this.location = location;
    }

    public User(String username, String password, String id, String name, LocalDate enrollDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.enrollDate = enrollDate;
    }


    public User(String username, String password, String name, Date birthDate, String subscriptionType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.subscriptionType =subscriptionType;

    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
