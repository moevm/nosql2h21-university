package com.example.universitiesandapplicants.service;

import com.example.universitiesandapplicants.entity.University;
import com.example.universitiesandapplicants.model.request.UniversityRequestModel;
import com.example.universitiesandapplicants.model.respose.UniversityResponseModel;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    UniversityRepository repository;

    @Override
    public void addUniversity(UniversityRequestModel universityRequestModel) {
        repository.save(new ModelMapper().map(universityRequestModel, University.class));
    }

    @Override
    public List<UniversityResponseModel> getUniversities() {
        return repository.findAll().stream()
                .map(university -> new ModelMapper().map(university, UniversityResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUniversity(String id, UniversityRequestModel universityRequestModel) {
        University university = new ModelMapper().map(universityRequestModel, University.class);
        university.setId(id);

        repository.save(university);
    }
}
