package com.example.universitiesandapplicants.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "universities")
public class University {

    @Id
    private String id;

    private String name;

    private String city;

    private String email;

    private String phone;

    private String description;

    private List<String> faculties;

    private List<String> directionsOfStudy;

}
