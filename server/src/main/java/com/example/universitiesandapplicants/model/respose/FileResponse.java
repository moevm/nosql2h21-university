package com.example.universitiesandapplicants.model.respose;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
