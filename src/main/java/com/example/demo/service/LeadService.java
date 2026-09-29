package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LeadRequestDTO;
import com.example.demo.dto.LeadResponseDTO;

public interface LeadService {

    LeadResponseDTO createLead(LeadRequestDTO request);
    
    List<LeadResponseDTO> getAllLeads();

    LeadResponseDTO getLeadById(Long id);

    LeadResponseDTO updateLead(Long id, LeadRequestDTO request);

    void deleteLead(Long id);
}