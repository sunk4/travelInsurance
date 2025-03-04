package com.roman.Insurance.country.response;

import com.roman.Insurance.coverageRegions.response.CoverageRegionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CountryResponse(
        UUID id,
        String name,
        CoverageRegionResponse coverageRegion,
        long days,
        LocalDate startDate,
        LocalDate endDate,
        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}
