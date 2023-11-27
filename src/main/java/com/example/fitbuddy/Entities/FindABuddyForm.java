package com.example.fitbuddy.Entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FindABuddyForm {
    public String gender;
    public List<String> trainingDays;
    public String objective;
    public String startSession;
    public String endSession;
    public FindABuddyForm(List<String> trainingDays, String startSession, String endSession) {
        this.trainingDays = trainingDays;
        this.startSession = startSession;
        this.endSession = endSession;
    }
}