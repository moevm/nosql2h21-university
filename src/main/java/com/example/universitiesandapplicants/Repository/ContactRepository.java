package com.example.universitiesandapplicants.Repository;

import com.example.universitiesandapplicants.Model.Contact;
import com.example.universitiesandapplicants.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
