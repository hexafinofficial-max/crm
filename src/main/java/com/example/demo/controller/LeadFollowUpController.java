package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FollowUpStatusUpdateRequestDTO;
import com.example.demo.dto.LeadFollowUpRequestDTO;
import com.example.demo.dto.LeadFollowUpResponseDTO;
import com.example.demo.service.LeadFollowUpService;

@RestController
@RequestMapping("/api/leads")
public class LeadFollowUpController {

    private final LeadFollowUpService leadFollowUpService;

    public LeadFollowUpController(
            LeadFollowUpService leadFollowUpService) {

        this.leadFollowUpService = leadFollowUpService;
    }

    @PostMapping("/{leadId}/follow-ups")
    public LeadFollowUpResponseDTO createFollowUp(
            @PathVariable Long leadId,
            @RequestBody LeadFollowUpRequestDTO request) {

        return leadFollowUpService.createFollowUp(
                leadId,
                request);
    }

    @GetMapping("/{leadId}/follow-ups")
    public List<LeadFollowUpResponseDTO> getFollowUpsByLeadId(
            @PathVariable Long leadId) {

        return leadFollowUpService
                .getFollowUpsByLeadId(leadId);
    }

    @GetMapping("/follow-ups/{id}")
    public LeadFollowUpResponseDTO getFollowUpById(
            @PathVariable Long id) {

        return leadFollowUpService.getFollowUpById(id);
    }

    @PutMapping("/follow-ups/{id}")
    public LeadFollowUpResponseDTO updateFollowUp(
            @PathVariable Long id,
            @RequestBody LeadFollowUpRequestDTO request) {

        return leadFollowUpService
                .updateFollowUp(id, request);
    }

    @DeleteMapping("/follow-ups/{id}")
    public void deleteFollowUp(
            @PathVariable Long id) {

        leadFollowUpService.deleteFollowUp(id);
    }
    
    @PatchMapping("/follow-ups/{id}/status")
    public LeadFollowUpResponseDTO updateFollowUpStatus(
            @PathVariable Long id,
            @RequestBody FollowUpStatusUpdateRequestDTO request) {

        return leadFollowUpService.updateFollowUpStatus(
                id,
                request);
    }
}