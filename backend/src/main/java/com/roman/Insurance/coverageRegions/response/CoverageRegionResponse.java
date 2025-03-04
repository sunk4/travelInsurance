package com.roman.Insurance.coverageRegions.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CoverageRegionResponse(
        UUID id,
        String name,
        String description,
        Double basePricePerDay,
        Double totalCalculatedPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public CoverageRegionResponse withTotalCalculatedPrice (double totalCalculatedPrice) {
        return new CoverageRegionResponse(id, name, description, basePricePerDay,
                totalCalculatedPrice, createdAt, updatedAt);
    }

}
