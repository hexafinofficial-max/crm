package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeadNoteRequestDTO;
import com.example.demo.dto.LeadNoteResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadNote;
import com.example.demo.mapper.LeadNoteMapper;
import com.example.demo.repository.LeadNoteRepository;
import com.example.demo.repository.LeadRepository;

@Service
public class LeadNoteServiceImpl implements LeadNoteService {

    private final LeadNoteRepository leadNoteRepository;
    private final LeadRepository leadRepository;

    public LeadNoteServiceImpl(
            LeadNoteRepository leadNoteRepository,
            LeadRepository leadRepository) {

        this.leadNoteRepository = leadNoteRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    public LeadNoteResponseDTO createNote(
            Long leadId,
            LeadNoteRequestDTO request) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        LeadNote note =
                LeadNoteMapper.toEntity(request, lead);

        LocalDateTime now = LocalDateTime.now();

        note.setCreatedAt(now);
        note.setUpdatedAt(now);

        LeadNote savedNote =
                leadNoteRepository.save(note);

        return LeadNoteMapper
                .toResponseDTO(savedNote);
    }

    @Override
    public List<LeadNoteResponseDTO> getNotesByLeadId(
            Long leadId) {

        if (!leadRepository.existsById(leadId)) {
            throw new RuntimeException(
                    "Lead not found with id: " + leadId);
        }

        return leadNoteRepository
                .findByLeadId(leadId)
                .stream()
                .map(LeadNoteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LeadNoteResponseDTO getNoteById(Long id) {

        LeadNote note =
                leadNoteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Note not found with id: " + id));

        return LeadNoteMapper
                .toResponseDTO(note);
    }

    @Override
    public LeadNoteResponseDTO updateNote(
            Long id,
            LeadNoteRequestDTO request) {

        LeadNote note =
                leadNoteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Note not found with id: " + id));

        note.setContent(request.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        LeadNote updatedNote =
                leadNoteRepository.save(note);

        return LeadNoteMapper
                .toResponseDTO(updatedNote);
    }

    @Override
    public void deleteNote(Long id) {

        LeadNote note =
                leadNoteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Note not found with id: " + id));

        leadNoteRepository.delete(note);
    }
}