package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeadStatusHistory;

public interface LeadStatusHistoryRepository
        extends JpaRepository<LeadStatusHistory, Long> {

    List<LeadStatusHistory> findByLeadId(Long leadId);
}