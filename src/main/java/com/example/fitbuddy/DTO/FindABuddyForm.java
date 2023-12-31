package com.example.fitbuddy.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindABuddyForm {
    private String gender;
    private String[] trainingDays;
    private String trainingObjective;
    private LocalTime startSession;
    private LocalTime endSession;
}