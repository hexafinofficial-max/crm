package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Quotation;

public interface QuotationRepository
        extends JpaRepository<Quotation, Long> {

    List<Quotation> findByLeadId(Long leadId);

    Optional<Quotation> findByQuotationNumber(String quotationNumber);
}