package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Buddy extends UserFitbuddy {

    private String personalId;
    private double score;
    private ArrayList<Reward> rewards;
    private ArrayList<String> buddiesIds;
}
