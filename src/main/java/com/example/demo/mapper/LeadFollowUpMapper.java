package com.example.demo.mapper;

import com.example.demo.dto.LeadFollowUpRequestDTO;
import com.example.demo.dto.LeadFollowUpResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadFollowUp;

public class LeadFollowUpMapper {

    public static LeadFollowUp toEntity(
            LeadFollowUpRequestDTO dto,
            Lead lead) {

        LeadFollowUp followUp = new LeadFollowUp();

        followUp.setLead(lead);
        followUp.setFollowUpDate(dto.getFollowUpDate());
        followUp.setFollowUpTime(dto.getFollowUpTime());
        followUp.setType(dto.getType());
        followUp.setNotes(dto.getNotes());

        return followUp;
    }

    public static LeadFollowUpResponseDTO toResponseDTO(
            LeadFollowUp followUp) {

        LeadFollowUpResponseDTO dto = new LeadFollowUpResponseDTO();

        dto.setId(followUp.getId());

        if (followUp.getLead() != null) {
            dto.setLeadId(followUp.getLead().getId());
        }

        dto.setFollowUpDate(followUp.getFollowUpDate());
        dto.setFollowUpTime(followUp.getFollowUpTime());
        dto.setType(followUp.getType());
        dto.setNotes(followUp.getNotes());
        dto.setStatus(followUp.getStatus());
        dto.setCreatedAt(followUp.getCreatedAt());
        dto.setUpdatedAt(followUp.getUpdatedAt());

        return dto;
    }
}