package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.dto.ApplicationResponse;
import com.example.jobbble_spring.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public List<ApplicationResponse> getAllUserApplications() {
        return applicationService.getAllUserApplications();
    }
}
