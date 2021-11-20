package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.*;
import com.example.universitiesandapplicants.model.respose.EnrolleeByUniversityResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeStatisticsResponseModel;
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

    @PostMapping(path = "/filter", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EnrolleeResponseModel> getEnrolleesByFilter(@RequestBody EnrolleeFilterRequest filterRequest) {
        return enrolleeService.getEnrolleesByFilter(filterRequest);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateEnrollee(@PathVariable String id, @RequestBody EnrolleeRequestModel enrolleeRequestModel) {
        enrolleeService.updateEnrollee(id, enrolleeRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/by-university/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EnrolleeByUniversityResponseModel> getEnrolleesByUniversity(@PathVariable String id) {
        return enrolleeService.getEnrolleesByUniversity(id);
    }

    @PostMapping(path = "/by-university/{id}/filter", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EnrolleeByUniversityResponseModel> getEnrolleesByUniversityAndFilter(@PathVariable String id,
                                                                                     @RequestBody EnrolleeByUniversityFilterRequest req) {

        // TODO
        return enrolleeService.getEnrolleesByUniversityAndFilter(id, req);
    }

    @PostMapping(path = "/by-university/{id}/statistics", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EnrolleeStatisticsResponseModel getStatistics(@PathVariable String id, @RequestBody EnrolleeByUniversityStatisticsRequest req) {
        return enrolleeService.getStatistics(id, req);
    }
}
