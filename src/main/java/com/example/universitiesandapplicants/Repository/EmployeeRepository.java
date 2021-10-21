package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
