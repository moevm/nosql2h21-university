package com.example.universitiesandapplicants.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

    public Statement(String formOfEducation, String agreement, String directionOfStudy, String enrolleeId, String universityId) {
        this.formOfEducation = formOfEducation;
        this.agreement = agreement;
        this.directionOfStudy = directionOfStudy;
        this.enrolleeId = enrolleeId;
        this.universityId = universityId;
    }
}
