package com.example.demo.dto;

import com.example.demo.entity.FollowUpStatus;

public class FollowUpStatusUpdateRequestDTO {

    private FollowUpStatus status;

    public FollowUpStatusUpdateRequestDTO() {
    }

    public FollowUpStatus getStatus() {
        return status;
    }

    public void setStatus(FollowUpStatus status) {
        this.status = status;
    }
}