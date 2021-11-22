package com.example.universitiesandapplicants.entity;

import lombok.Data;

import java.util.List;

@Data
public class JsonEntity {

    List<Employee> employees;
    List<Enrollee> enrollees;
    List<Statement> statements;
    List<University> universities;
}
