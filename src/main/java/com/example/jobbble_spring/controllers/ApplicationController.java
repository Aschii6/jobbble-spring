package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.dtos.ApplicationRequest;
import com.example.jobbble_spring.dtos.ApplicationResponse;
import com.example.jobbble_spring.dtos.ApplicationStepRequest;
import com.example.jobbble_spring.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest applicationRequest) {
        return applicationService.createApplication(applicationRequest);
    }

    @PostMapping("/{applicationId}/steps")
    public ApplicationResponse addApplicationStep(@PathVariable Long applicationId, @RequestBody ApplicationStepRequest applicationStepRequest) {
        return applicationService.addApplicationStep(applicationId, applicationStepRequest);
    }
}
