package com.example.demo.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BulkLeadImportResponseDTO;
import com.example.demo.entity.Lead;
import com.example.demo.entity.LeadSource;
import com.example.demo.entity.LeadStatus;
import com.example.demo.repository.LeadRepository;
import com.example.demo.service.BulkLeadImportService;

@Service
public class BulkLeadImportServiceImpl implements BulkLeadImportService {

    private final LeadRepository leadRepository;

    public BulkLeadImportServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public BulkLeadImportResponseDTO importLeads(MultipartFile file) {

        int totalRows = 0;
        int successCount = 0;
        int failedCount = 0;
        int skippedCount = 0;

        
        
        List<String> errors = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();

            // Row 0 = Header
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                totalRows++;

                try {

                    String firstName = getCellValue(row.getCell(0), formatter);
                    String lastName = getCellValue(row.getCell(1), formatter);
                    String companyName = getCellValue(row.getCell(2), formatter);
                    String email = getCellValue(row.getCell(3), formatter);
                    String phone = getCellValue(row.getCell(4), formatter);
                    String addressLine1 = getCellValue(row.getCell(5), formatter);
                    String addressLine2 = getCellValue(row.getCell(6), formatter);
                    String city = getCellValue(row.getCell(7), formatter);
                    String state = getCellValue(row.getCell(8), formatter);
                    String country = getCellValue(row.getCell(9), formatter);
                    String pincode = getCellValue(row.getCell(10), formatter);
                    String sourceValue = getCellValue(row.getCell(11), formatter);
                    String description = getCellValue(row.getCell(12), formatter);

                    // Basic validation
                    if (firstName.isBlank()) {
                        throw new RuntimeException("First name is required");
                    }

                    if (email.isBlank() && phone.isBlank()) {
                        throw new RuntimeException(
                                "Email or phone is required");
                    }

                    // Duplicate check
                    if (!email.isBlank() && leadRepository.existsByEmail(email)) {
                        skippedCount++;
                        continue;
                    }

                    if (!phone.isBlank() && leadRepository.existsByPhone(phone)) {
                        skippedCount++;
                        continue;
                    }

                    Lead lead = new Lead();

                    lead.setFirstName(firstName);
                    lead.setLastName(lastName);
                    lead.setCompanyName(companyName);
                    lead.setEmail(email);
                    lead.setPhone(phone);
                    lead.setAddressLine1(addressLine1);
                    lead.setAddressLine2(addressLine2);
                    lead.setCity(city);
                    lead.setState(state);
                    lead.setCountry(country);
                    lead.setPincode(pincode);
                    lead.setDescription(description);

                    // Source
                    if (!sourceValue.isBlank()) {
                        lead.setSource(
                                LeadSource.valueOf(sourceValue.toUpperCase()));
                    } else {
                        lead.setSource(LeadSource.OTHER);
                    }

                    // New lead
                    lead.setStatus(LeadStatus.NEW);

lead.setCreatedAt(LocalDateTime.now());
lead.setUpdatedAt(LocalDateTime.now());

                    leadRepository.save(lead);

                    successCount++;

                } catch (Exception e) {

                    failedCount++;

                    errors.add(
                            "Row " + (i + 1) + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to read Excel file: " + e.getMessage());
        }

        return new BulkLeadImportResponseDTO(
                totalRows,
                successCount,
                failedCount,
                skippedCount,
                errors);
    }

    private String getCellValue(
            Cell cell,
            DataFormatter formatter) {

        if (cell == null) {
            return "";
        }

        return formatter.formatCellValue(cell).trim();
    }
    
}