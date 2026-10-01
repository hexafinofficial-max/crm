package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadStatus;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LeadRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final LeadRepository leadRepository;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            LeadRepository leadRepository) {

        this.customerRepository = customerRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    @Transactional
    public CustomerResponseDTO createCustomerFromLead(Long leadId) {

        // 1. Lead शोधा
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException(
                        "Lead not found with id: " + leadId));

        // 2. Lead WON आहे का ते तपासा
        if (lead.getStatus() != LeadStatus.WON) {

            throw new RuntimeException(
                    "Only WON lead can be converted to customer");
        }

        // 3. हा Lead आधीच Customer झाला आहे का?
        if (customerRepository.existsBySourceLeadId(leadId)) {

            throw new RuntimeException(
                    "Customer already exists for lead id: " + leadId);
        }

        // 4. Lead मधून Customer तयार करा
        Customer customer = new Customer();

        customer.setFirstName(lead.getFirstName());
        customer.setLastName(lead.getLastName());
        customer.setEmail(lead.getEmail());
        customer.setPhone(lead.getPhone());

        customer.setAddressLine1(lead.getAddressLine1());
        customer.setAddressLine2(lead.getAddressLine2());
        customer.setCity(lead.getCity());
        customer.setState(lead.getState());
        customer.setCountry(lead.getCountry());
        customer.setPincode(lead.getPincode());

        customer.setSourceLead(lead);

        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        // 5. Customer save करा
        Customer savedCustomer =
                customerRepository.save(customer);

        // 6. Response DTO
        return CustomerMapper.toResponseDTO(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with id: " + customerId));

        return CustomerMapper.toResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO getCustomerByLeadId(Long leadId) {

        Customer customer = customerRepository
                .findBySourceLeadId(leadId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found for lead id: " + leadId));

        return CustomerMapper.toResponseDTO(customer);
    }

    // UPDATE CUSTOMER
    @Override
    public CustomerResponseDTO updateCustomer(
            Long customerId,
            CustomerRequestDTO request) {

        // 1. Customer शोधा
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with id: " + customerId));

        // 2. Customer information update करा
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

        // 3. Updated time
        customer.setUpdatedAt(LocalDateTime.now());

        // 4. Save updated customer
        Customer updatedCustomer =
                customerRepository.save(customer);

        // 5. Return response
        return CustomerMapper.toResponseDTO(updatedCustomer);
    }
}