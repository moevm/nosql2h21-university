package com.example.universitiesandapplicants.model.request;

import lombok.Data;

@Data
public class EnrolleeByUniversityFilterRequest {

    private String firstName = "";
    private String lastName = "";
    private String patronymic = "";
    private String agreement = "";
    private String formOfEducation = "";
    private String directionOfStudy = "";
    private Integer sumOfEgeResultsFrom = 0;
}
