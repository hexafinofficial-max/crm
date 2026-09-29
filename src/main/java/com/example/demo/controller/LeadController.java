package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LeadRequestDTO;
import com.example.demo.dto.LeadResponseDTO;
import com.example.demo.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public LeadResponseDTO createLead(@RequestBody LeadRequestDTO request) {
        return leadService.createLead(request);
    }

    @GetMapping
    public List<LeadResponseDTO> getAllLeads() {
        return leadService.getAllLeads();
    }
    @GetMapping("/{id}")
    public LeadResponseDTO getLeadById(@PathVariable Long id) {
        return leadService.getLeadById(id);
    }
    @PutMapping("/{id}")
    public LeadResponseDTO updateLead(
            @PathVariable Long id,
            @RequestBody LeadRequestDTO request) {

        return leadService.updateLead(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
    }
}