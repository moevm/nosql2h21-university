package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findAllByUniversityId(String id);
}
