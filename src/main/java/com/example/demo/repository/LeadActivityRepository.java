package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeadActivity;

public interface LeadActivityRepository
        extends JpaRepository<LeadActivity, Long> {

    List<LeadActivity> findByLeadId(Long leadId);
}