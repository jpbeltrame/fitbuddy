package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("TrainingSchedules")
public class TrainingSchedule  {

    @Id
    private String id;
    private List<String> buddiesIds;
    private List<String> exercises;
    private String location;
    private String title;
    private LocalDate date;
    private LocalTime startAt;
    private LocalTime endAt;
    private String ownerUserId;
}

