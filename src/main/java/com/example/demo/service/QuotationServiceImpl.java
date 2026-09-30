package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.QuotationRequestDTO;
import com.example.demo.dto.QuotationResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.Quotation;
import com.example.demo.entity.QuotationStatus;
import com.example.demo.mapper.QuotationMapper;
import com.example.demo.repository.LeadRepository;
import com.example.demo.repository.QuotationRepository;

@Service
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository quotationRepository;
    private final LeadRepository leadRepository;

    public QuotationServiceImpl(
            QuotationRepository quotationRepository,
            LeadRepository leadRepository) {

        this.quotationRepository = quotationRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    @Transactional
    public QuotationResponseDTO createQuotation(
            Long leadId,
            QuotationRequestDTO request) {

        // 1. Check Lead
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException(
                        "Lead not found with id: " + leadId));

        // 2. Check quotation number
        if (request.getQuotationNumber() != null
                && quotationRepository
                        .findByQuotationNumber(request.getQuotationNumber())
                        .isPresent()) {

            throw new RuntimeException(
                    "Quotation number already exists: "
                            + request.getQuotationNumber());
        }

        // 3. Convert DTO -> Entity
        Quotation quotation =
                QuotationMapper.toEntity(request, lead);

        // 4. Default values
        if (quotation.getQuotationDate() == null) {
            quotation.setQuotationDate(LocalDate.now());
        }

        if (quotation.getStatus() == null) {
            quotation.setStatus(QuotationStatus.DRAFT);
        }

        if (quotation.getRevisionNumber() == null) {
            quotation.setRevisionNumber(1);
        }

        // 5. Audit fields
        LocalDateTime now = LocalDateTime.now();

        quotation.setCreatedAt(now);
        quotation.setUpdatedAt(now);

        // createdBy will remain null until JWT authentication
        // is implemented.

        // 6. Save
        Quotation savedQuotation =
                quotationRepository.save(quotation);

        // 7. Entity -> Response DTO
        return QuotationMapper.toResponseDTO(savedQuotation);
    }

    @Override
    public List<QuotationResponseDTO> getQuotationsByLeadId(
            Long leadId) {

        // Check Lead
        if (!leadRepository.existsById(leadId)) {
            throw new RuntimeException(
                    "Lead not found with id: " + leadId);
        }

        return quotationRepository.findByLeadId(leadId)
                .stream()
                .map(QuotationMapper::toResponseDTO)
                .toList();
    }

    @Override
    public QuotationResponseDTO getQuotationById(
            Long quotationId) {

        Quotation quotation =
                quotationRepository.findById(quotationId)
                        .orElseThrow(() -> new RuntimeException(
                                "Quotation not found with id: "
                                        + quotationId));

        return QuotationMapper.toResponseDTO(quotation);
    }

    @Override
    @Transactional
    public QuotationResponseDTO updateQuotation(
            Long quotationId,
            QuotationRequestDTO request) {

        Quotation quotation =
                quotationRepository.findById(quotationId)
                        .orElseThrow(() -> new RuntimeException(
                                "Quotation not found with id: "
                                        + quotationId));

        // Update quotation number only if changed
        if (request.getQuotationNumber() != null
                && !request.getQuotationNumber()
                        .equals(quotation.getQuotationNumber())) {

            if (quotationRepository
                    .findByQuotationNumber(
                            request.getQuotationNumber())
                    .isPresent()) {

                throw new RuntimeException(
                        "Quotation number already exists: "
                                + request.getQuotationNumber());
            }

            quotation.setQuotationNumber(
                    request.getQuotationNumber());
        }

        quotation.setQuotationDate(
                request.getQuotationDate());

        quotation.setValidUntil(
                request.getValidUntil());

        quotation.setStatus(
                request.getStatus());

        quotation.setRevisionNumber(
                request.getRevisionNumber());

        quotation.setLoanType(
                request.getLoanType());

        quotation.setLoanAmount(
                request.getLoanAmount());

        quotation.setInterestRate(
                request.getInterestRate());

        quotation.setTenureMonths(
                request.getTenureMonths());

        quotation.setLoanPurpose(
                request.getLoanPurpose());

        quotation.setLenderName(
                request.getLenderName());

        quotation.setProcessingFee(
                request.getProcessingFee());

        quotation.setOtherCharges(
                request.getOtherCharges());

        quotation.setDiscountPercent(
                request.getDiscountPercent());

        quotation.setDiscountAmount(
                request.getDiscountAmount());

        quotation.setTaxableAmount(
                request.getTaxableAmount());

        quotation.setTaxPercent(
                request.getTaxPercent());

        quotation.setTaxAmount(
                request.getTaxAmount());

        quotation.setGrandTotal(
                request.getGrandTotal());

        quotation.setPaymentTerms(
                request.getPaymentTerms());

        quotation.setTermsAndConditions(
                request.getTermsAndConditions());

        quotation.setNotes(
                request.getNotes());

        quotation.setUpdatedAt(
                LocalDateTime.now());

        Quotation updatedQuotation =
                quotationRepository.save(quotation);

        return QuotationMapper.toResponseDTO(
                updatedQuotation);
    }

    @Override
    @Transactional
    public void deleteQuotation(Long quotationId) {

        Quotation quotation =
                quotationRepository.findById(quotationId)
                        .orElseThrow(() -> new RuntimeException(
                                "Quotation not found with id: "
                                        + quotationId));

        quotationRepository.delete(quotation);
    }
}