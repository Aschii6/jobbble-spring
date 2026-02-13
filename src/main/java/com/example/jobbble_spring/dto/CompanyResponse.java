package com.example.jobbble_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyResponse {
    private String name;
    private String description;
    private String location;
    private String websiteUrl;
    private String logoUrl;
}

