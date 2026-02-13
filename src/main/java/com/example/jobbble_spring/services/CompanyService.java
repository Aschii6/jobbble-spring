package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dto.CompanyResponse;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.mappers.CompanyMapper;
import com.example.jobbble_spring.repositories.CompanyRepository;
import com.example.jobbble_spring.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyResponse> getAllOwnerCompanies() {
        User currentUser = getCurrentUser();

        return companyRepository.findAllByOwner(currentUser)
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CompanyResponse createCompany(Company company) {
        User currentUser = getCurrentUser();

        company.setOwner(currentUser);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponse(savedCompany);
    }

    private @NonNull User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated.");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails == null) {
            throw new RuntimeException("User details not found.");
        }
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
