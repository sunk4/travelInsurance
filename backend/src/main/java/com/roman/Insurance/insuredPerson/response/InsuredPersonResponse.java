package com.roman.Insurance.insuredPerson.response;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import com.roman.Insurance.riskFactor.response.RiskFactorResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuredPersonResponse(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        AgeCategoryResponse ageCategory,
        RiskFactorResponse riskFactor,
        UUID mainCustomerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}