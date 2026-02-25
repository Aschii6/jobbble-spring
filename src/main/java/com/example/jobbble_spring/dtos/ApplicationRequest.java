package com.example.jobbble_spring.dtos;

import com.example.jobbble_spring.entities.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationRequest {
    private String title;
    private String description;
    private ApplicationStatus status;
    private Long companyId;
}
