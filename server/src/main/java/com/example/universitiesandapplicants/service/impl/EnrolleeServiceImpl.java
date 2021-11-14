package com.example.universitiesandapplicants.service.impl;

import com.example.universitiesandapplicants.entity.Enrollee;
import com.example.universitiesandapplicants.entity.Statement;
import com.example.universitiesandapplicants.entity.University;
import com.example.universitiesandapplicants.model.request.EnrolleeByUniversityStatisticsRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeFilterRequest;
import com.example.universitiesandapplicants.model.request.EnrolleeRequestModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeByUniversityResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeResponseModel;
import com.example.universitiesandapplicants.model.respose.EnrolleeStatisticsResponseModel;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.repository.StatementRepository;
import com.example.universitiesandapplicants.repository.UniversityRepository;
import com.example.universitiesandapplicants.service.EnrolleeService;
import com.mongodb.client.DistinctIterable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {

    @Autowired
    EnrolleeRepository repository;

    @Autowired
    StatementRepository statementRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<EnrolleeResponseModel> getEnrollees() {
        return repository.findAll().stream()
                .map(enrollee -> new ModelMapper().map(enrollee, EnrolleeResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addEnrollee(EnrolleeRequestModel enrolleeRequestModel) {
        if (repository.existsByEmail(enrolleeRequestModel.getEmail()) ||
                employeeRepository.existsByEmail(enrolleeRequestModel.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already taken");
        }

        Enrollee enrollee = new ModelMapper().map(enrolleeRequestModel, Enrollee.class);
        enrollee.setPassword(encoder.encode(enrollee.getPassword()));
        enrollee.countSumOfEgeResults();

        DistinctIterable<String> ids = mongoTemplate.getCollection("statements")
                .distinct("enrolleeId", String.class);

        for (String id : ids) {
            if (id.equals(enrollee.getId())) {
                enrollee.setIsStatementExists(true);
                break;
            }
        }

        repository.save(enrollee);
    }

    @Override
    public void updateEnrollee(String enrolleeId, EnrolleeRequestModel enrolleeRequestModel) {

        Enrollee enrollee = repository.findById(enrolleeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollee with ID: " + enrolleeId + " not found"));

        if (enrolleeRequestModel.getCity() != null) {
            enrollee.setCity(enrolleeRequestModel.getCity());
        }
        if (enrolleeRequestModel.getDOB() != null) {
            enrollee.setDOB(enrolleeRequestModel.getDOB());
        }
        if (enrolleeRequestModel.getFirstName() != null) {
            enrollee.setFirstName(enrolleeRequestModel.getFirstName());
        }
        if (enrolleeRequestModel.getLastName() != null) {
            enrollee.setLastName(enrolleeRequestModel.getLastName());
        }
        if (enrolleeRequestModel.getPatronymic() != null) {
            enrollee.setPatronymic(enrolleeRequestModel.getPatronymic());
        }
        if (enrolleeRequestModel.getSchool() != null) {
            enrollee.setSchool(enrolleeRequestModel.getSchool());
        }
        if (enrolleeRequestModel.getEgeResults() != null) {
            enrollee.setEgeResults(enrolleeRequestModel.getEgeResults());
            enrollee.countSumOfEgeResults();
        }

        repository.save(enrollee);
    }

    @Override
    public List<EnrolleeResponseModel> getEnrolleesByFilter(EnrolleeFilterRequest req) {

        if (req.getIsStatementExists() == null) {
            return repository.findAllByFilterExceptStatement(req.getFirstName(), req.getLastName(),
                            req.getPatronymic(), req.getCity(), req.getSumOfEgeResultsFrom())
                    .stream()
                    .map(enrollee -> new ModelMapper().map(enrollee, EnrolleeResponseModel.class))
                    .collect(Collectors.toList());
        }

        return repository.findAllByFilter(req.getFirstName(), req.getLastName(),
                        req.getPatronymic(), req.getCity(), req.getSumOfEgeResultsFrom(), req.getIsStatementExists())
                .stream()
                .map(enrollee -> new ModelMapper().map(enrollee, EnrolleeResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrolleeByUniversityResponseModel> getEnrolleesByUniversity(String universityId) {
        List<String> enrolleesInUniversityId = statementRepository.findAllByUniversityId(universityId)
                .stream()
                .map(Statement::getEnrolleeId)
                .collect(Collectors.toList());

        List<EnrolleeByUniversityResponseModel> returnValue = new ArrayList<>();

        for (String enrolleeId : enrolleesInUniversityId) {
            Enrollee enrollee = repository.findById(enrolleeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Enrollee in statemetns with ID: " + enrolleeId + " not found"));
            Statement statement = statementRepository.findByEnrolleeIdAndUniversityId(enrolleeId, universityId);

            EnrolleeByUniversityResponseModel responseModel = new ModelMapper().map(enrollee, EnrolleeByUniversityResponseModel.class);
            responseModel.setAgreement(statement.getAgreement());
            responseModel.setDirectionOfStudy(statement.getDirectionOfStudy());
            responseModel.setFormOfEducation(statement.getFormOfEducation());

            returnValue.add(responseModel);
        }

        return returnValue;
    }

    @Override
    public List<EnrolleeByUniversityResponseModel> getEnrolleesByUniversityAndFilter(String universityId, EnrolleeByUniversityFilterRequest req) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public EnrolleeStatisticsResponseModel getStatistics(String universityId, EnrolleeByUniversityStatisticsRequest req) {
        EnrolleeStatisticsResponseModel returnValue = new EnrolleeStatisticsResponseModel();

        DistinctIterable<String> ids = mongoTemplate.getCollection("statements")
                .distinct("universityId", String.class);
        List<String> listIds = new ArrayList<>();

        int numInYourUniversity = 0;

        for (String id : ids) {
            listIds.add(id);
            if (id.equals(universityId)) {
                numInYourUniversity++;
            }
        }

        returnValue.setNumInYourUniversity(numInYourUniversity);
        returnValue.setNumInOtherUniversities(listIds.size() - numInYourUniversity);
        returnValue.setNumWithoutStatement(repository.findAll().size() - listIds.size());

        String universityName = req.getUniversityName();
        if (universityName != null) {
            University university = universityRepository.findByName(universityName);
            if (university == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University with name: " + universityName + " not found");
            }
            returnValue.setNumInSpecifiedUniversity(statementRepository.findAllByUniversityId(university.getId()).size());
        }

        String directionName = req.getDirection();
        if (directionName != null) {
            returnValue.setNumInSpecifiedDirection(statementRepository.findAllByDirection(directionName).size());
        }

        return returnValue;
    }
}
