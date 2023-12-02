package com.example.fitbuddy.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.web.SortDefault;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("PersonalTrainers")
public class PersonalTrainer {
    @Id
    private String id;
    private String userId;
    private String specialty;
}
