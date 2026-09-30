package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeadStatusHistoryResponseDTO;
import com.example.demo.dto.LeadStatusUpdateRequestDTO;
import com.example.demo.service.LeadStatusHistoryService;

@RestController
@RequestMapping("/api/leads")
public class LeadStatusHistoryController {

    private final LeadStatusHistoryService leadStatusHistoryService;

    public LeadStatusHistoryController(
            LeadStatusHistoryService leadStatusHistoryService) {
        this.leadStatusHistoryService = leadStatusHistoryService;
    }

    @PatchMapping("/{leadId}/status")
    public LeadStatusHistoryResponseDTO updateLeadStatus(
            @PathVariable Long leadId,
            @RequestBody LeadStatusUpdateRequestDTO request) {

        return leadStatusHistoryService.updateLeadStatus(
                leadId,
                request);
    }

    @GetMapping("/{leadId}/status-history")
    public List<LeadStatusHistoryResponseDTO> getStatusHistoryByLeadId(
            @PathVariable Long leadId) {

        return leadStatusHistoryService
                .getStatusHistoryByLeadId(leadId);
    }
}