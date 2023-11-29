package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingSchedule  {

    @Id
    private String id;
    private ArrayList<String> buddiesIds;
    private ArrayList<Exercise> exercises;
    private Date startAt;
    private Date endAt;
}

