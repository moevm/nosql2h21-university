package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.request.UniversityRequestModel;
import com.example.universitiesandapplicants.model.respose.UniversityResponseModel;
import com.example.universitiesandapplicants.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/universities")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addUniversity(@RequestBody UniversityRequestModel universityRequestModel) {
        universityService.addUniversity(universityRequestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UniversityResponseModel> getUniversities() {
        return universityService.getUniversities();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateUniversity(@PathVariable String id, @RequestBody UniversityRequestModel universityRequestModel) {
        universityService.updateUniversity(id, universityRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
