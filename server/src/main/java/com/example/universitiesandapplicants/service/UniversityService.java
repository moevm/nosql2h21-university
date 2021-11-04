package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.model.request.UniversityRequestModel;
import com.example.universitiesandapplicants.model.respose.UniversityResponseModel;

import java.util.List;

public interface UniversityService {
    void addUniversity(UniversityRequestModel universityRequestModel);
    List<UniversityResponseModel> getUniversities();
    void updateUniversity(String id, UniversityRequestModel universityRequestModel);
}
