package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.model.request.EmployeeRequestModel;
import com.example.universitiesandapplicants.model.respose.EmployeeResponseModel;
import com.example.universitiesandapplicants.model.respose.FileResponse;
import com.example.universitiesandapplicants.service.EmployeeService;
import com.example.universitiesandapplicants.service.ImportExportDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    public FileResponse importDb(@RequestParam("file") MultipartFile file) {
        String fileName = importExportDBService.importDB(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/export/")
                .toUriString();

        return new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/export")
    public void exportDb(HttpServletResponse response) throws IOException {
        String myString = importExportDBService.exportDB();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=myFile.json");
        PrintWriter out = response.getWriter();
        out.println(myString);
        out.flush();
        out.close();
    }

}
