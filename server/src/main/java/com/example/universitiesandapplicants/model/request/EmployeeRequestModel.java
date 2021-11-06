package com.example.universitiesandapplicants.model.request;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
public class EmployeeRequestModel{

    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;
    private Date DOB;
    private String universityId;
}
