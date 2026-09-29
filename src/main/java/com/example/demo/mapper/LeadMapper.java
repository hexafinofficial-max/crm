package com.example.demo.mapper;

import com.example.demo.dto.LeadRequestDTO;
import com.example.demo.dto.LeadResponseDTO;
import com.example.demo.entity.Lead;

public class LeadMapper {

    public static Lead toEntity(LeadRequestDTO dto) {

        Lead lead = new Lead();

        lead.setFirstName(dto.getFirstName());
        lead.setLastName(dto.getLastName());
        lead.setCompanyName(dto.getCompanyName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());

        lead.setAddressLine1(dto.getAddressLine1());
        lead.setAddressLine2(dto.getAddressLine2());
        lead.setCity(dto.getCity());
        lead.setState(dto.getState());
        lead.setCountry(dto.getCountry());
        lead.setPincode(dto.getPincode());

        lead.setSource(dto.getSource());
        lead.setDescription(dto.getDescription());

        return lead;
    }

    public static LeadResponseDTO toResponseDTO(Lead lead) {

        LeadResponseDTO dto = new LeadResponseDTO();

        dto.setId(lead.getId());

        dto.setFirstName(lead.getFirstName());
        dto.setLastName(lead.getLastName());
        dto.setCompanyName(lead.getCompanyName());
        dto.setEmail(lead.getEmail());
        dto.setPhone(lead.getPhone());

        dto.setAddressLine1(lead.getAddressLine1());
        dto.setAddressLine2(lead.getAddressLine2());
        dto.setCity(lead.getCity());
        dto.setState(lead.getState());
        dto.setCountry(lead.getCountry());
        dto.setPincode(lead.getPincode());

        dto.setSource(lead.getSource());
        dto.setStatus(lead.getStatus());

        dto.setDescription(lead.getDescription());

        dto.setCreatedAt(lead.getCreatedAt());
        dto.setUpdatedAt(lead.getUpdatedAt());

        if (lead.getAssignedTo() != null) {
            dto.setAssignedToId(lead.getAssignedTo().getId());

            String firstName = lead.getAssignedTo().getFirstName();
            String lastName = lead.getAssignedTo().getLastName();

            String fullName = firstName;

            if (lastName != null && !lastName.isBlank()) {
                fullName = firstName + " " + lastName;
            }

            dto.setAssignedToName(fullName);
        }

        return dto;
    }
}