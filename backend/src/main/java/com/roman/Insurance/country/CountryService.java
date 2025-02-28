package com.roman.Insurance.country;


import com.roman.Insurance.coverageRegions.CoverageRegionDto;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryDto> findAllCountries();

    CountryDto findCountryById (UUID id);
    CountryEntity findCountryEntityById (UUID id);

    CountryDto findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);
}
