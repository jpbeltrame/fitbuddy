package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.Buddy;
import com.example.fitbuddy.Entities.PersonalTrainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonalTrainerRepository extends MongoRepository<PersonalTrainer, String> {
    @Query("{userId:  '?0'}")
    PersonalTrainer findByUserID(String userId);
}
