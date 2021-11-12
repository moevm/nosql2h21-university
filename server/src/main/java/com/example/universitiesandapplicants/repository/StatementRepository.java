package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StatementRepository extends MongoRepository<Statement, String> {
    List<Statement> findAllByEnrolleeId(String enrolleeId);
    Statement findByEnrolleeIdAndUniversityId(String enrolleeId, String universityId);
    List<Statement> findAllByUniversityId(String universityId);

    @Query("{ 'directionOfStudy': {'$regex': ?0} }")
    List<Statement> findAllByDirection(String direction);
}
