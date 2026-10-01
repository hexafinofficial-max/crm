package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO createCustomerFromLead(Long leadId);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long customerId);

    CustomerResponseDTO getCustomerByLeadId(Long leadId);
    CustomerResponseDTO updateCustomer(
            Long customerId,
            CustomerRequestDTO request);
}