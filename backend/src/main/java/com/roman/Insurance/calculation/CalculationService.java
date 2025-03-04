package com.roman.Insurance.calculation;

import com.roman.Insurance.calculation.response.CalculationResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;

public interface CalculationService {
    CalculationResponse calculatePrice (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);

}
