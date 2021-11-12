package com.example.universitiesandapplicants.model.request;

import lombok.Data;

@Data
public class EnrolleeByUniversityStatisticsRequest {

    private String universityName;
    private String direction;
}
