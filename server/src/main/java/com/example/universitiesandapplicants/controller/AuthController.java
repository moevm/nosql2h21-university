package com.example.universitiesandapplicants.controller;

import com.example.universitiesandapplicants.entity.User;
import com.example.universitiesandapplicants.model.request.LoginRequest;
import com.example.universitiesandapplicants.model.respose.JwtResponseModel;
import com.example.universitiesandapplicants.repository.EmployeeRepository;
import com.example.universitiesandapplicants.repository.EnrolleeRepository;
import com.example.universitiesandapplicants.security.jwt.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EnrolleeRepository enrolleeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> autoLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = employeeRepository.findByEmail(authentication.getName());

        if (user == null) {
            user = enrolleeRepository.findByEmail(authentication.getName());
        }

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email: " + authentication.getName() + " not found");
        }

        if (!authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.ok().body(user.getId());
        }

        return ResponseEntity.badRequest().body("User is not found");
    }

    @PostMapping(path = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String email = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        User user = employeeRepository.findByEmail(email);

        if (user == null) {
            user = enrolleeRepository.findByEmail(email);
        }

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email: " + email + " not found");
        }

        JwtResponseModel payload = new JwtResponseModel(user);

        String payloadStr;

        try {
            payloadStr = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            payloadStr = "";
        }

        return "Bearer " + jwtUtils.generateJwtToken(payloadStr);
    }
}
