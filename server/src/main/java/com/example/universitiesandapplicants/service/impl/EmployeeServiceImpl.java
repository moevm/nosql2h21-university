package com.example.universitiesandapplicants.service.impl;

import com.example.universitiesandapplicants.entity.Employee;
import com.example.universitiesandapplicants.model.request.EmployeeRequestModel;
import com.example.universitiesandapplicants.model.respose.EmployeeResponseModel;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EnrolleeRepository enrolleeRepository;

    @Override
    public void addEmployee(EmployeeRequestModel employeeRequestModel) {
        if (repository.existsByEmail(employeeRequestModel.getEmail()) ||
                enrolleeRepository.existsByEmail(employeeRequestModel.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already taken");
        }

        Employee employee = new ModelMapper().map(employeeRequestModel, Employee.class);
        repository.save(employee);
    }

    @Override
    public List<EmployeeResponseModel> getEmployees() {
        return repository.findAll().stream()
                .map(employee -> new ModelMapper().map(employee, EmployeeResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateEmployee(String id, EmployeeRequestModel employeeRequestModel) {
        Employee employee = new ModelMapper().map(employeeRequestModel, Employee.class);
        employee.setId(id);

        repository.save(employee);
    }
}
