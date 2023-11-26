package com.example.fitbuddy.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Buddy extends User {

    String personalId;
    double score;
    ArrayList<Reward> rewards;
    ArrayList<String> buddiesIds;

    public Buddy(String username, String password, String id, String name, LocalDate enrollDate) {
        super(username, password, id, name, enrollDate);
    }


    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public ArrayList<String> getBuddiesIds() {
        return buddiesIds;
    }

    public void setBuddiesIds(ArrayList<String> buddiesIds) {
        this.buddiesIds = buddiesIds;
    }

    public void addReward(Reward reward){

    }
    public void removeReward(Reward reward){

    }
    public void addBuddyId(String uuid){

    }
    public void removeBuddyId(String uuid){

    }
}
