package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.LeadSource;
import com.example.demo.entity.LeadStatus;

public class LeadResponseDTO {
	private Long id;

	private String firstName;
	private String lastName;
	private String companyName;
	private String email;
	private String phone;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String pincode;

	private LeadSource source;
	private LeadStatus status;

	private String description;

	private Long assignedToId;
	private String assignedToName;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getCompanyName() {
    return companyName;
}

public void setCompanyName(String companyName) {
    this.companyName = companyName;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getAddressLine1() {
    return addressLine1;
}

public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
}

public String getAddressLine2() {
    return addressLine2;
}

public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
}

public String getCity() {
    return city;
}

public void setCity(String city) {
    this.city = city;
}

public String getState() {
    return state;
}

public void setState(String state) {
    this.state = state;
}

public String getCountry() {
    return country;
}

public void setCountry(String country) {
    this.country = country;
}

public String getPincode() {
    return pincode;
}

public void setPincode(String pincode) {
    this.pincode = pincode;
}

public LeadSource getSource() {
    return source;
}

public void setSource(LeadSource source) {
    this.source = source;
}

public LeadStatus getStatus() {
    return status;
}

public void setStatus(LeadStatus status) {
    this.status = status;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Long getAssignedToId() {
    return assignedToId;
}

public void setAssignedToId(Long assignedToId) {
    this.assignedToId = assignedToId;
}

public String getAssignedToName() {
    return assignedToName;
}

public void setAssignedToName(String assignedToName) {
    this.assignedToName = assignedToName;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

public LocalDateTime getUpdatedAt() {
    return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
}}