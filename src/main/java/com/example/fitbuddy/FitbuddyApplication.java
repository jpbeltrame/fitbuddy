package com.example.fitbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FitbuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitbuddyApplication.class, args);
    }
}
