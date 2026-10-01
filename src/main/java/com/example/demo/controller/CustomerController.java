package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerResponseDTO getCustomerById(
            @PathVariable Long customerId) {

        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/lead/{leadId}")
    public CustomerResponseDTO getCustomerByLeadId(
            @PathVariable Long leadId) {

        return customerService.getCustomerByLeadId(leadId);
    }

    @PostMapping("/from-lead/{leadId}")
    public CustomerResponseDTO createCustomerFromLead(
            @PathVariable Long leadId) {

        return customerService.createCustomerFromLead(leadId);
    }
    @PutMapping("/{customerId}")
    public CustomerResponseDTO updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDTO request) {

        return customerService.updateCustomer(
                customerId, request);
    }
}