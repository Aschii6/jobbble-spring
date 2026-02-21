package com.example.jobbble_spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 4, max = 50, message = "Company name must be between 4 and 50 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @Size(max = 50, message = "Location must be less than 50 characters")
    @Column(name = "location", nullable = false)
    private String location;

    @Size(max = 50, message = "Website URL must be less than 50 characters")
    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Size(max = 80, message = "Logo URL must be less than 80 characters")
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator; // The user who created the record

    @OneToMany(mappedBy = "company")
    private List<Application> applications;
}
