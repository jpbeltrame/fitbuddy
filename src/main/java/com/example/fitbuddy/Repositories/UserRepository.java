package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.UserFitbuddy;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserFitbuddy, String> {

    @Query("{username:  '?0', status: 'active'}")
    UserFitbuddy findUserByEmail(String username);

    @Query("{username:  '?0'}")
    UserFitbuddy findUserByEmailIgnoreStatus(String username);

    @Aggregation(pipeline = {
            "{ '$match': {subscriptionType: 'buddy', $or: [ " +
                    "{'preferences.gender':  '?0', }, " +
                    "{'preferences.trainingObjective': '?1' } ] } }",
            "{ '$sort' : { 'city' : 1 } }",
            "{ '$limit' : 10 }"

    })
    List<UserFitbuddy> findUserByGender(String gender, String trainingObjective);

    @Aggregation(pipeline = {
            "{ '$match': {'username':  {'$regex': '?0'}} }",
            "{ '$sort' : { 'city' : 1 } }",
            "{ '$limit' : 10 }"

    })
    List<UserFitbuddy> searchByUserName(String search);

}
