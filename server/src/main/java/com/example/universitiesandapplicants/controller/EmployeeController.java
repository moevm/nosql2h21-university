package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.EmployeeRequestModel;
import com.example.universitiesandapplicants.model.respose.EmployeeResponseModel;
import com.example.universitiesandapplicants.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employees")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.addEmployee(employeeRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeResponseModel> getEmployees() {
        return employeeService.getEmployees();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.updateEmployee(id, employeeRequestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
