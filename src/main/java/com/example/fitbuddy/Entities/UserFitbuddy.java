package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserFitbuddy {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private LocalDate enrollDate;
    private Date birthDate;
    private String location;
    private String subscriptionType;

    public UserFitbuddy(String id, String username, String password, String name, LocalDate enrollDate, Date birthDate, String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.enrollDate = enrollDate;
        this.birthDate = birthDate;
        this.location = location;
    }

    public UserFitbuddy(String username, String password, String id, String name, LocalDate enrollDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.enrollDate = enrollDate;
    }


    public UserFitbuddy(String username, String password, String name, Date birthDate, String subscriptionType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.subscriptionType =subscriptionType;

    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
