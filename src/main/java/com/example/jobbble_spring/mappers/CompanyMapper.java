package com.example.jobbble_spring.mappers;

import com.example.jobbble_spring.dto.CompanyResponse;
import com.example.jobbble_spring.entities.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponse toResponse(Company company);
}

