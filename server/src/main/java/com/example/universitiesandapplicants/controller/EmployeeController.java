package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.EmployeeRequestModel;
import com.example.universitiesandapplicants.model.respose.EmployeeResponseModel;
import com.example.universitiesandapplicants.model.respose.FileResponse;
import com.example.universitiesandapplicants.service.EmployeeService;
import com.example.universitiesandapplicants.service.ImportExportDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/employees")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ImportExportDBService importExportDBService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.addEmployee(employeeRequestModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeResponseModel> getEmployees() {
        return employeeService.getEmployees();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.updateEmployee(id, employeeRequestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/import")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = importExportDBService.importDB(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/export/")
//                .path(fileName)
                .toUriString();

        return new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportDb(HttpServletRequest request) {
        List<Resource> resources = importExportDBService.loadFilesAsResource();
        Resource resource = resources.get(0);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
