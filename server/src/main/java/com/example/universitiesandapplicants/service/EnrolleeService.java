package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.model.request.EnrolleeFilterRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;

import java.util.List;

public interface EnrolleeService {
    List<EnrolleeResponseModel> getEnrollees();
    void addEnrollee(EnrolleeRequestModel enrolleeRequestModel);
    void updateEnrollee(String enrolleeId, EnrolleeRequestModel enrolleeRequestModel);
}
