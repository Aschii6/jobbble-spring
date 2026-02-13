package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dtos.ApplicationRequest;
import com.example.jobbble_spring.dtos.ApplicationResponse;
import com.example.jobbble_spring.entities.Application;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
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
        Company company = companyRepository.findById(applicationRequest.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Application application = applicationMapper.toEntity(applicationRequest);
        application.setApplicant(currentUser);
        application.setCompany(company);
        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toResponse(savedApplication);
    }
}
