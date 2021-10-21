package com.example.universitiesandapplicants.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String password;

    private Date DOB;

    private String universityId;


    public Employee(String email, String name, String surname, String patronymic, String password, Date DOB, String universityId) {
        this.email = email;
        this.firstName = name;
        this.lastName = surname;
        this.patronymic = patronymic;
        this.password = password;
        this.DOB = DOB;
        this.universityId = universityId;
    }
}
