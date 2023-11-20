package com.example.fitbuddy.Entities;

import java.time.LocalDate;
import java.util.Date;

public class Reward extends Buddy {
    String label;
    LocalDate acquiredAt;

    public Reward(String username, String password, String id, String name, LocalDate enrollDate, String location) {
        super(username, password, id, name, enrollDate, location);
    }

    public String getId(){
        return id;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getAcquiredAt() {
        return acquiredAt;
    }

    public void setAcquiredAt(LocalDate acquiredAt) {
        this.acquiredAt = acquiredAt;
    }
}
