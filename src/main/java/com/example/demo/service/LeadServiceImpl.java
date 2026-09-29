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

        // DTO → Entity
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

        // Get all leads from database
        List<Lead> leads = leadRepository.findAll();

        // Entity → Response DTO
        return leads.stream()
                .map(LeadMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LeadResponseDTO getLeadById(Long id) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));

        return LeadMapper.toResponseDTO(lead);
    }

    @Override
    public LeadResponseDTO updateLead(Long id, LeadRequestDTO request) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));

        lead.setFirstName(request.getFirstName());
        lead.setLastName(request.getLastName());
        lead.setCompanyName(request.getCompanyName());
        lead.setEmail(request.getEmail());
        lead.setPhone(request.getPhone());

        lead.setAddressLine1(request.getAddressLine1());
        lead.setAddressLine2(request.getAddressLine2());
        lead.setCity(request.getCity());
        lead.setState(request.getState());
        lead.setCountry(request.getCountry());
        lead.setPincode(request.getPincode());

        lead.setSource(request.getSource());
        lead.setDescription(request.getDescription());

        lead.setUpdatedAt(LocalDateTime.now());

        Lead updatedLead = leadRepository.save(lead);

        return LeadMapper.toResponseDTO(updatedLead);
    }

    @Override
    public void deleteLead(Long id) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));

        leadRepository.delete(lead);
    }
}