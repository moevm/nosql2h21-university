package com.example.universitiesandapplicants.service.impl;


import com.example.universitiesandapplicants.entity.*;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.StatementRepository;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import com.example.universitiesandapplicants.service.ImportExportDBService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ImportExportDBServiceImpl implements ImportExportDBService {

    private final String PATH_TO_RESOURCES = Paths.get("src", "main", "resources").toFile().getAbsolutePath();

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

    private static final Map<String, String> fileNames = new HashMap<>() {{
        put("employees", "employees.json");
        put("enrollees", "enrollees.json");
        put("statements", "statements.json");
        put("universities", "universities.json");

    }};

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void exportDB() {
        save();
    }

    @Override
    public String importDB(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        read(fileName);
        return fileName;
    }

    private void save(){
        JsonEntity jsonEntity = new JsonEntity();

        jsonEntity.setEmployees(employeeRepository.findAll());
        jsonEntity.setEnrollees(enrolleeRepository.findAll());
        jsonEntity.setStatements(statementRepository.findAll());
        jsonEntity.setUniversities(universityRepository.findAll());

        try {
            Writer writer = new FileWriter(String.format("%s/%s.json", PATH_TO_RESOURCES, "bigFile"));
            gson.toJson(jsonEntity, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read(String filename){
        Type type = new TypeToken<JsonEntity>() {}.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(
                    String.format("%s/%s", PATH_TO_RESOURCES, filename)
            ));
            JsonEntity out = gson.fromJson(reader, type);

            enrolleeRepository.saveAll(out.getEnrollees());
            employeeRepository.saveAll(out.getEmployees());
            statementRepository.saveAll(out.getStatements());
            universityRepository.saveAll(out.getUniversities());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Resource> loadFilesAsResource() {
        List<Resource> resources = new ArrayList<>();
        try {
            for (Map.Entry<String, String> entry : fileNames.entrySet()) {
                String fileName = entry.getValue();
                Path filePath = Paths.get("src", "main", "resources").resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists()) {
                    resources.add(resource);
                } else {
                    throw new RuntimeException("File not found " + fileName);
                }
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File %s not found");
        }

        return resources;
    }
}
