package com.example.universitiesandapplicants.model.request;

import com.example.universitiesandapplicants.entity.EGEresults;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

@Data
public class EnrolleeRequestModel {
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;
    private Date DOB;
    private String school;
    private String city;
    private List<EGEresults> egeResults;

}
