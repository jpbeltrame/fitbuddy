package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Preferences {
    private String gender;
    private String[] trainingDays;
    private String objective;
    private LocalTime startTime;
    private LocalTime endDate;
}
