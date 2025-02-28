package com.roman.Insurance.calculation;

import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.insuranceType.InsuranceTypeDto;
import com.roman.Insurance.insuredPerson.InsuredPersonDTO;

import java.util.List;

public record CalculationDto(CountryDto country,
                             List<InsuranceTypeDto> insuranceTypes,
                             List<InsuranceTypeDto> pickedInsuranceTypes,
                             double totalCalculatedPrice,
                              List<InsuredPersonDTO> insuredPersons
                             ) {
}
