package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.response.InsuranceCalculationResponse;
import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;

import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeResponse> getAllInsuranceTypes ();

    InsuranceTypeResponse getInsuranceTypeById (UUID id);

    List<InsuranceTypeEntity> getAllInsuranceTypesEntitiesByIds (List<UUID> insuranceTypeIds);

    List<InsuranceTypeResponse> getAllCalculatedInsuranceTypesByDates (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);

    InsuranceCalculationResponse getPickedInsuranceTypes (CustomerTravelInsuranceRequest customerTravelInsuranceRequest, List<InsuranceTypeResponse> insuranceTypes, CountryResponse countryResponse);
}
