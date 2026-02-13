package com.example.jobbble_spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationRequest {
    private String title;
    private String description;
    private Long companyId;
}
