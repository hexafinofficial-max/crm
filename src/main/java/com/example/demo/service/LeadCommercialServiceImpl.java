package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LeadCommercialRequestDTO;
import com.example.demo.dto.LeadCommercialResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadCommercial;
import com.example.demo.mapper.LeadCommercialMapper;
import com.example.demo.repository.LeadCommercialRepository;
import com.example.demo.repository.LeadRepository;

@Service
public class LeadCommercialServiceImpl
        implements LeadCommercialService {

    private final LeadRepository leadRepository;
    private final LeadCommercialRepository commercialRepository;

    public LeadCommercialServiceImpl(
            LeadRepository leadRepository,
            LeadCommercialRepository commercialRepository) {

        this.leadRepository = leadRepository;
        this.commercialRepository = commercialRepository;
    }

    @Override
    @Transactional
    public LeadCommercialResponseDTO createCommercial(
            Long leadId,
            LeadCommercialRequestDTO request) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        if (commercialRepository.findByLeadId(leadId).isPresent()) {
            throw new RuntimeException(
                    "Commercial already exists for lead id: "
                    + leadId);
        }

        LeadCommercial commercial =
                LeadCommercialMapper.toEntity(request, lead);

        LocalDateTime now = LocalDateTime.now();

        commercial.setCreatedAt(now);
        commercial.setUpdatedAt(now);

        LeadCommercial saved =
                commercialRepository.save(commercial);

        return LeadCommercialMapper
                .toResponseDTO(saved);
    }

    @Override
    public LeadCommercialResponseDTO getCommercialByLeadId(
            Long leadId) {

        if (!leadRepository.existsById(leadId)) {
            throw new RuntimeException(
                    "Lead not found with id: " + leadId);
        }

        LeadCommercial commercial =
                commercialRepository.findByLeadId(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Commercial not found for lead id: "
                                + leadId));

        return LeadCommercialMapper
                .toResponseDTO(commercial);
    }

    @Override
    @Transactional
    public LeadCommercialResponseDTO updateCommercial(
            Long leadId,
            LeadCommercialRequestDTO request) {

        LeadCommercial commercial =
                commercialRepository.findByLeadId(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Commercial not found for lead id: "
                                + leadId));

        commercial.setLoanType(request.getLoanType());
        commercial.setLoanAmount(request.getLoanAmount());
        commercial.setInterestRate(request.getInterestRate());
        commercial.setTenureMonths(request.getTenureMonths());
        commercial.setProcessingFee(request.getProcessingFee());
        commercial.setGstPercent(request.getGstPercent());
        commercial.setDiscountPercent(request.getDiscountPercent());
        commercial.setDiscountAmount(request.getDiscountAmount());
        commercial.setOtherCharges(request.getOtherCharges());
        commercial.setTotalAmount(request.getTotalAmount());
        commercial.setPaymentTerms(request.getPaymentTerms());
        commercial.setNotes(request.getNotes());

        commercial.setUpdatedAt(LocalDateTime.now());

        LeadCommercial updated =
                commercialRepository.save(commercial);

        return LeadCommercialMapper
                .toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteCommercial(Long leadId) {

        LeadCommercial commercial =
                commercialRepository.findByLeadId(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Commercial not found for lead id: "
                                + leadId));

        commercialRepository.delete(commercial);
    }
}