package com.example.universitiesandapplicants.entity;

import lombok.Data;

@Data
public class EGEresults {

    private String subject;

    private Integer score;

    public EGEresults(String subject, Integer score) {
        this.subject = subject;
        this.score = score;
    }
}
