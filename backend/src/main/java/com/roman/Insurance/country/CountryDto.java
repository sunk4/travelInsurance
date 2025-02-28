package com.roman.Insurance.country;

import com.roman.Insurance.coverageRegions.CoverageRegionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CountryDto(
        UUID id,
        String name,
        CoverageRegionDto coverageRegion,
        long days,
        LocalDate startDate,
        LocalDate endDate,
        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}
