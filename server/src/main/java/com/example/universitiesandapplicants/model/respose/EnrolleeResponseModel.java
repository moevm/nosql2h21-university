package com.example.universitiesandapplicants.model.respose;

import com.example.universitiesandapplicants.entity.EGEresults;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EnrolleeResponseModel {
    private String id;
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
    private Boolean isStatementExists;
}
