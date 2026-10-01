package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BulkLeadImportResponseDTO;
import com.example.demo.service.BulkLeadImportService;

@RestController
@RequestMapping("/api/leads")
public class BulkLeadImportController {

    private final BulkLeadImportService bulkLeadImportService;

    public BulkLeadImportController(
            BulkLeadImportService bulkLeadImportService) {
        this.bulkLeadImportService = bulkLeadImportService;
    }

    @PostMapping(
            value = "/import",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BulkLeadImportResponseDTO importLeads(
            @RequestPart("file") MultipartFile file) {

        return bulkLeadImportService.importLeads(file);
    }
}