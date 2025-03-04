package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.response.InsuranceCalculationResponse;
import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;
import com.roman.Insurance.utils.DateUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceTypeServiceImpl implements InsuranceTypeService {
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final InsuranceTypeMapper insuranceTypeMapper;
    private final DateUtilsService dateUtilsService;

    @Override
    public List<InsuranceTypeResponse> getAllInsuranceTypes () {
        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        return insuranceTypeMapper.entityListToDto(insuranceTypes);
    }

    @Override
    public InsuranceTypeResponse getInsuranceTypeById (UUID id) {
        InsuranceTypeEntity insuranceTypeEntity = insuranceTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance type not found"));

        return insuranceTypeMapper.toDto(insuranceTypeEntity);
    }

    @Override
    public List<InsuranceTypeEntity> getAllInsuranceTypesEntitiesByIds (List<UUID> insuranceTypeIds) {

        return insuranceTypeRepository.findAllById(insuranceTypeIds);
    }

    @Override
    public List<InsuranceTypeResponse> getAllCalculatedInsuranceTypesByDates (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        long days =
                dateUtilsService.calculateDateDifferenceInDays(customerTravelInsuranceRequest.insuranceRequest().startDate(),
                        customerTravelInsuranceRequest.insuranceRequest().endDate());
        int amountOfPeople =
                customerTravelInsuranceRequest.insuredPersonRequest().size();

        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        List<InsuranceTypeResponse> insuranceTypeDtos = insuranceTypeMapper.entityListToDto(insuranceTypes);

        return insuranceTypeDtos.stream().map(insuranceTypeDto -> {
            double totalCalculatedPrice;
            if (insuranceTypeDto.isPriceTotal()) {
                totalCalculatedPrice =
                        Math.round((insuranceTypeDto.basePricePerDay()) * 100.0) / 100.0;
            } else if (insuranceTypeDto.isAdditionalInsurance()) {
                totalCalculatedPrice = Math.round((insuranceTypeDto.basePricePerDay() * amountOfPeople) * 100.0) / 100.0;
            } else {
                totalCalculatedPrice = Math.round((days * insuranceTypeDto.basePricePerDay() * amountOfPeople) * 100.0) / 100.0;
            }
            return insuranceTypeDto.withTotalCalculatedPrice(totalCalculatedPrice);

        }).toList();
    }

    @Override
    public InsuranceCalculationResponse getPickedInsuranceTypes (CustomerTravelInsuranceRequest customerTravelInsuranceRequest, List<InsuranceTypeResponse> insuranceTypes, CountryResponse countryResponse) {
        List<InsuranceTypeResponse> pickedInsuranceTypesByUser =
                insuranceTypes.stream().filter(insuranceTypeDto -> customerTravelInsuranceRequest.insuranceRequest().insuranceTypeIds().contains(insuranceTypeDto.id())).toList();
        double totalCalculatedPrice =
                pickedInsuranceTypesByUser.stream().mapToDouble(InsuranceTypeResponse::totalCalculatedPrice).sum() + countryResponse.coverageRegion().totalCalculatedPrice();
        return new InsuranceCalculationResponse(pickedInsuranceTypesByUser, totalCalculatedPrice);
    }
}
