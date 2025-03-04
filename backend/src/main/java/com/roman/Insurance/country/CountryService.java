package com.roman.Insurance.country;

import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryResponse> findAllCountries ();

    CountryResponse findCountryById (UUID id);

    CountryEntity findCountryEntityById (UUID id);

    CountryResponse findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory (CustomerTravelInsuranceRequest customerTravelInsuranceRequest);
}
