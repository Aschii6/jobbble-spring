package com.example.jobbble_spring.repositories;

import com.example.jobbble_spring.entities.Application;
import com.example.jobbble_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByApplicant(User applicant);
}
