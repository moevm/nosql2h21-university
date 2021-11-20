package com.example.universitiesandapplicants;

import com.example.universitiesandapplicants.entity.properties.FileStorageProperties;
import com.example.universitiesandapplicants.service.ImportExportDBService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class UniversitiesAndApplicantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesAndApplicantsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ImportExportDBService importExportDBService) {
        return args -> {
//            importExportDBService.importDB("bigFile.json");
        };
    }

}
