package com.example.fitbuddy;

import com.example.fitbuddy.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FitbuddyApplication {

    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(FitbuddyApplication.class, args);
    }
}
