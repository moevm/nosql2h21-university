package com.example.universitiesandapplicants.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public University(String name, String city, String email, String phone, String description, List<String> faculties,
                      List<String> directionsOfStudy) {

        this.name = name;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.faculties = faculties;
        this.directionsOfStudy = directionsOfStudy;
    }
}
