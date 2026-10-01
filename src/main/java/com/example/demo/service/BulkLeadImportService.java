package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BulkLeadImportResponseDTO;

public interface BulkLeadImportService {

    BulkLeadImportResponseDTO importLeads(MultipartFile file);
}