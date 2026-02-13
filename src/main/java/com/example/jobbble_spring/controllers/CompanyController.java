package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.dto.CompanyResponse;
import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllOwnerCompanies() {
        return companyService.getAllOwnerCompanies();
    }

    @PostMapping
    public CompanyResponse createCompany(@RequestBody Company company) {
        System.out.println("Received company: " + company);
        return companyService.createCompany(company);
    }
}
