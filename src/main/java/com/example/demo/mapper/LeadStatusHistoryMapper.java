package com.example.demo.mapper;

import com.example.demo.dto.LeadStatusHistoryResponseDTO;
import com.example.demo.entity.LeadStatusHistory;

public class LeadStatusHistoryMapper {

    public static LeadStatusHistoryResponseDTO toResponseDTO(
            LeadStatusHistory history) {

        LeadStatusHistoryResponseDTO dto =
                new LeadStatusHistoryResponseDTO();

        dto.setId(history.getId());

        if (history.getLead() != null) {
            dto.setLeadId(history.getLead().getId());
        }

        dto.setOldStatus(history.getOldStatus());
        dto.setNewStatus(history.getNewStatus());

        if (history.getChangedBy() != null) {

            dto.setChangedById(
                    history.getChangedBy().getId());

            String firstName =
                    history.getChangedBy().getFirstName();

            String lastName =
                    history.getChangedBy().getLastName();

            String fullName = firstName;

            if (lastName != null &&
                    !lastName.isBlank()) {

                fullName =
                        firstName + " " + lastName;
            }

            dto.setChangedByName(fullName);
        }

        dto.setChangedAt(history.getChangedAt());

        return dto;
    }
}