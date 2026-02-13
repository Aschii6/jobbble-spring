package com.example.jobbble_spring.mappers;

import com.example.jobbble_spring.dtos.ApplicationRequest;
import com.example.jobbble_spring.dtos.ApplicationResponse;
import com.example.jobbble_spring.entities.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationResponse toResponse(Application application);
    Application toEntity(ApplicationRequest applicationRequest);
}
