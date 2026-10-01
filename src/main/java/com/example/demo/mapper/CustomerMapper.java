package com.example.demo.mapper;

import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Lead;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer toEntity(
            CustomerRequestDTO request,
            Lead lead) {

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAlternatePhone(request.getAlternatePhone());

        customer.setAddressLine1(request.getAddressLine1());
        customer.setAddressLine2(request.getAddressLine2());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setCountry(request.getCountry());
        customer.setPincode(request.getPincode());

        customer.setSourceLead(lead);

        return customer;
    }

    public static CustomerResponseDTO toResponseDTO(
            Customer customer) {

        CustomerResponseDTO response = new CustomerResponseDTO();

        response.setId(customer.getId());

        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        response.setAlternatePhone(customer.getAlternatePhone());

        response.setAddressLine1(customer.getAddressLine1());
        response.setAddressLine2(customer.getAddressLine2());
        response.setCity(customer.getCity());
        response.setState(customer.getState());
        response.setCountry(customer.getCountry());
        response.setPincode(customer.getPincode());

        if (customer.getSourceLead() != null) {
            response.setSourceLeadId(
                    customer.getSourceLead().getId());
        }

        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());

        return response;
    }
}