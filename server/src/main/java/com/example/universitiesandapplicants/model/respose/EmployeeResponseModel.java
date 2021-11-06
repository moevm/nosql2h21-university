package com.example.universitiesandapplicants.model.respose;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponseModel {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;
    private Date DOB;
    private String universityId;
}
