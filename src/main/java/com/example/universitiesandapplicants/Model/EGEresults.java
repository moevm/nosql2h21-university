package com.example.universitiesandapplicants.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ege_results")
public class EGEresults {

    @Id
    private String id;

    private String subject;

    private Integer score;

    public EGEresults(String subject, Integer score) {
        this.subject = subject;
        this.score = score;
    }
}
