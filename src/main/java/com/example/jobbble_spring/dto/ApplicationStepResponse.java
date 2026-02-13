package com.example.jobbble_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationStepResponse {
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
}
