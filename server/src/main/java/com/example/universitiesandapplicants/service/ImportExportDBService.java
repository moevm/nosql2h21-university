package com.example.universitiesandapplicants.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportExportDBService {

    void exportDB();
    String importDB(MultipartFile file);
    List<Resource> loadFilesAsResource();
}
