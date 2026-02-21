package com.example.jobbble_spring.services;

import com.example.jobbble_spring.dtos.CompanyResponse;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.exceptions.NotFoundException;
import com.example.jobbble_spring.mappers.CompanyMapper;
import com.example.jobbble_spring.repositories.CompanyRepository;
import com.example.jobbble_spring.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CompanyResponse getCompanyById(Long id) {
        User currentUser = currentUserUtils.getCurrentUser();

        Company company = companyRepository.findByIdAndCreator(id, currentUser)
                .orElseThrow(() -> new NotFoundException("Company not found."));
        return companyMapper.toResponse(company);
    }

    public void deleteCompany(Long id) {
        User currentUser = currentUserUtils.getCurrentUser();

        Company company = companyRepository.findByIdAndCreator(id, currentUser)
                .orElseThrow(() -> new NotFoundException("Company not found."));
        companyRepository.delete(company);
    }
}
