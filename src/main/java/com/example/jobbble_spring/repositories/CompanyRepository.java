package com.example.jobbble_spring.repositories;

import com.example.jobbble_spring.entities.Company;
import com.example.jobbble_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByOwner(User owner);
}
