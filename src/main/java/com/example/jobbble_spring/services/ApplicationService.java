package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dtos.ApplicationRequest;
import com.example.jobbble_spring.dtos.ApplicationResponse;
import com.example.jobbble_spring.dtos.ApplicationStepRequest;
import com.example.jobbble_spring.entities.Application;
import com.example.jobbble_spring.entities.ApplicationStep;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.exceptions.NotFoundException;
import com.example.jobbble_spring.mappers.ApplicationMapper;
import com.example.jobbble_spring.repositories.ApplicationRepository;
import com.example.jobbble_spring.repositories.CompanyRepository;
import com.example.jobbble_spring.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    private CurrentUserUtils currentUserUtils;
    @Autowired
    private ApplicationMapper applicationMapper;

    public List<ApplicationResponse> getAllUserApplications() {
        User currentUser = currentUserUtils.getCurrentUser();

        return applicationRepository.findAllByApplicant(currentUser)
                .stream()
                .map(applicationMapper::toResponse)
                .toList();
    }

    public ApplicationResponse createApplication(ApplicationRequest applicationRequest) {
        User currentUser = currentUserUtils.getCurrentUser();
        Company company = companyRepository.findByIdAndCreator(applicationRequest.getCompanyId(), currentUser)
                .orElseThrow(() -> new NotFoundException("Company not found"));

        Application application = applicationMapper.toEntity(applicationRequest);
        application.setApplicant(currentUser);
        application.setCompany(company);
        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toResponse(savedApplication);
    }

    public ApplicationResponse addApplicationStep(Long applicationId, ApplicationStepRequest applicationStepRequest) {
        User currentUser = currentUserUtils.getCurrentUser();

        Application application = applicationRepository.findByIdAndApplicant(applicationId, currentUser)
                .orElseThrow(() -> new NotFoundException("Application not found"));

        ApplicationStep applicationStep = applicationMapper.toStepEntity(applicationStepRequest);
        applicationStep.setApplication(application);

        application.getSteps().add(applicationStep);
        Application updatedApplication = applicationRepository.save(application);
        return applicationMapper.toResponse(updatedApplication);
    }

    public ApplicationResponse getApplicationById(Long id) {
        User currentUser = currentUserUtils.getCurrentUser();

        Application application = applicationRepository.findByIdAndApplicant(id, currentUser)
                .orElseThrow(() -> new NotFoundException("Application not found."));
        return applicationMapper.toResponse(application);
    }

    public void deleteApplication(Long id) {
        User currentUser = currentUserUtils.getCurrentUser();

        Application application = applicationRepository.findByIdAndApplicant(id, currentUser)
                .orElseThrow(() -> new NotFoundException("Application not found."));
        applicationRepository.delete(application);
    }

    public ApplicationResponse updateApplication(Long id, ApplicationRequest applicationRequest) {
        User currentUser = currentUserUtils.getCurrentUser();

        Application application = applicationRepository.findByIdAndApplicant(id, currentUser)
                .orElseThrow(() -> new NotFoundException("Application not found."));

        if (applicationRequest.getTitle() != null) {
            application.setTitle(applicationRequest.getTitle());
        }
        if (applicationRequest.getDescription() != null) {
            application.setDescription(applicationRequest.getDescription());
        }
        if (applicationRequest.getStatus() != null) {
            application.setStatus(applicationRequest.getStatus());
        }

        Application updatedApplication = applicationRepository.save(application);
        return applicationMapper.toResponse(updatedApplication);
    }
}
