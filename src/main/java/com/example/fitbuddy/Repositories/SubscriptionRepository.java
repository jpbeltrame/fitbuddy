package com.example.fitbuddy.Repositories;
import com.example.fitbuddy.DTO.SignupForm;
import com.example.fitbuddy.Entities.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    @Query("{type: '?0'}")
    List<Subscription> getByType(String subscriptionType);
}
