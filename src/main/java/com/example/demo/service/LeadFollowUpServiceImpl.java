package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FollowUpStatusUpdateRequestDTO;
import com.example.demo.dto.LeadFollowUpRequestDTO;
import com.example.demo.dto.LeadFollowUpResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadFollowUp;
import com.example.demo.repository.LeadFollowUpRepository;
import com.example.demo.repository.LeadRepository;
import com.example.demo.mapper.LeadFollowUpMapper;

@Service
public class LeadFollowUpServiceImpl implements LeadFollowUpService {

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final LeadRepository leadRepository;

    public LeadFollowUpServiceImpl(
            LeadFollowUpRepository leadFollowUpRepository,
            LeadRepository leadRepository) {

        this.leadFollowUpRepository = leadFollowUpRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    public LeadFollowUpResponseDTO createFollowUp(
            Long leadId,
            LeadFollowUpRequestDTO request) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found with id: " + leadId));

        LeadFollowUp followUp =
                LeadFollowUpMapper.toEntity(request, lead);

        LocalDateTime now = LocalDateTime.now();

        followUp.setCreatedAt(now);
        followUp.setUpdatedAt(now);

        LeadFollowUp savedFollowUp =
                leadFollowUpRepository.save(followUp);

        return LeadFollowUpMapper.toResponseDTO(savedFollowUp);
    }

    @Override
    public List<LeadFollowUpResponseDTO> getFollowUpsByLeadId(
            Long leadId) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found with id: " + leadId));

        return leadFollowUpRepository.findAll()
                .stream()
                .filter(followUp ->
                        followUp.getLead().getId().equals(lead.getId()))
                .map(LeadFollowUpMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LeadFollowUpResponseDTO getFollowUpById(Long id) {

        LeadFollowUp followUp =
                leadFollowUpRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "FollowUp not found with id: " + id));

        return LeadFollowUpMapper.toResponseDTO(followUp);
    }

    @Override
    public LeadFollowUpResponseDTO updateFollowUp(
            Long id,
            LeadFollowUpRequestDTO request) {

        LeadFollowUp followUp =
                leadFollowUpRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "FollowUp not found with id: " + id));

        followUp.setFollowUpDate(request.getFollowUpDate());
        followUp.setFollowUpTime(request.getFollowUpTime());
        followUp.setType(request.getType());
        followUp.setNotes(request.getNotes());

        followUp.setUpdatedAt(LocalDateTime.now());

        LeadFollowUp updatedFollowUp =
                leadFollowUpRepository.save(followUp);

        return LeadFollowUpMapper.toResponseDTO(updatedFollowUp);
    }

    @Override
    public void deleteFollowUp(Long id) {

        LeadFollowUp followUp =
                leadFollowUpRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "FollowUp not found with id: " + id));

        leadFollowUpRepository.delete(followUp);
    }
    
    @Override
    public LeadFollowUpResponseDTO updateFollowUpStatus(
            Long id,
            FollowUpStatusUpdateRequestDTO request) {

        LeadFollowUp followUp =
                leadFollowUpRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "FollowUp not found with id: " + id));

        followUp.setStatus(request.getStatus());
        followUp.setUpdatedAt(LocalDateTime.now());

        LeadFollowUp updatedFollowUp =
                leadFollowUpRepository.save(followUp);

        return LeadFollowUpMapper
                .toResponseDTO(updatedFollowUp);
    }
}