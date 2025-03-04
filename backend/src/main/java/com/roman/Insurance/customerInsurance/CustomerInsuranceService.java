package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;

public interface CustomerInsuranceService {
    void createTravelInsurance (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) throws Exception;
}
