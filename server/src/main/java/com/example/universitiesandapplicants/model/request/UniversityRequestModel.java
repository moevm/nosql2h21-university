package com.example.universitiesandapplicants.model.request;

import lombok.Data;

import java.util.List;

@Data
public class UniversityRequestModel {

    private String name;
    private String city;
    private String email;
    private String phone;
    private String description;
    private List<String> faculties;
    private List<String> directionsOfStudy;
}
