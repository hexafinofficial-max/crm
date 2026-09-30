package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LeadStatusHistoryResponseDTO;
import com.example.demo.dto.LeadStatusUpdateRequestDTO;

public interface LeadStatusHistoryService {

    LeadStatusHistoryResponseDTO updateLeadStatus(
            Long leadId,
            LeadStatusUpdateRequestDTO request);

    List<LeadStatusHistoryResponseDTO> getStatusHistoryByLeadId(
            Long leadId);
}