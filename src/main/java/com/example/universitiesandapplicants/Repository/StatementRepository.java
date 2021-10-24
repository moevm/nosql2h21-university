package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatementRepository extends MongoRepository<Statement, String> {
}
