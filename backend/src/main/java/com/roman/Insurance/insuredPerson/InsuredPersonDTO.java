package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.ageCategories.AgeCategoryDto;
import com.roman.Insurance.riskFactor.RiskFactorDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuredPersonDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        AgeCategoryDto ageCategory,
        RiskFactorDto riskFactor,
        UUID mainCustomerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
