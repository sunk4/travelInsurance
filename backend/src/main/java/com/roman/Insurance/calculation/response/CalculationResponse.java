package com.roman.Insurance.calculation.response;

import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;
import com.roman.Insurance.insuredPerson.response.InsuredPersonResponse;

import java.util.List;

public record CalculationResponse(CountryResponse country,
                                  List<InsuranceTypeResponse> insuranceTypes,
                                  List<InsuranceTypeResponse> pickedInsuranceTypes,
                                  double totalCalculatedPrice,
                                  List<InsuredPersonResponse> insuredPersons
) {
}
