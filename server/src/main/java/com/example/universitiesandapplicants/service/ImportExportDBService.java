package com.example.universitiesandapplicants.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface ImportExportDBService {

    String exportDB() throws MalformedURLException;
    String importDB(MultipartFile file);
}
