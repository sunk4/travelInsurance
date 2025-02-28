package com.roman.Insurance.calculation;

import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;

public interface CalculationService {
    CalculationDto calculatePrice (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);
    double calculatePriceTotal (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);
}
