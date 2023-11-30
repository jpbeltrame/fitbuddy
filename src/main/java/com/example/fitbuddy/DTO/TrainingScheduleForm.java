package com.example.fitbuddy.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingScheduleForm {
    private String title;
    private String location;
    private LocalTime startAt;
    private LocalTime endAt;
    private LocalDate date;
}
