package com.example.universitiesandapplicants;

import com.example.universitiesandapplicants.entity.Employee;
import com.example.universitiesandapplicants.entity.Enrollee;
import com.example.universitiesandapplicants.entity.University;
import com.example.universitiesandapplicants.entity.properties.FileStorageProperties;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import com.example.universitiesandapplicants.service.ImportExportDBService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class UniversitiesAndApplicantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesAndApplicantsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UniversityRepository universityRepository,
                             EmployeeRepository employeeRepository,
                             EnrolleeRepository enrolleeRepository,
                             PasswordEncoder passwordEncoder) {
        return args -> {
            Enrollee enrollee1 = Enrollee.builder()
                    .email("enrollee1@email.com")
                    .firstName("Enrollee1")
                    .lastName("Enrollee1")
                    .patronymic("Enrollee1")
                    .password(passwordEncoder.encode("pass"))
                    .DOB(new Date())
                    .school("School1")
                    .city("City1")
                    .egeResults(new ArrayList<>())
                    .individualAchievements(new ArrayList<>())
                    .build();

            enrolleeRepository.save(enrollee1);

            University university1 = University.builder()
                    .name("University1")
                    .city("University1")
                    .email("University1@email.com")
                    .phone("phone")
                    .description("descr")
                    .faculties(new ArrayList<>())
                    .directionsOfStudy(List.of("first"))
                    .build();

            universityRepository.save(university1);

            Employee employee1 = Employee.builder()
                    .email("employee1@email.com")
                    .firstName("Employee1")
                    .lastName("Employee1")
                    .patronymic("Employee1")
                    .password(passwordEncoder.encode("pass"))
                    .DOB(new Date())
                    .universityId(university1.getId())
                    .build();

            employeeRepository.save(employee1);
        };
    }

}
