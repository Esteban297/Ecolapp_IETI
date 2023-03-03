package com.ecolapp.core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheck {
    @GetMapping
    public ResponseEntity<?> healthCheck() {
        return new ResponseEntity<>("Spring Boot App Running", HttpStatus.OK);
    }
}