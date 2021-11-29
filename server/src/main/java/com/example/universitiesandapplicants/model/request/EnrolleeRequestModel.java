package com.example.universitiesandapplicants.model.request;

import com.example.universitiesandapplicants.entity.EGEresults;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class EnrolleeRequestModel {

    @NotBlank(message = "email cannot be empty")
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;

    @NotBlank(message = "password cannot be empty")
    private String password;
    private Date DOB;
    private String school;
    private String city;
    private List<EGEresults> egeResults;
    private List<String> individualAchievements;

}
