package com.example.universitiesandapplicants.service.impl;


import com.example.universitiesandapplicants.entity.JsonEntity;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.StatementRepository;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import com.example.universitiesandapplicants.service.ImportExportDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Objects;


@Service
public class ImportExportDBServiceImpl implements ImportExportDBService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EnrolleeRepository enrolleeRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String exportDB() {
        return save();
    }

    @Override
    public String importDB(MultipartFile file) {
        read(file);

        return StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    }

    private String save() {
        JsonEntity jsonEntity = new JsonEntity();

        jsonEntity.setEmployees(employeeRepository.findAll());
        jsonEntity.setEnrollees(enrolleeRepository.findAll());
        jsonEntity.setStatements(statementRepository.findAll());
        jsonEntity.setUniversities(universityRepository.findAll());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(jsonEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void read(MultipartFile file) {
        Type type = new TypeToken<JsonEntity>() {
        }.getType();

        try {
            String content = new String(file.getBytes());
            JsonReader reader = new JsonReader(new StringReader(content));
            JsonEntity out = gson.fromJson(reader, type);

            enrolleeRepository.saveAll(out.getEnrollees());
            employeeRepository.saveAll(out.getEmployees());
            statementRepository.saveAll(out.getStatements());
            universityRepository.saveAll(out.getUniversities());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
