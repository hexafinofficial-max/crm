package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LeadStatusHistoryResponseDTO;
import com.example.demo.dto.LeadStatusUpdateRequestDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadStatus;
import com.example.demo.entity.LeadStatusHistory;
import com.example.demo.mapper.LeadStatusHistoryMapper;
import com.example.demo.repository.LeadRepository;
import com.example.demo.repository.LeadStatusHistoryRepository;

@Service
public class LeadStatusHistoryServiceImpl
        implements LeadStatusHistoryService {

    private final LeadRepository leadRepository;

    private final LeadStatusHistoryRepository historyRepository;

    private final CustomerService customerService;

    public LeadStatusHistoryServiceImpl(
            LeadRepository leadRepository,
            LeadStatusHistoryRepository historyRepository,
            CustomerService customerService) {

        this.leadRepository = leadRepository;
        this.historyRepository = historyRepository;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public LeadStatusHistoryResponseDTO updateLeadStatus(
            Long leadId,
            LeadStatusUpdateRequestDTO request) {

        // 1. Find Lead
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        // 2. Check same status
        if (lead.getStatus() == request.getStatus()) {

            throw new RuntimeException(
                    "Lead is already in status: "
                    + request.getStatus());
        }

        // 3. Create status history
        LeadStatusHistory history =
                new LeadStatusHistory();

        history.setLead(lead);

        history.setOldStatus(lead.getStatus());

        history.setNewStatus(request.getStatus());

        history.setChangedAt(LocalDateTime.now());

        // 4. Update Lead status
        lead.setStatus(request.getStatus());

        // 5. Save status history
        LeadStatusHistory savedHistory =
                historyRepository.save(history);

        // 6. If Lead becomes WON,
        // automatically create Customer
        if (request.getStatus() == LeadStatus.WON) {

            customerService.createCustomerFromLead(leadId);
        }

        // 7. Return response
        return LeadStatusHistoryMapper
                .toResponseDTO(savedHistory);
    }

    @Override
    public List<LeadStatusHistoryResponseDTO>
            getStatusHistoryByLeadId(Long leadId) {

        // Check Lead exists
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