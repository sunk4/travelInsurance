package com.roman.Insurance.insuranceType;

import java.time.LocalDateTime;
import java.util.UUID;

public record InsuranceTypeDto
        (UUID id,
         String name,
         String description,
         double basePricePerDay,
         double totalCalculatedPrice,
         Boolean isAdditionalInsurance,
         Boolean isPriceTotal,
         LocalDateTime createdAt,
         LocalDateTime updatedAt) {
    public InsuranceTypeDto withTotalCalculatedPrice (double totalCalculatedPrice) {
        return new InsuranceTypeDto(id, name, description, basePricePerDay,
                totalCalculatedPrice, isAdditionalInsurance, isPriceTotal,
                createdAt,
                updatedAt);
    }
}
