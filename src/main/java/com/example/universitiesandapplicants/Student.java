package com.example.universitiesandapplicants;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, String email, Gender gender, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.createdAt = createdAt;
    }
}
