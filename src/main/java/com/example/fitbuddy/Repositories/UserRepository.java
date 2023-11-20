package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:  '?0'}")
    User findUserByEmail(String username);

}
