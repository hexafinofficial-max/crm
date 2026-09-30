package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.LeadStatus;

public class LeadStatusHistoryResponseDTO {

    private Long id;

    private Long leadId;

    private LeadStatus oldStatus;

    private LeadStatus newStatus;

    private Long changedById;

    private String changedByName;

    private LocalDateTime changedAt;

    public LeadStatusHistoryResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public LeadStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(LeadStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public LeadStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(LeadStatus newStatus) {
        this.newStatus = newStatus;
    }

    public Long getChangedById() {
        return changedById;
    }

    public void setChangedById(Long changedById) {
        this.changedById = changedById;
    }

    public String getChangedByName() {
        return changedByName;
    }

    public void setChangedByName(String changedByName) {
        this.changedByName = changedByName;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}