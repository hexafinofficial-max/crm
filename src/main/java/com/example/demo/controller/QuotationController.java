package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.QuotationRequestDTO;
import com.example.demo.dto.QuotationResponseDTO;
import com.example.demo.service.QuotationService;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(
            QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    // Create quotation for a Lead
    @PostMapping("/lead/{leadId}")
    public QuotationResponseDTO createQuotation(
            @PathVariable Long leadId,
            @RequestBody QuotationRequestDTO request) {

        return quotationService.createQuotation(
                leadId,
                request);
    }

    // Get all quotations of a Lead
    @GetMapping("/lead/{leadId}")
    public List<QuotationResponseDTO> getQuotationsByLeadId(
            @PathVariable Long leadId) {

        return quotationService
                .getQuotationsByLeadId(leadId);
    }

    // Get quotation by ID
    @GetMapping("/{quotationId}")
    public QuotationResponseDTO getQuotationById(
            @PathVariable Long quotationId) {

        return quotationService
                .getQuotationById(quotationId);
    }

    // Update quotation
    @PutMapping("/{quotationId}")
    public QuotationResponseDTO updateQuotation(
            @PathVariable Long quotationId,
            @RequestBody QuotationRequestDTO request) {

        return quotationService.updateQuotation(
                quotationId,
                request);
    }

    // Delete quotation
    @DeleteMapping("/{quotationId}")
    public void deleteQuotation(
            @PathVariable Long quotationId) {

        quotationService.deleteQuotation(
                quotationId);
    }
}