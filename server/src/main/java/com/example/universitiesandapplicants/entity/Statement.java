package com.example.universitiesandapplicants.entity;


import com.example.universitiesandapplicants.model.request.StatementRequestModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "statements")
public class Statement {

    @Id
    private String id;

    private String formOfEducation;

    private String agreement;

    private String directionOfStudy;

    private String enrolleeId;

    private String universityId;

    public Statement(StatementRequestModel statementRequestModel) {
        this.formOfEducation = statementRequestModel.getFormOfEducation();
        this.agreement = statementRequestModel.getAgreement();
        this.directionOfStudy = statementRequestModel.getDirectionOfStudy();
        this.enrolleeId = statementRequestModel.getEnrolleeId();
        this.universityId = statementRequestModel.getUniversityId();
    }
}
