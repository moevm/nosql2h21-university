package com.example.universitiesandapplicants.model.request;

import lombok.Data;

@Data
public class UniversityFilterRequest {

    private String name = "";
    private String city = "";
    private String direction = "";
}
