package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.UserFitbuddy;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserFitbuddy, String> {

    @Query("{username:  '?0'}")
    UserFitbuddy findUserByEmail(String username);

    @Aggregation(pipeline = {
            "{ '$match': {'preferences.gender':  '?0'} }",
            "{ '$sort' : { 'city' : 1 } }",
            "{ '$limit' : 10 }"

    })
    List<UserFitbuddy> findUserByGender(String gender);

}
