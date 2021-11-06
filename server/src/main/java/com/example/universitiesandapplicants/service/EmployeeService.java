package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.model.request.EmployeeRequestModel;
import com.example.universitiesandapplicants.model.respose.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    void addEmployee(EmployeeRequestModel employeeRequestModel);
    List<EmployeeResponseModel> getEmployees();
    void updateEmployee(String id, EmployeeRequestModel employeeRequestModel);
}
