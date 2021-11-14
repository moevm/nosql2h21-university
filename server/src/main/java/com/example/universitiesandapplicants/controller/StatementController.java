package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.StatementRequestModel;
import com.example.universitiesandapplicants.model.respose.StatementResponseModel;
import com.example.universitiesandapplicants.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/statements")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatementController {

    @Autowired
    StatementService statementService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addStatement(@RequestBody StatementRequestModel statementRequestModel) {
        statementService.addStatement(statementRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StatementResponseModel> getStatements() {
        return statementService.getStatements();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateStatement(@PathVariable String id, @RequestBody StatementRequestModel statementRequestModel) {
        statementService.updateStatement(id, statementRequestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{enrolleeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StatementResponseModel> getStatementsByEnrolleeId(@PathVariable String enrolleeId) {
        return statementService.getStatementsByEnrolleeId(enrolleeId);
    }

    @PostMapping(path = "/find", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public StatementResponseModel getStatementByEnrolleeIdAndUniversityId(@RequestBody StatementByIdRequest request) {
        return statementService.getStatementByEnrolleeIdAndUniversityId(request.getEnrolleeId(), request.getUniversityId());
    }
}
