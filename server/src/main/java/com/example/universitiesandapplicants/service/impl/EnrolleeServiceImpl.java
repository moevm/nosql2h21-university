package com.example.universitiesandapplicants.service.impl;

import com.example.universitiesandapplicants.entity.Enrollee;
import com.example.universitiesandapplicants.model.request.EnrolleeFilterRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.service.EnrolleeService;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {

    @Autowired
    EnrolleeRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<EnrolleeResponseModel> getEnrollees() {
        return repository.findAll().stream()
                .map(enrollee -> new ModelMapper().map(enrollee, EnrolleeResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addEnrollee(EnrolleeRequestModel enrolleeRequestModel) {
        Enrollee enrollee = new ModelMapper().map(enrolleeRequestModel, Enrollee.class);
        enrollee.countSumOfEgeResults();
        repository.save(enrollee);
    }

    @Override
    public void updateEnrollee(String enrolleeId, EnrolleeRequestModel enrolleeRequestModel) {

        Enrollee enrollee = repository.findById(enrolleeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollee with ID: " + enrolleeId + " not found"));

        if (enrolleeRequestModel.getCity() != null) {
            enrollee.setCity(enrolleeRequestModel.getCity());
        }
        if (enrolleeRequestModel.getDOB() != null) {
            enrollee.setDOB(enrolleeRequestModel.getDOB());
        }
        if (enrolleeRequestModel.getFirstName() != null) {
            enrollee.setFirstName(enrolleeRequestModel.getFirstName());
        }
        if (enrolleeRequestModel.getLastName() != null) {
            enrollee.setLastName(enrolleeRequestModel.getLastName());
        }
        if (enrolleeRequestModel.getPatronymic() != null) {
            enrollee.setPatronymic(enrolleeRequestModel.getPatronymic());
        }
        if (enrolleeRequestModel.getSchool() != null) {
            enrollee.setSchool(enrolleeRequestModel.getSchool());
        }
        if (enrolleeRequestModel.getEgeResults() != null) {
            enrollee.setEgeResults(enrolleeRequestModel.getEgeResults());
            enrollee.countSumOfEgeResults();
        }

        repository.save(enrollee);
    }
}
