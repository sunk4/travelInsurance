package com.roman.Insurance.calculation;

import com.roman.Insurance.calculation.response.CalculationResponse;
import com.roman.Insurance.calculation.response.InsuranceCalculationResponse;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.insuranceType.InsuranceTypeService;
import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;
import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import com.roman.Insurance.insuredPerson.InsuredPersonService;
import com.roman.Insurance.insuredPerson.response.InsuredPersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final InsuranceTypeService insuranceTypeService;
    private final CountryService countryService;
    private final InsuredPersonService insuredPersonService;

    @Override
    public CalculationResponse calculatePrice (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CountryResponse countryResponse = countryService.findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory(customerTravelInsuranceRequest);

        List<InsuranceTypeResponse> insuranceTypes =
                insuranceTypeService.getAllCalculatedInsuranceTypesByDates(customerTravelInsuranceRequest);

        InsuranceCalculationResponse pickedInsuranceTypes =
                insuranceTypeService.getPickedInsuranceTypes(customerTravelInsuranceRequest, insuranceTypes, countryResponse);

        List<InsuredPersonResponse> insuredPersons =
                insuredPersonService.getInsuredPersons(customerTravelInsuranceRequest.insuredPersonRequest());

        return new CalculationResponse(countryResponse, insuranceTypes,
                pickedInsuranceTypes.pickedInsuranceTypes(),
                pickedInsuranceTypes.totalCalculatedPrice(),
                insuredPersons
        );

    }
}
