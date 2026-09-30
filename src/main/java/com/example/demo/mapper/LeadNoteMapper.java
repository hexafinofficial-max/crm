package com.example.demo.mapper;

import com.example.demo.dto.LeadNoteRequestDTO;
import com.example.demo.dto.LeadNoteResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadNote;

public class LeadNoteMapper {

    public static LeadNote toEntity(
            LeadNoteRequestDTO dto,
            Lead lead) {

        LeadNote note = new LeadNote();

        note.setLead(lead);
        note.setContent(dto.getContent());

        return note;
    }

    public static LeadNoteResponseDTO toResponseDTO(
            LeadNote note) {

        LeadNoteResponseDTO dto =
                new LeadNoteResponseDTO();

        dto.setId(note.getId());

        if (note.getLead() != null) {
            dto.setLeadId(note.getLead().getId());
        }

        dto.setContent(note.getContent());

        if (note.getCreatedBy() != null) {

            dto.setCreatedById(
                    note.getCreatedBy().getId());

            String firstName =
                    note.getCreatedBy().getFirstName();

            String lastName =
                    note.getCreatedBy().getLastName();

            String fullName = firstName;

            if (lastName != null &&
                    !lastName.isBlank()) {

                fullName =
                        firstName + " " + lastName;
            }

            dto.setCreatedByName(fullName);
        }

        dto.setCreatedAt(note.getCreatedAt());
        dto.setUpdatedAt(note.getUpdatedAt());

        return dto;
    }
}