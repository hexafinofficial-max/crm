package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.FollowUpStatusUpdateRequestDTO;
import com.example.demo.dto.LeadFollowUpRequestDTO;
import com.example.demo.dto.LeadFollowUpResponseDTO;

public interface LeadFollowUpService {

    LeadFollowUpResponseDTO createFollowUp(
            Long leadId,
            LeadFollowUpRequestDTO request);

    List<LeadFollowUpResponseDTO> getFollowUpsByLeadId(
            Long leadId);

    LeadFollowUpResponseDTO getFollowUpById(
            Long id);

    LeadFollowUpResponseDTO updateFollowUp(
            Long id,
            LeadFollowUpRequestDTO request);

    LeadFollowUpResponseDTO updateFollowUpStatus(
            Long id,
            FollowUpStatusUpdateRequestDTO request); 
    
    void deleteFollowUp(Long id);
}