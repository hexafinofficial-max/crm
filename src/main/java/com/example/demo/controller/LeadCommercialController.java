package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeadCommercialRequestDTO;
import com.example.demo.dto.LeadCommercialResponseDTO;
import com.example.demo.service.LeadCommercialService;

@RestController
@RequestMapping("/api/leads")
public class LeadCommercialController {

    private final LeadCommercialService leadCommercialService;

    public LeadCommercialController(
            LeadCommercialService leadCommercialService) {

        this.leadCommercialService =
                leadCommercialService;
    }

    @PostMapping("/{leadId}/commercial")
    public LeadCommercialResponseDTO createCommercial(
            @PathVariable Long leadId,
            @RequestBody LeadCommercialRequestDTO request) {

        return leadCommercialService
                .createCommercial(leadId, request);
    }

    @GetMapping("/{leadId}/commercial")
    public LeadCommercialResponseDTO getCommercial(
            @PathVariable Long leadId) {

        return leadCommercialService
                .getCommercialByLeadId(leadId);
    }

    @PutMapping("/{leadId}/commercial")
    public LeadCommercialResponseDTO updateCommercial(
            @PathVariable Long leadId,
            @RequestBody LeadCommercialRequestDTO request) {

        return leadCommercialService
                .updateCommercial(leadId, request);
    }

    @DeleteMapping("/{leadId}/commercial")
    public void deleteCommercial(
            @PathVariable Long leadId) {

        leadCommercialService
                .deleteCommercial(leadId);
    }
}