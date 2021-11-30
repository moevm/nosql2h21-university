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

    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    private String lastName;

    @NotBlank(message = "patronymic cannot be empty")
    private String patronymic;

    @NotBlank(message = "password cannot be empty")
    private String password;
    private Date DOB;
    private String school;

    @NotBlank(message = "city cannot be empty")
    private String city;
    private List<EGEresults> egeResults;
    private List<String> individualAchievements;

}
