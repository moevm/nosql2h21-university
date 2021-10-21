package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Employee;
import com.example.universitiesandapplicants.Model.University;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UniversityRepository extends MongoRepository<University, String> {
}
