package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeadNote;

public interface LeadNoteRepository extends JpaRepository<LeadNote, Long> {

    List<LeadNote> findByLeadId(Long leadId);
}