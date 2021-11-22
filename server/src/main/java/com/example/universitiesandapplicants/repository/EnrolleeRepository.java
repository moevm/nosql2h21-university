package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.Enrollee;
import com.example.universitiesandapplicants.entity.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {
    @Query("{ 'firstName': {'$regex': ?0}, 'lastName': {'$regex': ?1}, 'patronymic': {'$regex': ?2}, " +
            "'city': {'$regex': ?3}, 'sumOfEgeResults': {'$gte': ?4}, 'isStatementExists': {'$eq' : ?5} }")
    List<Enrollee> findAllByFilter(String firstName, String lastName, String patronymic, String city,
                                   Integer sumOfEgeResultsFrom, Boolean isStatementExists);

    @Query("{ 'firstName': {'$regex': ?0}, 'lastName': {'$regex': ?1}, 'patronymic': {'$regex': ?2}, " +
            "'city': {'$regex': ?3}, 'sumOfEgeResults': {'$gte': ?4} }")
    List<Enrollee> findAllByFilterExceptStatement(String firstName, String lastName, String patronymic, String city,
                                                  Integer sumOfEgeResultsFrom);

    Enrollee findByEmail(String email);

    Boolean existsByEmail(String email);
}

