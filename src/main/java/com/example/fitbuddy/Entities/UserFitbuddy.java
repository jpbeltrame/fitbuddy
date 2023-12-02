package com.example.fitbuddy.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
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
    @Builder.Default
    private String status = "active";
    @Builder.Default
    private String role = "user";
}
