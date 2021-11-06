package com.example.universitiesandapplicants;

import com.example.universitiesandapplicants.entity.*;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.StatementRepository;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class UniversitiesAndApplicantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesAndApplicantsApplication.class, args);
    }

}
