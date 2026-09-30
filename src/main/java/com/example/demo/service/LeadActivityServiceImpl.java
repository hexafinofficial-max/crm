package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeadActivityRequestDTO;
import com.example.demo.dto.LeadActivityResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadActivity;
import com.example.demo.mapper.LeadActivityMapper;
import com.example.demo.repository.LeadActivityRepository;
import com.example.demo.repository.LeadRepository;

@Service
public class LeadActivityServiceImpl
        implements LeadActivityService {

    private final LeadActivityRepository leadActivityRepository;
    private final LeadRepository leadRepository;

    public LeadActivityServiceImpl(
            LeadActivityRepository leadActivityRepository,
            LeadRepository leadRepository) {

        this.leadActivityRepository = leadActivityRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    public LeadActivityResponseDTO createActivity(
            Long leadId,
            LeadActivityRequestDTO request) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        LeadActivity activity =
                LeadActivityMapper.toEntity(request, lead);

        activity.setCreatedAt(LocalDateTime.now());

        LeadActivity savedActivity =
                leadActivityRepository.save(activity);

        return LeadActivityMapper
                .toResponseDTO(savedActivity);
    }

    @Override
    public List<LeadActivityResponseDTO> getActivitiesByLeadId(
            Long leadId) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with id: " + leadId));

        return leadActivityRepository.findByLeadId(leadId)
                .stream()
                .map(LeadActivityMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LeadActivityResponseDTO getActivityById(Long id) {

        LeadActivity activity =
                leadActivityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Activity not found with id: " + id));

        return LeadActivityMapper
                .toResponseDTO(activity);
    }

    @Override
    public LeadActivityResponseDTO updateActivity(
            Long id,
            LeadActivityRequestDTO request) {

        LeadActivity activity =
                leadActivityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Activity not found with id: " + id));

        activity.setType(request.getType());

        activity.setActivityDateTime(
                request.getActivityDateTime());

        activity.setDescription(
                request.getDescription());

        LeadActivity updatedActivity =
                leadActivityRepository.save(activity);

        return LeadActivityMapper
                .toResponseDTO(updatedActivity);
    }

    @Override
    public void deleteActivity(Long id) {

        LeadActivity activity =
                leadActivityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Activity not found with id: " + id));

        leadActivityRepository.delete(activity);
    }
}