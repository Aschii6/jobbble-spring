package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dto.CompanyResponse;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.mappers.CompanyMapper;
import com.example.jobbble_spring.repositories.CompanyRepository;
import com.example.jobbble_spring.repositories.UserRepository;
import com.example.jobbble_spring.utils.CurrentUserUtils;
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
    private CompanyMapper companyMapper;
    @Autowired
    private CurrentUserUtils currentUserUtils;

    public List<CompanyResponse> getAllUserCompanies() {
        User currentUser = currentUserUtils.getCurrentUser();

        return companyRepository.findAllByCreator(currentUser)
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    public CompanyResponse createCompany(Company company) {
        User currentUser = currentUserUtils.getCurrentUser();

        company.setCreator(currentUser);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponse(savedCompany);
    }
}
