package com.example.jobbble_spring.dto;

import com.example.jobbble_spring.entities.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationResponse {
    private String title;
    private String description;
    private ApplicationStatus status;
    private CompanyResponse company;
    private List<ApplicationStepResponse> steps;
}
