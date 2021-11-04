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

//    @Bean
//    CommandLineRunner runner(EnrolleeRepository enrolleeRepository,
//                             EmployeeRepository employeeRepository,
//                             UniversityRepository universityRepository,
//                             StatementRepository statementRepository) {
//        return args -> {
//
//            Enrollee enrollee = new Enrollee(
//                    "email@example.com",
//                    "Ivan",
//                    "Shishkin",
//                    "Viktorovich",
//                    "password",
//                    new Date(),
//                    "gimnasiya",
//                    "SPB",
//                    Arrays.asList(new EGEresults("rus", 30), new EGEresults("math", 50)),
//                    List.of("GTO")
//            );
//
//            enrolleeRepository.save(enrollee);
//
//            University university = new University(
//                    "LETI",
//                    "SPB",
//                    "some_email@mail.com",
//                    "+7-932-123-43-23",
//                    "We are the best! (no)",
//                    List.of("Первый факультет", "Второй"),
//                    List.of("Первое направление", "Второе")
//            );
//
//
//            universityRepository.save(university);
//
//            Employee employee = new Employee(
//                    "some_email@email.com",
//                    "Vladislav",
//                    "Vladislavov",
//                    "Vladislavovich",
//                    "pass",
//                    new Date(),
//                    university.getId()
//            );
//
//            employeeRepository.save(employee);
//
//            Statement statement = new Statement(
//                     "formOfEducation",
//                    "agreement",
//                    "directionOfStudy",
//                    enrollee.getId(),
//                    university.getId()
//            );
//
//            statementRepository.save(statement);
//
//
//        };
//    }

}
