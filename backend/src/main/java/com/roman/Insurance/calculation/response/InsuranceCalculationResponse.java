package com.roman.Insurance.calculation.response;

import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;

import java.util.List;

public record InsuranceCalculationResponse(
        List<InsuranceTypeResponse> pickedInsuranceTypes,
        double totalCalculatedPrice
) {
}
