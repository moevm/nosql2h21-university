package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.University;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UniversityRepository extends MongoRepository<University, String> {
}
