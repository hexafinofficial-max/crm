package com.example.demo.service;

import com.example.demo.dto.LeadCommercialRequestDTO;
import com.example.demo.dto.LeadCommercialResponseDTO;

public interface LeadCommercialService {

    LeadCommercialResponseDTO createCommercial(
            Long leadId,
            LeadCommercialRequestDTO request);

    LeadCommercialResponseDTO getCommercialByLeadId(
            Long leadId);

    LeadCommercialResponseDTO updateCommercial(
            Long leadId,
            LeadCommercialRequestDTO request);

    void deleteCommercial(Long leadId);
}