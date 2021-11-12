package com.example.universitiesandapplicants.model.request;

import lombok.Data;

@Data
public class EnrolleeFilterRequest {

    private String firstName = "";
    private String lastName = "";
    private String patronymic = "";
    private String city = "";
    private Integer sumOfEgeResultsFrom = 0;
    private Boolean isStatementExists;
}
