package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Enrollee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {
}

