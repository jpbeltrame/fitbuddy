package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("buddies")
public class Buddy {

    @Id
    private String id;
    private String userId;
    private String personalId;
    private double score;
    private List<Reward> rewards;
    private List<String> buddiesIds;
}
