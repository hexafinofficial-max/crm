package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "lead_status_history")
public class LeadStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private LeadStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private LeadStatus newStatus;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(nullable = false)
    private LocalDateTime changedAt;

    public LeadStatusHistory() {
    }

    public Long getId() {
        return id;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
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

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}