package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.Buddy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BuddyRepository extends MongoRepository<Buddy, String> {
    @Query("{userId:  '?0'}")
    Buddy findBuddyByUserId(String userId);
}
