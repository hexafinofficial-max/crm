package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeadNoteRequestDTO;
import com.example.demo.dto.LeadNoteResponseDTO;
import com.example.demo.service.LeadNoteService;

@RestController
@RequestMapping("/api/leads")
public class LeadNoteController {

    private final LeadNoteService leadNoteService;

    public LeadNoteController(
            LeadNoteService leadNoteService) {

        this.leadNoteService = leadNoteService;
    }

    @PostMapping("/{leadId}/notes")
    public LeadNoteResponseDTO createNote(
            @PathVariable Long leadId,
            @RequestBody LeadNoteRequestDTO request) {

        return leadNoteService.createNote(
                leadId,
                request);
    }

    @GetMapping("/{leadId}/notes")
    public List<LeadNoteResponseDTO> getNotesByLeadId(
            @PathVariable Long leadId) {

        return leadNoteService
                .getNotesByLeadId(leadId);
    }

    @GetMapping("/notes/{id}")
    public LeadNoteResponseDTO getNoteById(
            @PathVariable Long id) {

        return leadNoteService
                .getNoteById(id);
    }

    @PutMapping("/notes/{id}")
    public LeadNoteResponseDTO updateNote(
            @PathVariable Long id,
            @RequestBody LeadNoteRequestDTO request) {

        return leadNoteService
                .updateNote(id, request);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(
            @PathVariable Long id) {

        leadNoteService.deleteNote(id);
    }
}