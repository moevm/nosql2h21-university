package com.example.universitiesandapplicants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class UniversitiesAndApplicantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesAndApplicantsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return args -> {
            Student student = new Student(
                    "Ivan",
                    "Shishkin",
                    "ivanshishh@gmail.com",
                    Gender.MALE,
                    LocalDateTime.now()
            );

            repository.save(student);

            System.out.println(repository.findById(student.getId()).get());
        };
    }

}
