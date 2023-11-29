package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Buddy {

    private String userId;
    private String personalId;
    private double score;
    private ArrayList<Reward> rewards;
    private ArrayList<String> buddiesIds;
}
