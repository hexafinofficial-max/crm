package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LeadNoteRequestDTO;
import com.example.demo.dto.LeadNoteResponseDTO;

public interface LeadNoteService {

    LeadNoteResponseDTO createNote(
            Long leadId,
            LeadNoteRequestDTO request);

    List<LeadNoteResponseDTO> getNotesByLeadId(
            Long leadId);

    LeadNoteResponseDTO getNoteById(
            Long id);

    LeadNoteResponseDTO updateNote(
            Long id,
            LeadNoteRequestDTO request);

    void deleteNote(Long id);
}