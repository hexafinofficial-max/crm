package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.QuotationRequestDTO;
import com.example.demo.dto.QuotationResponseDTO;

public interface QuotationService {

    QuotationResponseDTO createQuotation(
            Long leadId,
            QuotationRequestDTO request);

    List<QuotationResponseDTO> getQuotationsByLeadId(
            Long leadId);

    QuotationResponseDTO getQuotationById(
            Long quotationId);

    QuotationResponseDTO updateQuotation(
            Long quotationId,
            QuotationRequestDTO request);

    void deleteQuotation(
            Long quotationId);
}