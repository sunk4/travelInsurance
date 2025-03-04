package com.roman.Insurance.insuranceType.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record InsuranceTypeResponse
        (UUID id,
         String name,
         String description,
         double basePricePerDay,
         double totalCalculatedPrice,
         Boolean isAdditionalInsurance,
         Boolean isPriceTotal,
         LocalDateTime createdAt,
         LocalDateTime updatedAt) {
    public InsuranceTypeResponse withTotalCalculatedPrice (double totalCalculatedPrice) {
        return new InsuranceTypeResponse(id, name, description, basePricePerDay,
                totalCalculatedPrice, isAdditionalInsurance, isPriceTotal,
                createdAt,
                updatedAt);
    }
}
