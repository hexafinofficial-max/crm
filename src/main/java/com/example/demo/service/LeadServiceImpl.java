package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeadRequestDTO;
import com.example.demo.dto.LeadResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadStatus;
import com.example.demo.mapper.LeadMapper;
import com.example.demo.repository.LeadRepository;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public LeadResponseDTO createLead(LeadRequestDTO request) {
    	

    	Lead lead = LeadMapper.toEntity(request);

        // New Lead default status
        lead.setStatus(LeadStatus.NEW);

        // Audit timestamps
        LocalDateTime now = LocalDateTime.now();
        lead.setCreatedAt(now);
        lead.setUpdatedAt(now);

        // Save Entity
        Lead savedLead = leadRepository.save(lead);

        // Entity → Response DTO
        return LeadMapper.toResponseDTO(savedLead);
    }
    @Override
    public List<LeadResponseDTO> getAllLeads() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LeadResponseDTO getLeadById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LeadResponseDTO updateLead(Long id, LeadRequestDTO request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteLead(Long id) {
        // TODO Auto-generated method stub
    }
}