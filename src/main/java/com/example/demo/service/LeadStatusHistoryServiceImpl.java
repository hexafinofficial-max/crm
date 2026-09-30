package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LeadStatusHistoryResponseDTO;
import com.example.demo.dto.LeadStatusUpdateRequestDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadStatusHistory;
import com.example.demo.mapper.LeadStatusHistoryMapper;
import com.example.demo.repository.LeadRepository;
import com.example.demo.repository.LeadStatusHistoryRepository;

@Service
public class LeadStatusHistoryServiceImpl
        implements LeadStatusHistoryService {

    private final LeadRepository leadRepository;
    private final LeadStatusHistoryRepository historyRepository;

    public LeadStatusHistoryServiceImpl(
            LeadRepository leadRepository,
            LeadStatusHistoryRepository historyRepository) {

        this.leadRepository = leadRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional
    public LeadStatusHistoryResponseDTO updateLeadStatus(
            Long leadId,
            LeadStatusUpdateRequestDTO request) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        if (lead.getStatus() == request.getStatus()) {
            throw new RuntimeException(
                    "Lead is already in status: "
                    + request.getStatus());
        }

        LeadStatusHistory history =
                new LeadStatusHistory();

        history.setLead(lead);
        history.setOldStatus(lead.getStatus());
        history.setNewStatus(request.getStatus());
        history.setChangedAt(LocalDateTime.now());

        lead.setStatus(request.getStatus());

        LeadStatusHistory savedHistory =
                historyRepository.save(history);

        return LeadStatusHistoryMapper
                .toResponseDTO(savedHistory);
    }

    @Override
    public List<LeadStatusHistoryResponseDTO>
            getStatusHistoryByLeadId(Long leadId) {

        if (!leadRepository.existsById(leadId)) {
            throw new RuntimeException(
                    "Lead not found with id: " + leadId);
        }

        return historyRepository
                .findByLeadId(leadId)
                .stream()
                .map(LeadStatusHistoryMapper::toResponseDTO)
                .toList();
    }
}