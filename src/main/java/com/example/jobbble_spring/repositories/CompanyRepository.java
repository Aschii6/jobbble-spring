package com.example.jobbble_spring.repositories;

import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByCreator(User creator);
    Optional<Company> findByIdAndCreator(Long id, User creator);
}
