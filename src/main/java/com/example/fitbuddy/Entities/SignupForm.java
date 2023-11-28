package com.example.fitbuddy.Entities;

import java.time.LocalDate;
import java.util.Date;

public class SignupForm {
    public String name;
    public String username;
    public String password;
    public String city;
    public Date birthDate;
    public String subscriptionType;
    public SignupForm(String name, String username, String password, String city, Date birthDate, String subscriptionType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.city = city;
        this.birthDate = birthDate;
        this.subscriptionType = subscriptionType;
    }
}