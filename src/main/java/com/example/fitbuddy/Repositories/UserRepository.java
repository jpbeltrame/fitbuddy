package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.UserFitbuddy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserFitbuddy, String> {

    @Query("{username:  '?0'}")
    UserFitbuddy findUserByEmail(String username);

}
