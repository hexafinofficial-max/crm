package com.example.demo.mapper;

import com.example.demo.dto.QuotationRequestDTO;
import com.example.demo.dto.QuotationResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.Quotation;

public class QuotationMapper {

    public static Quotation toEntity(
            QuotationRequestDTO dto,
            Lead lead) {

        Quotation quotation = new Quotation();

        quotation.setLead(lead);

        quotation.setQuotationNumber(
                dto.getQuotationNumber());

        quotation.setQuotationDate(
                dto.getQuotationDate());

        quotation.setValidUntil(
                dto.getValidUntil());

        quotation.setStatus(
                dto.getStatus());

        quotation.setRevisionNumber(
                dto.getRevisionNumber());

        // Loan Details
        quotation.setLoanType(
                dto.getLoanType());

        quotation.setLoanAmount(
                dto.getLoanAmount());

        quotation.setInterestRate(
                dto.getInterestRate());

        quotation.setTenureMonths(
                dto.getTenureMonths());

        quotation.setLoanPurpose(
                dto.getLoanPurpose());

        quotation.setLenderName(
                dto.getLenderName());

        // Charges
        quotation.setProcessingFee(
                dto.getProcessingFee());

        quotation.setOtherCharges(
                dto.getOtherCharges());

        quotation.setDiscountPercent(
                dto.getDiscountPercent());

        quotation.setDiscountAmount(
                dto.getDiscountAmount());

        quotation.setTaxableAmount(
                dto.getTaxableAmount());

        quotation.setTaxPercent(
                dto.getTaxPercent());

        quotation.setTaxAmount(
                dto.getTaxAmount());

        quotation.setGrandTotal(
                dto.getGrandTotal());

        // Payment / Terms
        quotation.setPaymentTerms(
                dto.getPaymentTerms());

        quotation.setTermsAndConditions(
                dto.getTermsAndConditions());

        quotation.setNotes(
                dto.getNotes());

        return quotation;
    }

    public static QuotationResponseDTO toResponseDTO(
            Quotation quotation) {

        QuotationResponseDTO dto =
                new QuotationResponseDTO();

        dto.setId(quotation.getId());

        if (quotation.getLead() != null) {
            dto.setLeadId(
                    quotation.getLead().getId());
        }

        dto.setQuotationNumber(
                quotation.getQuotationNumber());

        dto.setQuotationDate(
                quotation.getQuotationDate());

        dto.setValidUntil(
                quotation.getValidUntil());

        dto.setStatus(
                quotation.getStatus());

        dto.setRevisionNumber(
                quotation.getRevisionNumber());

        // Created By
        if (quotation.getCreatedBy() != null) {

            dto.setCreatedById(
                    quotation.getCreatedBy().getId());

            dto.setCreatedByName(
                    quotation.getCreatedBy().getFirstName()
                    + " "
                    + quotation.getCreatedBy().getLastName());
        }

        // Loan Details
        dto.setLoanType(
                quotation.getLoanType());

        dto.setLoanAmount(
                quotation.getLoanAmount());

        dto.setInterestRate(
                quotation.getInterestRate());

        dto.setTenureMonths(
                quotation.getTenureMonths());

        dto.setLoanPurpose(
                quotation.getLoanPurpose());

        dto.setLenderName(
                quotation.getLenderName());

        // Charges
        dto.setProcessingFee(
                quotation.getProcessingFee());

        dto.setOtherCharges(
                quotation.getOtherCharges());

        dto.setDiscountPercent(
                quotation.getDiscountPercent());

        dto.setDiscountAmount(
                quotation.getDiscountAmount());

        dto.setTaxableAmount(
                quotation.getTaxableAmount());

        dto.setTaxPercent(
                quotation.getTaxPercent());

        dto.setTaxAmount(
                quotation.getTaxAmount());

        dto.setGrandTotal(
                quotation.getGrandTotal());

        // Payment / Terms
        dto.setPaymentTerms(
                quotation.getPaymentTerms());

        dto.setTermsAndConditions(
                quotation.getTermsAndConditions());

        dto.setNotes(
                quotation.getNotes());

        // Audit
        dto.setCreatedAt(
                quotation.getCreatedAt());

        dto.setUpdatedAt(
                quotation.getUpdatedAt());

        return dto;
    }
}