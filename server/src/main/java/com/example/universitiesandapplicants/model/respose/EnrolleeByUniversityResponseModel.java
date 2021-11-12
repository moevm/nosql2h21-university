package com.example.universitiesandapplicants.model.respose;

import com.example.universitiesandapplicants.entity.EGEresults;
import lombok.Data;

import java.util.List;

@Data
public class EnrolleeByUniversityResponseModel {
    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private List<EGEresults> egeResults;
    private List<String> individualAchievements;
    private Integer sumOfEgeResults;
    private String agreement;
    private String formOfEducation;
    private String directionOfStudy;
}
