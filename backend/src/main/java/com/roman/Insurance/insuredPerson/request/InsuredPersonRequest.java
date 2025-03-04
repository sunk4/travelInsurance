package com.roman.Insurance.insuredPerson.request;

import com.roman.Insurance.ageCategories.request.AgeCategoryRequest;
import com.roman.Insurance.riskFactor.response.RiskFactorResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuredPersonRequest(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        AgeCategoryRequest ageCategory,
        RiskFactorResponse riskFactor,
        UUID mainCustomerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
