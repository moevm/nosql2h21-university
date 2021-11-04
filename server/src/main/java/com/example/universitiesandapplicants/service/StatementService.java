package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.model.request.StatementRequestModel;
import com.example.universitiesandapplicants.model.request.UniversityRequestModel;
import com.example.universitiesandapplicants.model.respose.StatementResponseModel;
import com.example.universitiesandapplicants.model.respose.UniversityResponseModel;

import java.util.List;

public interface StatementService {
    void addStatement(StatementRequestModel statementRequestModel);
    List<StatementResponseModel> getStatements();
    void updateStatement(String id, StatementRequestModel statementRequestModel);
}
