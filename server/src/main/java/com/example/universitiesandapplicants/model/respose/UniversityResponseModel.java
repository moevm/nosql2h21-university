package com.example.universitiesandapplicants.model.respose;

import lombok.Data;

import java.util.List;

@Data
public class UniversityResponseModel {

    private String id;
    private String name;
    private String city;
    private String email;
    private String phone;
    private String description;
    private List<String> faculties;
    private List<String> directionsOfStudy;
}
