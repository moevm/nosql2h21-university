package com.example.universitiesandapplicants.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "enrollees")
public class Enrollee {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String password;

    private Date DOB;

    private String school;

    private String city;

    private List<EGEresults> egeResults;

    private List<String> individualAchievements;

    private Integer sumOfEgeResults;

    @Builder.Default
    private Boolean isStatementExists = false;

    public void countSumOfEgeResults() {
        if (egeResults.size() == 0) {
            sumOfEgeResults = 0;
            return;
        }
        sumOfEgeResults = egeResults.stream().map(EGEresults::getScore).reduce(0, Integer::sum);
    }
}
