package com.example.demo.mapper;

import com.example.demo.dto.LeadCommercialRequestDTO;
import com.example.demo.dto.LeadCommercialResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadCommercial;

public class LeadCommercialMapper {

    public static LeadCommercial toEntity(
            LeadCommercialRequestDTO dto,
            Lead lead) {

        LeadCommercial commercial = new LeadCommercial();

        commercial.setLead(lead);
        commercial.setLoanType(dto.getLoanType());
        commercial.setLoanAmount(dto.getLoanAmount());
        commercial.setInterestRate(dto.getInterestRate());
        commercial.setTenureMonths(dto.getTenureMonths());
        commercial.setProcessingFee(dto.getProcessingFee());
        commercial.setGstPercent(dto.getGstPercent());
        commercial.setDiscountPercent(dto.getDiscountPercent());
        commercial.setDiscountAmount(dto.getDiscountAmount());
        commercial.setOtherCharges(dto.getOtherCharges());
        commercial.setTotalAmount(dto.getTotalAmount());
        commercial.setPaymentTerms(dto.getPaymentTerms());
        commercial.setNotes(dto.getNotes());

        return commercial;
    }

    public static LeadCommercialResponseDTO toResponseDTO(
            LeadCommercial commercial) {

        LeadCommercialResponseDTO dto =
                new LeadCommercialResponseDTO();

        dto.setId(commercial.getId());

        if (commercial.getLead() != null) {
            dto.setLeadId(
                    commercial.getLead().getId());
        }

        dto.setLoanType(commercial.getLoanType());
        dto.setLoanAmount(commercial.getLoanAmount());
        dto.setInterestRate(commercial.getInterestRate());
        dto.setTenureMonths(commercial.getTenureMonths());
        dto.setProcessingFee(commercial.getProcessingFee());
        dto.setGstPercent(commercial.getGstPercent());
        dto.setDiscountPercent(commercial.getDiscountPercent());
        dto.setDiscountAmount(commercial.getDiscountAmount());
        dto.setOtherCharges(commercial.getOtherCharges());
        dto.setTotalAmount(commercial.getTotalAmount());
        dto.setPaymentTerms(commercial.getPaymentTerms());
        dto.setNotes(commercial.getNotes());

        dto.setCreatedAt(commercial.getCreatedAt());
        dto.setUpdatedAt(commercial.getUpdatedAt());

        return dto;
    }
}