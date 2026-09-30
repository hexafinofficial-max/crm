package com.example.demo.mapper;

import com.example.demo.dto.LeadActivityRequestDTO;
import com.example.demo.dto.LeadActivityResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadActivity;

public class LeadActivityMapper {

    public static LeadActivity toEntity(
            LeadActivityRequestDTO dto,
            Lead lead) {

        LeadActivity activity = new LeadActivity();

        activity.setLead(lead);
        activity.setType(dto.getType());
        activity.setActivityDateTime(dto.getActivityDateTime());
        activity.setDescription(dto.getDescription());

        return activity;
    }

    public static LeadActivityResponseDTO toResponseDTO(
            LeadActivity activity) {

        LeadActivityResponseDTO dto =
                new LeadActivityResponseDTO();

        dto.setId(activity.getId());

        if (activity.getLead() != null) {
            dto.setLeadId(activity.getLead().getId());
        }

        dto.setType(activity.getType());
        dto.setActivityDateTime(
                activity.getActivityDateTime());

        dto.setDescription(activity.getDescription());

        if (activity.getCreatedBy() != null) {

            dto.setCreatedById(
                    activity.getCreatedBy().getId());

            String firstName =
                    activity.getCreatedBy().getFirstName();

            String lastName =
                    activity.getCreatedBy().getLastName();

            String fullName = firstName;

            if (lastName != null && !lastName.isBlank()) {
                fullName = firstName + " " + lastName;
            }

            dto.setCreatedByName(fullName);
        }

        dto.setCreatedAt(activity.getCreatedAt());

        return dto;
    }
}