package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.Enrollee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {
}

