package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.model.request.EnrolleeByUniversityStatisticsRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeFilterRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeByUniversityResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeStatisticsResponseModel;

import java.util.List;

public interface EnrolleeService {
    List<EnrolleeResponseModel> getEnrollees();
    void addEnrollee(EnrolleeRequestModel enrolleeRequestModel);
    void updateEnrollee(String enrolleeId, EnrolleeRequestModel enrolleeRequestModel);
    List<EnrolleeResponseModel> getEnrolleesByFilter(EnrolleeFilterRequest req);
    List<EnrolleeByUniversityResponseModel> getEnrolleesByUniversity(String universityId);
    EnrolleeStatisticsResponseModel getStatistics(String universityId, EnrolleeByUniversityStatisticsRequest req);
}
