package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LeadActivityRequestDTO;
import com.example.demo.dto.LeadActivityResponseDTO;

public interface LeadActivityService {

    LeadActivityResponseDTO createActivity(
            Long leadId,
            LeadActivityRequestDTO request);

    List<LeadActivityResponseDTO> getActivitiesByLeadId(
            Long leadId);

    LeadActivityResponseDTO getActivityById(
            Long id);

    LeadActivityResponseDTO updateActivity(
            Long id,
            LeadActivityRequestDTO request);

    void deleteActivity(Long id);
}