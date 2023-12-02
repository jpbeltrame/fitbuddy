package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String city;
    private Date birthDate;
    private String location;
    private String subscriptionType;
    private UserPreferences preferences;
    private String status = "active";
    private String role = "user";
}
