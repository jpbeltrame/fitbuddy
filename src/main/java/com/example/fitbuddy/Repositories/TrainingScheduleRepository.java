package com.example.fitbuddy.Repositories;

import com.example.fitbuddy.Entities.TrainingSchedule;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrainingScheduleRepository extends MongoRepository<TrainingSchedule, String> {
    @Aggregation(pipeline = {
            "{'$match': {buddiesIds:  '?0'}}",
            "{'$sort' : { '_id' : 1 } }",
    })
    List<TrainingSchedule> findByBuddyId(String userId);

    @Query("{_id:  '?0'}")
    TrainingSchedule getById(String id);
}