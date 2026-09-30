package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeadCommercial;

public interface LeadCommercialRepository
        extends JpaRepository<LeadCommercial, Long> {

    Optional<LeadCommercial> findByLeadId(Long leadId);
}