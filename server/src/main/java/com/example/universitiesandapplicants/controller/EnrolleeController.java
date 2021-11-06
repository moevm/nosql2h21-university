package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.EnrolleeFilterRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.request.UniversityFilterRequest;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.UniversityResponseModel;
import com.example.universitiesandapplicants.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/enrollees")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addEnrollee(@RequestBody EnrolleeRequestModel enrolleeRequestModel) {
        enrolleeService.addEnrollee(enrolleeRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EnrolleeResponseModel> getEnrollees() {
        return enrolleeService.getEnrollees();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateEnrollee(@PathVariable String id, @RequestBody EnrolleeRequestModel enrolleeRequestModel) {
        enrolleeService.updateEnrollee(id, enrolleeRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}