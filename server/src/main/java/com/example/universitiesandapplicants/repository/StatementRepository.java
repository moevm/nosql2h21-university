package com.example.universitiesandapplicants.repository;

import com.example.universitiesandapplicants.entity.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatementRepository extends MongoRepository<Statement, String> {
}
