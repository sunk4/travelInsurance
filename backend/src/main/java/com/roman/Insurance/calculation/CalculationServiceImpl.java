package com.roman.Insurance.calculation;

import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;
import com.roman.Insurance.insuranceType.InsuranceTypeDto;
import com.roman.Insurance.insuranceType.InsuranceTypeService;
import com.roman.Insurance.insuredPerson.InsuredPersonDTO;
import com.roman.Insurance.insuredPerson.InsuredPersonService;
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
    public CalculationDto calculatePrice (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CountryDto countryDto = countryService.findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory(customerTravelInsuranceRequest);

                List<InsuranceTypeDto> insuranceTypes =
                insuranceTypeService.getAllCalculatedInsuranceTypesByDates(customerTravelInsuranceRequest);

                InsuranceCalculationResponse pickedInsuranceTypes =
                insuranceTypeService.getPickedInsuranceTypes(customerTravelInsuranceRequest, insuranceTypes,countryDto);

                List<InsuredPersonDTO> insuredPersons =
                        insuredPersonService.getInsuredPersons(customerTravelInsuranceRequest.insuredPersonDTO());


                return new CalculationDto(countryDto, insuranceTypes,
                pickedInsuranceTypes.pickedInsuranceTypes(),
                        pickedInsuranceTypes.totalCalculatedPrice(),
                        insuredPersons
                );

    }

    @Override
    public double calculatePriceTotal (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CountryDto countryDto = countryService.findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory(customerTravelInsuranceRequest);

        List<InsuranceTypeDto> insuranceTypes =
                insuranceTypeService.getAllCalculatedInsuranceTypesByDates(customerTravelInsuranceRequest);

        InsuranceCalculationResponse pickedInsuranceTypes =
                insuranceTypeService.getPickedInsuranceTypes(customerTravelInsuranceRequest, insuranceTypes,countryDto);

     return pickedInsuranceTypes.totalCalculatedPrice();
    }
}
