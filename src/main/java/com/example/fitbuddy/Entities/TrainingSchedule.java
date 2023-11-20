package com.example.fitbuddy.Entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrainingSchedule  {

    String id;
    private ArrayList<String> buddiesIds;
    private ArrayList<Exercise> exercises;
    LocalDateTime start_at;
    LocalDateTime end_at;

    public String getId() {
        return id;
    }

    public ArrayList<String> getBuddiesIds() {
        return buddiesIds;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public LocalDateTime getStart_at() {
        return start_at;
    }

    public void setStart_at(LocalDateTime start_at) {
        this.start_at = start_at;
    }

    public LocalDateTime getEnd_at() {
        return end_at;
    }

    public void addBuddyId(String uuid){

    }
    public void removeBuddyId(String uuid){

    }

    public void addExercise(Exercise ex){

    }
    public void removeExercise(Exercise ex){

    }
}

