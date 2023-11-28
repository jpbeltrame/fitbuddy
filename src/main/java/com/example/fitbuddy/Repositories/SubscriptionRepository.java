package com.example.fitbuddy.Repositories;
import com.example.fitbuddy.DTO.SignupForm;
import com.example.fitbuddy.Entities.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    @Query("{userId:  '?0'}")
    SignupForm findUserByUserId(String userId);
}
