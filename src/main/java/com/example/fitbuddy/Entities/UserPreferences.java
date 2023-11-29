package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferences {
    private String gender;
    private String[] trainingDays;
    private String trainingObjective;
    private LocalTime startSession;
    private LocalTime endSession;

    @Override
    public String toString() {
        return getGender()+ Arrays.toString(getTrainingDays())+getTrainingObjective()+getStartSession()+getEndSession();
    }
}
