package com.example.demo.dto;

import java.util.List;

public class BulkLeadImportResponseDTO {

    private int totalRows;
    private int successCount;
    private int failedCount;
    private int skippedCount;
    private List<String> errors;

    public BulkLeadImportResponseDTO() {
    }

    public BulkLeadImportResponseDTO(
            int totalRows,
            int successCount,
            int failedCount,
            int skippedCount,
            List<String> errors) {

        this.totalRows = totalRows;
        this.successCount = successCount;
        this.failedCount = failedCount;
        this.skippedCount = skippedCount;
        this.errors = errors;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}