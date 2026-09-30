package com.example.demo.dto;

import com.example.demo.entity.LeadStatus;

public class LeadStatusUpdateRequestDTO {

    private LeadStatus status;

    public LeadStatusUpdateRequestDTO() {
    }

    public LeadStatus getStatus() {
        return status;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }
}