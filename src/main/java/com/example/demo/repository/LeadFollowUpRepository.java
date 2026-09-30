package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeadFollowUp;

public interface LeadFollowUpRepository extends JpaRepository<LeadFollowUp, Long> {

}