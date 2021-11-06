package com.example.universitiesandapplicants.service.impl;

import com.example.universitiesandapplicants.entity.Statement;
import com.example.universitiesandapplicants.model.request.StatementRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.StatementResponseModel;
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

    @Override
    public void addStatement(StatementRequestModel statementRequestModel) {
        repository.save(new ModelMapper().map(statementRequestModel, Statement.class));
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
}
