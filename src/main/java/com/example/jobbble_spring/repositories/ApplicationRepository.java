package com.example.jobbble_spring.repositories;

import com.example.jobbble_spring.entities.Application;
import com.example.jobbble_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByApplicant(User applicant);
    Optional<Application> findByIdAndApplicant(Long id, User applicant);
}
