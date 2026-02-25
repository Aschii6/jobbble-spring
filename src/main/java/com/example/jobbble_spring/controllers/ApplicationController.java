package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.dtos.ApplicationRequest;
import com.example.jobbble_spring.dtos.ApplicationResponse;
import com.example.jobbble_spring.dtos.ApplicationStepRequest;
import com.example.jobbble_spring.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest applicationRequest) {
        return applicationService.createApplication(applicationRequest);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateApplication(@PathVariable Long id, @RequestBody ApplicationRequest applicationRequest) {
        return applicationService.updateApplication(id, applicationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

    @PostMapping("/{applicationId}/steps")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse addApplicationStep(@PathVariable Long applicationId, @RequestBody ApplicationStepRequest applicationStepRequest) {
        return applicationService.addApplicationStep(applicationId, applicationStepRequest);
    }
}
