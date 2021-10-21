package com.example.universitiesandapplicants;

import com.example.universitiesandapplicants.Model.*;
import com.example.universitiesandapplicants.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class UniversitiesAndApplicantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesAndApplicantsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EnrolleeRepository enrolleeRepository, EGEresultsRepository egeresultsRepository,
                             EmployeeRepository employeeRepository, ContactRepository contactRepository,
                             UniversityRepository universityRepository) {
        return args -> {
            EGEresults egeresults = new EGEresults("russian", 96);

            Enrollee enrollee = new Enrollee(
                    "email@example.com",
                    "Ivan",
                    "Shishkin",
                    "Viktorovich",
                    "password",
                    new Date(),
                    "gimnasiya",
                    "SPB",
                    new ArrayList<>(List.of(egeresults)),
                    new ArrayList<>(List.of("GTO"))
            );

            Contact contacts = new Contact(
                    "some_email@mail.com",
                    "+7-932-123-43-23"
            );


            University university = new University(
                    "LETI",
                    "SPB",
                    contacts
            );

            contactRepository.save(contacts);

            universityRepository.save(university);

            Employee employee = new Employee(
                    "some_email@email.com",
                    "Vladislav",
                    "Vladislavov",
                    "Vladislavovich",
                    "pass",
                    new Date(),
                    university.getId()
            );

            employeeRepository.save(employee);

            egeresultsRepository.save(egeresults);

            enrolleeRepository.save(enrollee);

            System.out.println(employeeRepository.findById(employee.getId()).get());
        };
    }

}
