package com.example.jobbble_spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String websiteUrl;
    private String logoUrl;
}

