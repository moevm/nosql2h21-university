package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findAllByUniversityId(String id);

    Employee findByEmail(String email);

    Boolean existsByEmail(String email);
}
