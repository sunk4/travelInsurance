package com.roman.Insurance.calculation;

import com.roman.Insurance.insuranceType.InsuranceTypeDto;

import java.util.List;

public record InsuranceCalculationResponse(
        List<InsuranceTypeDto> pickedInsuranceTypes,
        double totalCalculatedPrice
) {
}
