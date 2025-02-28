package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.InsuranceCalculationResponse;
import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;
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
    public List<InsuranceTypeDto> getAllInsuranceTypes () {
        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        return insuranceTypeMapper.entityListToDto(insuranceTypes);
    }

    @Override
    public InsuranceTypeDto getInsuranceTypeById (UUID id) {
        InsuranceTypeEntity insuranceTypeEntity = insuranceTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance type not found"));

        return insuranceTypeMapper.toDto(insuranceTypeEntity);
    }

    @Override
    public List<InsuranceTypeEntity> getAllInsuranceTypesEntitiesByIds (List<UUID> insuranceTypeIds) {

        return insuranceTypeRepository.findAllById(insuranceTypeIds);
    }

    @Override
    public List<InsuranceTypeDto> getAllCalculatedInsuranceTypesByDates (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        long days =
                dateUtilsService.calculateDateDifferenceInDays(customerTravelInsuranceRequest.insuranceDTO().startDate(),
                        customerTravelInsuranceRequest.insuranceDTO().endDate());
        int amountOfPeople =
                customerTravelInsuranceRequest.insuredPersonDTO().size();

        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        List<InsuranceTypeDto> insuranceTypeDtos = insuranceTypeMapper.entityListToDto(insuranceTypes);

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
    public InsuranceCalculationResponse getPickedInsuranceTypes (CustomerTravelInsuranceRequest customerTravelInsuranceRequest, List<InsuranceTypeDto> insuranceTypes, CountryDto countryDto) {
        List<InsuranceTypeDto> pickedInsuranceTypesByUser =
                insuranceTypes.stream().filter(insuranceTypeDto -> customerTravelInsuranceRequest.insuranceDTO().insuranceTypeIds().contains(insuranceTypeDto.id())).toList();
        double totalCalculatedPrice =
                pickedInsuranceTypesByUser.stream().mapToDouble(InsuranceTypeDto::totalCalculatedPrice).sum() + countryDto.coverageRegion().totalCalculatedPrice();
        return new InsuranceCalculationResponse(pickedInsuranceTypesByUser, totalCalculatedPrice);
    }
}
