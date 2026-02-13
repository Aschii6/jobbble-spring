package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dto.ApplicationResponse;
import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.mappers.ApplicationMapper;
import com.example.jobbble_spring.repositories.ApplicationRepository;
import com.example.jobbble_spring.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
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
}
