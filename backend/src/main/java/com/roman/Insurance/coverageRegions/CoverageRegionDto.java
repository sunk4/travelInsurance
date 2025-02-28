package com.roman.Insurance.coverageRegions;

import java.time.LocalDateTime;
import java.util.UUID;

public record CoverageRegionDto(
        UUID id,
        String name,
        String description,
        Double basePricePerDay,
        Double totalCalculatedPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public CoverageRegionDto withTotalCalculatedPrice (double totalCalculatedPrice) {
        return new CoverageRegionDto(id, name, description, basePricePerDay,
                totalCalculatedPrice, createdAt, updatedAt);
    }

}
