package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UniversityRepository extends MongoRepository<University, String> {
    @Query("{ 'name': {'$regex': ?0}, 'city': {'$regex': ?1}, 'directionsOfStudy': {'$regex': ?2}}")
    List<University> findAllByFilter(String name, String city, String direction);

    University findByName(String name);
}
