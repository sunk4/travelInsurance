package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.InsuranceCalculationResponse;
import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;

import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeDto> getAllInsuranceTypes();
    InsuranceTypeDto getInsuranceTypeById(UUID id);
    List<InsuranceTypeEntity> getAllInsuranceTypesEntitiesByIds(List<UUID> insuranceTypeIds);

    List<InsuranceTypeDto> getAllCalculatedInsuranceTypesByDates (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);

    InsuranceCalculationResponse getPickedInsuranceTypes (CustomerTravelInsuranceRequest customerTravelInsuranceRequest, List<InsuranceTypeDto> insuranceTypes, CountryDto countryDto);
}
