package com.example.universitiesandapplicants.model.request;

import lombok.Data;

@Data
public class StatementByIdRequest {

    private String enrolleeId;
    private String universityId;
}
