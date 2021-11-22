package com.example.universitiesandapplicants.service.impl;

import com.example.universitiesandapplicants.entity.Enrollee;
import com.example.universitiesandapplicants.entity.Statement;
import com.example.universitiesandapplicants.model.request.StatementRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.StatementResponseModel;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.StatementRepository;
import com.example.universitiesandapplicants.service.StatementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    StatementRepository repository;

    @Autowired
    EnrolleeRepository enrolleeRepository;

    @Override
    public void addStatement(StatementRequestModel statementRequestModel) {
        String enrolleeId = statementRequestModel.getEnrolleeId();
        Enrollee enrollee = enrolleeRepository.findById(enrolleeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollee with ID: " + enrolleeId + " not found"));
        enrollee.setIsStatementExists(true);
        enrolleeRepository.save(enrollee);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        repository.save(modelMapper.map(statementRequestModel, Statement.class));
    }

    @Override
    public List<StatementResponseModel> getStatements() {
        return repository.findAll().stream()
                .map(statement -> new ModelMapper().map(statement, StatementResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatement(String id, StatementRequestModel statementRequestModel) {
        Statement statement = new Statement(statementRequestModel);
        statement.setId(id);

        repository.save(statement);
    }

    @Override
    public List<StatementResponseModel> getStatementsByEnrolleeId(String enrolleeId) {
        return repository.findAllByEnrolleeId(enrolleeId).stream()
                .map(statement -> new ModelMapper().map(statement, StatementResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StatementResponseModel getStatementByEnrolleeIdAndUniversityId(String enrolleeId, String universityId) {
        if (enrolleeId == null || universityId == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "EnrolleeId and UniversityId must not be null");
        }

        return new ModelMapper()
                .map(repository.findByEnrolleeIdAndUniversityId(enrolleeId, universityId), StatementResponseModel.class);
    }
}
